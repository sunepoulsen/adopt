package dk.sunepoulsen.adopt.javafx.window.system.modes;

import dk.sunepoulsen.adopt.javafx.window.system.api.AdoptTopComponent;
import dk.sunepoulsen.adopt.javafx.window.system.api.AdoptWindowMode;
import javafx.beans.property.ReadOnlyListProperty;
import javafx.beans.property.ReadOnlyListWrapper;
import javafx.scene.control.SplitPane;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implements a Window Mode with a single parent component that can contain a single TopComponent.
 */
public class AdoptSplitMode extends SplitPane implements AdoptWindowMode {
    private String modeIdentifier;
    private ReadOnlyListWrapper<AdoptTopComponent> topComponents;

    public AdoptSplitMode() {
        super();
        this.modeIdentifier = null;
        this.topComponents = new ReadOnlyListWrapper<>();
    }

    @Override
    public String getModeIdentifier() {
        return modeIdentifier;
    }

    @Override
    public void setModeIdentifier( String modeIdentifier ) {
        this.modeIdentifier = modeIdentifier;
    }

    @Override
    public ReadOnlyListProperty<AdoptTopComponent> topComponents() {
        return this.topComponents.getReadOnlyProperty();
    }

    @Override
    public void addTopComponent( AdoptTopComponent topComponent ) {
        if( getChildren().add( topComponent.getNode() ) ) {
            layout();
            this.topComponents.add( topComponent );
        }
    }

    @Override
    public void removeTopComponent( AdoptTopComponent topComponent ) {
        if( getChildren().remove( topComponent.getNode() ) ) {
            layout();
            this.topComponents.remove( topComponent );
        }
    }

    @Override
    public Optional<AdoptTopComponent> findTopComponent( Class<AdoptTopComponent> clazz ) {
        return topComponents.stream()
            .filter( topComponent -> topComponent.getClass() == clazz )
            .findFirst();
    }

    @Override
    public List<AdoptTopComponent> findAllTopComponent( Class<AdoptTopComponent> clazz ) {
        return topComponents.stream()
            .filter( topComponent -> topComponent.getClass() == clazz )
            .collect( Collectors.toList() );
    }
}
