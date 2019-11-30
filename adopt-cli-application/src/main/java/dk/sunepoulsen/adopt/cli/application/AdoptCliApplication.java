package dk.sunepoulsen.adopt.cli.application;

import dk.sunepoulsen.adopt.cli.command.api.CliException;
import dk.sunepoulsen.adopt.cli.command.api.CommandExecutor;
import dk.sunepoulsen.adopt.cli.commandline.CommandLineInterpreter;
import dk.sunepoulsen.adopt.core.environment.Environment;
import dk.sunepoulsen.adopt.core.environment.EnvironmentException;
import dk.sunepoulsen.adopt.core.registry.api.Registry;
import dk.sunepoulsen.adopt.core.registry.api.RegistryFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

public class AdoptCliApplication {
    public static final String CONSOLE_LOGGER_NAME = "adopt.cli.console.output.logger";

    private static Logger log = LoggerFactory.getLogger( AdoptCliApplication.class );
    private static Logger consoleLogger = LoggerFactory.getLogger( AdoptCliApplication.CONSOLE_LOGGER_NAME );

    private List<String> arguments;
    private Registry registry;

    public AdoptCliApplication() {
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

    protected Registry getRegistry() {
        return this.registry;
    }

    private void launch( String[] args ) {
        arguments = Arrays.asList(args);

        registry = RegistryFactory.createRegistryFromModules();
        Environment environment = registry.getInstance( Environment.class );

        String appName = "unknown";
        String appVersion = "unknown";

        try {
            appName = environment.getString( "application.name");
            appVersion = environment.getString( "application.version" );
        }
        catch( EnvironmentException ex ) {
            log.debug("Unable to read property from environment", ex );
        }

        log.info( "Starting {} version {}", appName, appVersion );
        environment.logProperties();
        start();

        CommandLineInterpreter commandLineInterpreter = new CommandLineInterpreter(registry);
        try {
            CommandExecutor commandExecutor = commandLineInterpreter.parse( arguments );

            commandExecutor.validateArguments();
            commandExecutor.performAction();
        }
        catch( CliException ex ) {
            consoleLogger.error( "Unable to execute command: {}", ex.getMessage());
            log.error( "Unable to execute command: {}", ex.getMessage(), ex);
        }

        stop();
        log.info( "Stopped {} version {}", appName, appVersion );
    }
}
