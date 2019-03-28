package dk.sunepoulsen.adopt.javafx.mainwindow;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class MainWindow extends BorderPane {
    public MainWindow() {
        FXMLLoader fxmlLoader = new FXMLLoader( getClass().getResource( "mainwindow.fxml" ) );
        fxmlLoader.setController( this );

        try {
            fxmlLoader.load();
        }
        catch( IOException exception ) {
            throw new RuntimeException( exception );
        }
    }
}
