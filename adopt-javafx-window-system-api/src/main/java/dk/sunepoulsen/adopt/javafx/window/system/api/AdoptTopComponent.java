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
     * Returns the Window Mode options for this TopComponent.
     */
    AdoptTopComponentWindowModeOptions getWindowModeOptions();

    /**
     * Sets the Window mode options for this TopComponent.
     *
     * @param windowModeOptions An instance of Window mode options that matches the Window Mode that is specified in
     *                          <code>modeIdentifier</code>
     */
    void setWindowModeOptions( AdoptTopComponentWindowModeOptions windowModeOptions );

    /**
     * Sets the WindowSystemModelIntegrator of this TopComponent.
     *
     * @param windowSystemModelIntegrator The Window System Model integrator.
     */
    void setWindowSystemModelIntegrator( WindowSystemModelIntegrator windowSystemModelIntegrator );

    /**
     * Gets the Node that represents ths UI of the TopComponent.
     *
     * <p>
     * The implementation class is required to cache the TopComponent.
     * </p>
     */
    Node getNode();
}
