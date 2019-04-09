package dk.sunepoulsen.adopt.javafx.window.system.api;

/**
 * Interface that defines the integration between TopComponent's and the Window System Model.
 * <p>
 * This interface is passed to a TopComponent when it is inserted and viewed in the Window System. A TopComponent will
 * use this integrator to integrate with the Window System to lookup other TopComponents and to add and open
 * TopComponents.
 * </p>
 */
public interface WindowSystemModelIntegrator {
}
