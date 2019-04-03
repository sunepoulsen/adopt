package dk.sunepoulsen.adopt.javafx.window.system.api;

import javafx.beans.property.ReadOnlyListProperty;
import javafx.beans.property.ReadOnlyListWrapper;
import javafx.scene.layout.AnchorPane;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Defines a Window mode.
 * <p>
 * A Window Mode is a region in the application window that can contain AdoptTopComponents when they are opened.
 * </p>
 * <p>
 * If a Window Mode uses controls to manage it's TopComponents, like a <code>TabPane</code>, then the implementation
 * should return a Pane with the <code>TabPane</code> inside of it.
 * </p>
 */
public abstract class AdoptWindowMode extends AnchorPane {
    private String modeIdentifier;
    protected ReadOnlyListWrapper<AdoptTopComponent> topComponents;

    public AdoptWindowMode() {
        super();
        this.modeIdentifier = null;
        this.topComponents = new ReadOnlyListWrapper<>();
    }

    public String getModeIdentifier() {
        return modeIdentifier;
    }

    public void setModeIdentifier( String modeIdentifier ) {
        this.modeIdentifier = modeIdentifier;
    }

    /**
     * Returns a read only list of the TopComponent's in this Window Mode.
     */
    public ReadOnlyListProperty<AdoptTopComponent> topComponents() {
        return this.topComponents.getReadOnlyProperty();
    }

    /**
     * Adds a new TopCompoent to this Window Mode.
     * <p>
     * It is up to the implementation to define whether it should be shown, have focus, etc.
     * </p>
     */
    public abstract void addTopComponent( AdoptTopComponent topComponent );

    /**
     * Removes a TopComponent from this Window Mode.
     * <p>
     * It is up to the implementation to define what should happen to focus, etc.
     * </p>
     */
    public abstract void removeTopComponent( AdoptTopComponent topComponent );

    /**
     * Find and returns the first instance of a TopComponent.
     */
    public Optional<AdoptTopComponent> findTopComponent( Class<AdoptTopComponent> clazz ) {
        return topComponents.stream()
            .filter( topComponent -> topComponent.getClass() == clazz )
            .findFirst();
    }

    /**
     * Find all instances of a TopComponent.
     */
    public List<AdoptTopComponent> findAllTopComponent( Class<AdoptTopComponent> clazz ) {
        return topComponents.stream()
            .filter( topComponent -> topComponent.getClass() == clazz )
            .collect( Collectors.toList() );
    }
}
