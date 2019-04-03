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

    protected URL mainWindowFXMLUrl;
    protected String applicationTitle;

    public AdoptJavaFXApplication() {
        this.mainWindowFXMLUrl = MainWindow.class.getResource( "mainwindow.fxml" );
        this.applicationTitle = "Application Title";
    }

    /**
     * Main launch function that will start the JavaFX application.
     *
     * @param args Arguments from the command line.
     */
    public static void launchApplication( String[] args ) {
        launch( args );
    }

    /**
     * Main launch function that will start the JavaFX application.
     *
     * @param args Arguments from the command line.
     */
    public static void launchApplication( Class<? extends Application> clazz, String[] args ) {
        launch( clazz, args );
    }

    @Override
    public void start( Stage primaryStage ) throws Exception {
        log.debug( "Creating primary stage" );

        FXMLLoader loader = new FXMLLoader( this.mainWindowFXMLUrl );
        Parent root = loader.load();
        if( !( root instanceof BorderPane ) ) {
            throw new IllegalStateException( "FXML must load a BorderPane from " + mainWindowFXMLUrl.toString() );
        }
        MainWindow mainWindow = loader.getController();
        mainWindow.configureWindowModes( ( BorderPane ) root );
        mainWindow.createStartupTopComponents();

        Scene scene = new Scene( root );

        primaryStage.setTitle( this.applicationTitle );
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
