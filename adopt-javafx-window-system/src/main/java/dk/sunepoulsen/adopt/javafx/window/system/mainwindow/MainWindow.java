package dk.sunepoulsen.adopt.javafx.window.system.mainwindow;

import com.google.common.collect.Lists;
import dk.sunepoulsen.adopt.javafx.window.system.api.AdoptTopComponent;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.ServiceLoader;

public class MainWindow implements Initializable {
    private WindowSystemModel windowSystemModel;

    public MainWindow() {
        this.windowSystemModel = new WindowSystemModel();
    }

    @Override
    public void initialize( URL url, ResourceBundle resourceBundle ) {
    }

    public void configureWindowModes( BorderPane root ) {
        this.windowSystemModel.configureWindowModes( root );
    }

    public void createStartupTopComponents() {
        ServiceLoader<AdoptTopComponent> loader = ServiceLoader.load( AdoptTopComponent.class );

        ArrayList<AdoptTopComponent> topComponents = Lists.newArrayList( loader.iterator() );
        topComponents.stream()
            .filter( AdoptTopComponent::createOnStartup )
            .forEach( topComponent -> this.windowSystemModel.addTopComponent( topComponent ) );
    }
}
