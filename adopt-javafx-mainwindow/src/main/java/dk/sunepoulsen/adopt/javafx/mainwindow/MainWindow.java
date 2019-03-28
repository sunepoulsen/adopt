package dk.sunepoulsen.adopt.javafx.mainwindow;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class MainWindow extends BorderPane {
    private static Logger log = LoggerFactory.getLogger( MainWindow.class );

    public MainWindow() {
        FXMLLoader fxmlLoader = new FXMLLoader( getClass().getResource( "mainwindow.fxml" ) );
        fxmlLoader.setController( this );

        try {
            log.debug( "Loading MainWindow from mainwindow.fxml" );
            fxmlLoader.load();
        }
        catch( IOException exception ) {
            throw new RuntimeException( exception );
        }
    }
}
