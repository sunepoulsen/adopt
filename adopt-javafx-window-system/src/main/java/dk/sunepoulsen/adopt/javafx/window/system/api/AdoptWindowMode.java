package dk.sunepoulsen.adopt.javafx.window.system.api;

import javafx.beans.property.ReadOnlyListProperty;

import java.util.List;
import java.util.Optional;

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
public interface AdoptWindowMode {
    String getModeIdentifier();

    void setModeIdentifier( String modeIdentifier );

    /**
     * Returns a read only list of the TopComponent's in this Window Mode.
     */
    ReadOnlyListProperty<AdoptTopComponent> topComponents();

    /**
     * Adds a new TopCompoent to this Window Mode.
     * <p>
     * It is up to the implementation to define whether it should be shown, have focus, etc.
     * </p>
     */
    void addTopComponent( AdoptTopComponent topComponent );

    /**
     * Removes a TopComponent from this Window Mode.
     * <p>
     * It is up to the implementation to define what should happen to focus, etc.
     * </p>
     */
    void removeTopComponent( AdoptTopComponent topComponent );

    /**
     * Find and returns the first instance of a TopComponent.
     */
    Optional<AdoptTopComponent> findTopComponent( Class<AdoptTopComponent> clazz );

    /**
     * Find all instances of a TopComponent.
     */
    List<AdoptTopComponent> findAllTopComponent( Class<AdoptTopComponent> clazz );
}
