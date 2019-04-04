package dk.sunepoulsen.adopt.javafx.window.system.modes;

import dk.sunepoulsen.adopt.javafx.window.system.api.AdoptTopComponent;
import dk.sunepoulsen.adopt.javafx.window.system.api.AdoptWindowMode;
import javafx.beans.property.ReadOnlyListProperty;
import javafx.beans.property.ReadOnlyListWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.AnchorPane;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implements a Window Mode with a single parent component that can contain a single TopComponent.
 */
public class AdoptAnchorMode extends AnchorPane implements AdoptWindowMode {
    private static AdoptAnchorModeOptions defaultModeOptions = new AdoptAnchorModeOptions();

    private String modeIdentifier;
    private ObservableList<AdoptTopComponent> topComponents;
    private ReadOnlyListWrapper<AdoptTopComponent> topComponentsWrapper;

    public AdoptAnchorMode() {
        super();

        this.modeIdentifier = null;
        this.topComponents = FXCollections.observableArrayList();
        this.topComponentsWrapper = new ReadOnlyListWrapper<>( this.topComponents );
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
        return this.topComponentsWrapper.getReadOnlyProperty();
    }

    @Override
    public void addTopComponent( AdoptTopComponent topComponent ) {
        AdoptAnchorModeOptions modeOptions = defaultModeOptions;
        if( topComponent.getWindowModeOptions() instanceof AdoptAnchorModeOptions ) {
            modeOptions = ( AdoptAnchorModeOptions ) topComponent.getWindowModeOptions();
        }

        AnchorPane.setTopAnchor( topComponent.getNode(), modeOptions.getTopAnchor() );
        AnchorPane.setLeftAnchor( topComponent.getNode(), modeOptions.getLeftAnchor() );
        AnchorPane.setRightAnchor( topComponent.getNode(), modeOptions.getRightAnchor() );
        AnchorPane.setBottomAnchor( topComponent.getNode(), modeOptions.getBottomAnchor() );

        getChildren().clear();
        this.topComponents.clear();

        if( getChildren().add( topComponent.getNode() ) ) {
            this.topComponents.add( topComponent );
        }

        layout();
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
