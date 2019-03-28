package dk.sunepoulsen.adopt.javafx.application;

import dk.sunepoulsen.adopt.javafx.mainwindow.MainWindow;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * Application class for an Adopt JavaFX Application.
 */
public class AdoptJavaFXApplication extends Application {
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
        MainWindow mainWindow = new MainWindow();

        Scene scene = new Scene( mainWindow );

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
