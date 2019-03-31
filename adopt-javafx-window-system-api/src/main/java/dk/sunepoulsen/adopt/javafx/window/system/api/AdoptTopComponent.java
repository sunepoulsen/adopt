package dk.sunepoulsen.adopt.javafx.window.system.api;

import javafx.scene.Node;

/**
 * Defines a TopComponent.
 * <p>
 * A TopComponent is a UI component that can be placed inside an AdoptWindowMode.
 * </p>
 */
public interface AdoptTopComponent {
    /**
     * Returns identifier of the Window Mode that should contain TopComponent's of this type.
     */
    String modeIdentifier();

    /**
     * Gets the Node that represents ths UI of the TopComponent.
     *
     * <p>
     * The implementation class is required to cache the TopComponent.
     * </p>
     */
    Node getNode();
}
