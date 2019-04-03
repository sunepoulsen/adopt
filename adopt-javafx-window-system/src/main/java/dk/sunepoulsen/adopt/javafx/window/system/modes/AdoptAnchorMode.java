package dk.sunepoulsen.adopt.javafx.window.system.modes;

import dk.sunepoulsen.adopt.javafx.window.system.api.AdoptTopComponent;
import dk.sunepoulsen.adopt.javafx.window.system.api.AdoptWindowMode;
import javafx.scene.layout.AnchorPane;

/**
 * Implements a Window Mode with a single parent component that can contain a single TopComponent.
 */
public class AdoptAnchorMode extends AdoptWindowMode {
    public AdoptAnchorMode() {
        super();
    }

    @Override
    public void addTopComponent( AdoptTopComponent topComponent ) {
        if( topComponent.getWindowModeOptions() instanceof AdoptAnchorModeOptions ) {
            AdoptAnchorModeOptions options = ( AdoptAnchorModeOptions ) topComponent.getWindowModeOptions();

            AnchorPane.setTopAnchor( topComponent.getNode(), options.getTopAnchor() );
            AnchorPane.setLeftAnchor( topComponent.getNode(), options.getLeftAnchor() );
            AnchorPane.setRightAnchor( topComponent.getNode(), options.getRightAnchor() );
            AnchorPane.setBottomAnchor( topComponent.getNode(), options.getBottomAnchor() );
        }

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
