package dk.sunepoulsen.adopt.javafx.application.startup;

import dk.sunepoulsen.adopt.core.environment.Environment;
import dk.sunepoulsen.adopt.core.environment.EnvironmentException;
import dk.sunepoulsen.adopt.javafx.window.system.mainwindow.MainWindow;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

/**
 * Helper class to setup the JavaFX Scene
 */
public class SceneStartup {
    private static Logger log = LoggerFactory.getLogger( SceneStartup.class );
    private Environment env;
    private MainWindow mainWindow;
    private Parent mainWindowRoot;

    public SceneStartup( Environment env ) {
        this.env = env;
    }

    public void loadMainWindow() throws EnvironmentException, IOException {
        URL mainWindowFXMLUrl = Objects.requireNonNull( MainWindow.class.getResource( "mainwindow.fxml" ) );
        if( env.containsKey( "mainwindow.fxml" ) ) {
            mainWindowFXMLUrl = Objects.requireNonNull( ClassLoader.getSystemResource( env.getString( "mainwindow.fxml" ) ) );
        }

        FXMLLoader loader = new FXMLLoader( mainWindowFXMLUrl );
        log.debug( "Loading Window modes from {}", mainWindowFXMLUrl.toString() );
        mainWindowRoot = loader.load();
        if( !( mainWindowRoot instanceof BorderPane ) ) {
            throw new IllegalStateException( "FXML must load a BorderPane from " + mainWindowFXMLUrl.toString() );
        }
        mainWindow = loader.getController();
    }

    public void configureWindowModes() {
        mainWindow.configureWindowModes( ( BorderPane ) mainWindowRoot );
    }

    public void createStartupTopComponents() {
        mainWindow.createStartupTopComponents();
    }

    public void createAndShowScene( Stage primaryStage ) throws EnvironmentException {
        Scene scene = new Scene( mainWindowRoot );

        if( env.containsKey( "mainwindow.stylesheet" ) ) {
            String styleSheet = Objects.requireNonNull( ClassLoader.getSystemResource( env.getString( "mainwindow.stylesheet" ) ).toString() );

            log.info( "Using stylesheet: {}", styleSheet );
            scene.getStylesheets().add( styleSheet );
        }

        primaryStage.setTitle( env.getString( "application.name", "Application Title" ) );
        primaryStage.setScene( scene );

        // Maximize the window
        final Screen screen = Screen.getPrimary();
        primaryStage.setX( screen.getVisualBounds().getMinX() );
        primaryStage.setY( screen.getVisualBounds().getMinY() );
        primaryStage.setWidth( screen.getVisualBounds().getWidth() );
        primaryStage.setHeight( screen.getVisualBounds().getHeight() );

        primaryStage.show();
    }
}
