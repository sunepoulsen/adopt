package dk.sunepoulsen.adopt.examples.topcomponents;

import dk.sunepoulsen.adopt.javafx.application.AdoptJavaFXApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;

/**
 * Application class.
 */
public class TopComponentsApplication extends AdoptJavaFXApplication {
    private static Logger log = LoggerFactory.getLogger( TopComponentsApplication.class );

    public TopComponentsApplication() {
        super();

        this.applicationTitle = "TopComponents Example";
        this.mainWindowFXMLUrl = TopComponentsApplication.class.getResource( "/mainwindow.fxml" );
    }

    /**
     * Main function.
     *
     * @param args Arguments from the command line.
     */
    public static void main( String[] args ) {
        Properties settings = new Properties();
        try {
            settings.load( TopComponentsApplication.class.getResourceAsStream( "/application.properties" ) );
        }
        catch( IOException ex ) {
            log.error( "Unable to load resource /application.properties", ex );
        }

        log.info( "Starting {} version {}", settings.getProperty( "application.name", "unknown" ), settings.getProperty( "application.version", "unknown" ) );
        launchApplication( TopComponentsApplication.class, args );
    }
}
