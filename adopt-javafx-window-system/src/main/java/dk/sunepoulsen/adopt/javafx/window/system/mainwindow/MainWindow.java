package dk.sunepoulsen.adopt.javafx.window.system.mainwindow;

import dk.sunepoulsen.adopt.javafx.window.system.api.AdoptWindowMode;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainWindow implements Initializable {
    private static Logger log = LoggerFactory.getLogger( MainWindow.class );

    private URL url;
    private ResourceBundle resourceBundle;
    private BorderPane root;
    private List<AdoptWindowMode> modes;

    @Override
    public void initialize( URL url, ResourceBundle resourceBundle ) {
        this.url = url;
        this.resourceBundle = resourceBundle;
    }

    public void configureWindowModes( BorderPane root ) {
        this.root = root;
        this.modes = new ArrayList<>();

        collectWindowMode( root.getTop() );
        collectWindowMode( root.getLeft() );
        collectWindowMode( root.getCenter() );
        collectWindowMode( root.getRight() );
        collectWindowMode( root.getBottom() );

        if( log.isDebugEnabled() ) {
            this.modes.stream()
                .forEach( adoptWindowMode -> log.debug( "Configured window mode {} of type {}", adoptWindowMode.getModeIdentifier(), adoptWindowMode.getClass().getName() ) );
        }
    }

    private void collectWindowMode( Node node ) {
        if( node instanceof AdoptWindowMode ) {
            modes.add( ( AdoptWindowMode ) node );
        }

    }
}
