package dk.sunepoulsen.adopt.javafx.window.system.mainwindow;

import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

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
}
