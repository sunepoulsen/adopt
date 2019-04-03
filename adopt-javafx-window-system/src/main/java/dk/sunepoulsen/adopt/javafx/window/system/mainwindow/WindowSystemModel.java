package dk.sunepoulsen.adopt.javafx.window.system.mainwindow;

import dk.sunepoulsen.adopt.javafx.window.system.api.AdoptTopComponent;
import dk.sunepoulsen.adopt.javafx.window.system.api.AdoptWindowMode;
import javafx.beans.DefaultProperty;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class WindowSystemModel {
    private static Logger log = LoggerFactory.getLogger( WindowSystemModel.class );

    private BorderPane root;
    private List<AdoptWindowMode> modes;

    public WindowSystemModel() {
    }

    void addTopComponent( AdoptTopComponent topComponent ) {
        Optional<AdoptWindowMode> windowMode = findWindowMode( topComponent.modeIdentifier() );
        if( windowMode.isEmpty() ) {
            log.warn( "Unable to open TopComponent {} because Window Mode {} does not exist", topComponent.getClass().getName(), topComponent.modeIdentifier() );
            return;
        }

        windowMode.get().addTopComponent( topComponent );
        topComponent.setWindowSystemModelIntegrator( new DefaultWindowSystemModelIntegrator( this ) );
        this.root.layout();
    }

    void configureWindowModes( BorderPane root ) {
        this.root = root;
        this.modes = new ArrayList<>();

        collectWindowMode( root );

        if( log.isDebugEnabled() ) {
            this.modes.stream()
                .forEach( adoptWindowMode -> log.debug( "Configured window mode {} of type {}", adoptWindowMode.getModeIdentifier(), adoptWindowMode.getClass().getName() ) );
        }
    }

    private Optional<AdoptWindowMode> findWindowMode( String identifier ) {
        return this.modes.stream()
            .filter( adoptWindowMode -> adoptWindowMode.getModeIdentifier().equalsIgnoreCase( identifier ) )
            .findFirst();
    }

    private void collectWindowMode( Node node ) {
        if( node instanceof AdoptWindowMode ) {
            modes.add( ( AdoptWindowMode ) node );
        }

        collectChildrenNodes( node ).ifPresent( nodes -> nodes.forEach( this::collectWindowMode ) );
    }

    private Optional<ObservableList<Node>> collectChildrenNodes( Node node ) {
        if( !( node instanceof Parent ) ) {
            return Optional.empty();
        }

        Parent parent = ( Parent ) node;

        String childrenMethodName = "getChildrenUnmodifiable";
        if( parent.getClass().isAnnotationPresent( DefaultProperty.class ) ) {
            DefaultProperty defaultProperty = parent.getClass().getAnnotation( DefaultProperty.class );

            childrenMethodName = "get" + defaultProperty.value().substring( 0, 1 ).toUpperCase() + defaultProperty.value().substring( 1 );
        }

        try {
            Method method = parent.getClass().getMethod( childrenMethodName );
            if( method.getReturnType().equals( ObservableList.class ) ) {
                return Optional.of( ( ObservableList ) method.invoke( parent ) );
            }
        }
        catch( NoSuchMethodException ex ) {
            log.debug( "Method {}.{} does not exist", parent.getClass().getSimpleName(), childrenMethodName, ex );
        }
        catch( InvocationTargetException | IllegalAccessException ex ) {
            log.debug( "Enable to execute method {}.{}", parent.getClass().getSimpleName(), childrenMethodName, ex );
        }

        return Optional.empty();
    }
}
