package dk.sunepoulsen.adopt.cli.application;

import dk.sunepoulsen.adopt.core.environment.Environment;
import dk.sunepoulsen.adopt.core.environment.EnvironmentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

public class AdoptCliApplication {
    private static Logger log = LoggerFactory.getLogger( AdoptCliApplication.class );
    private Environment env;
    private List<String> arguments;

    public AdoptCliApplication() {
        this.env = new Environment();
    }

    public void start() {
    }

    public void stop() {
    }

    /**
     * Main launch function that will start the Cli application.
     *
     * @param args Arguments from the command line.
     */
    protected static void launchApplication( Class<? extends AdoptCliApplication> clazz, String[] args ) {
        AdoptCliApplication application;

        try {
            application = clazz.getDeclaredConstructor().newInstance();
            application.launch(args);
        }
        catch( InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException ex ) {
            log.error("Unable to construct Application object.", ex);
            System.exit( -1 );
        }
    }

    private void launch( String[] args ) {
        arguments = Arrays.asList(args);

        String appName = "unknown";
        String appVersion = "unknown";

        try {
            appName = env.getString( "application.name");
            appVersion = env.getString( "application.version" );
        }
        catch( EnvironmentException ex ) {
            log.debug("Unable to read property from environment", ex );
        }


        log.info( "Starting {} version {}", appName, appVersion );
        start();

        stop();
        log.info( "Stopped {} version {}", appName, appVersion );
    }
}
