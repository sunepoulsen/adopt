package dk.sunepoulsen.adopt.javafx.window.system.modes;

import dk.sunepoulsen.adopt.javafx.window.system.api.AdoptTopComponent;
import dk.sunepoulsen.adopt.javafx.window.system.api.AdoptWindowMode;

/**
 * Implements a Window Mode with a single parent component that can contain a single TopComponent.
 */
public class AdoptAnchorMode extends AdoptWindowMode {
    public AdoptAnchorMode() {
        super();
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
}
