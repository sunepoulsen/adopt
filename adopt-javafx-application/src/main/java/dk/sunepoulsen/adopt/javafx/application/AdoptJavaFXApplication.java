package dk.sunepoulsen.adopt.javafx.application;

import dk.sunepoulsen.adopt.core.environment.Environment;
import dk.sunepoulsen.adopt.javafx.application.handlers.ApplicationModuleHandler;
import dk.sunepoulsen.adopt.javafx.application.startup.LocaleStartup;
import dk.sunepoulsen.adopt.javafx.application.startup.SceneStartup;
import javafx.application.Application;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Application class for an Adopt JavaFX Application.
 */
public class AdoptJavaFXApplication extends Application {
    private static Logger log = LoggerFactory.getLogger( AdoptJavaFXApplication.class );
    private Environment env;
    private ApplicationModuleHandler applicationModuleHandler;

    public AdoptJavaFXApplication() {
        this.env = new Environment();
        this.applicationModuleHandler = new ApplicationModuleHandler();
    }

    /**
     * Main launch function that will start the JavaFX application.
     *
     * @param args Arguments from the command line.
     */
    protected static void launchApplication( Class<? extends Application> clazz, String[] args ) {
        launch( clazz, args );
    }

    @Override
    public void start( Stage primaryStage ) throws Exception {
        log.debug( "Creating primary stage" );

        env.logProperties();
        LocaleStartup.initializeLocale( env );

        this.applicationModuleHandler.modulesStartup();

        SceneStartup sceneStartup = new SceneStartup( env );
        sceneStartup.loadMainWindow();
        sceneStartup.configureWindowModes();
        sceneStartup.createStartupTopComponents();
        sceneStartup.createAndShowScene( primaryStage );
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        this.applicationModuleHandler.modulesShutdown();
    }
}
