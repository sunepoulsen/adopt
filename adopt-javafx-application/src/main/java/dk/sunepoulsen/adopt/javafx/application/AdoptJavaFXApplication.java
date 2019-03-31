package dk.sunepoulsen.adopt.javafx.application;

import dk.sunepoulsen.adopt.javafx.window.system.mainwindow.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;

/**
 * Application class for an Adopt JavaFX Application.
 */
public class AdoptJavaFXApplication extends Application {
    private static Logger log = LoggerFactory.getLogger( AdoptJavaFXApplication.class );

    /**
     * Main launch function that will start the JavaFX application.
     *
     * @param args Arguments from the command line.
     */
    public static void launchApplication( String[] args ) {
        launch( AdoptJavaFXApplication.class, args );
    }

    @Override
    public void start( Stage primaryStage ) throws Exception {
        log.debug( "Creating primary stage" );

        URL url = MainWindow.class.getResource( "mainwindow.fxml" );
        FXMLLoader loader = new FXMLLoader( url );
        Parent root = loader.load();
        if( !( root instanceof BorderPane ) ) {
            throw new IllegalStateException( "FXML must load a BorderPane from " + url.toString() );
        }
        MainWindow mainWindow = loader.getController();
        mainWindow.configureWindowModes( ( BorderPane ) root );

        Scene scene = new Scene( root );

        primaryStage.setTitle( "Application Title" );
        primaryStage.setScene( scene );

        // Maximize the window
        final Screen screen = Screen.getPrimary();
        primaryStage.setX( screen.getVisualBounds().getMinX() );
        primaryStage.setY( screen.getVisualBounds().getMinY() );
        primaryStage.setWidth( screen.getVisualBounds().getWidth() );
        primaryStage.setHeight( screen.getVisualBounds().getHeight() );

        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }
}
