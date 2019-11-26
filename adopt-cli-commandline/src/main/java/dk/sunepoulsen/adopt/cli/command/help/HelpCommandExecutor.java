package dk.sunepoulsen.adopt.cli.command.help;

import com.google.common.collect.Lists;
import dk.sunepoulsen.adopt.cli.command.api.CliException;
import dk.sunepoulsen.adopt.cli.command.api.CommandDefinition;
import dk.sunepoulsen.adopt.cli.command.api.CommandExecutor;
import dk.sunepoulsen.adopt.core.environment.Environment;
import dk.sunepoulsen.adopt.core.environment.EnvironmentException;
import dk.sunepoulsen.adopt.core.registry.api.Registry;
import dk.sunepoulsen.adopt.core.registry.api.RegistryModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ServiceLoader;

public class HelpCommandExecutor implements CommandExecutor {
    private static Logger consoleLogger = LoggerFactory.getLogger( CommandExecutor.CONSOLE_LOGGER_NAME );

    private String commandName;

    public HelpCommandExecutor() {
        this( null );
    }

    public HelpCommandExecutor( String commandName ) {
        this.commandName = commandName;
    }

    @Override
    public void validateArguments() throws CliException {
    }

    @Override
    public void performAction() throws CliException {
        if( commandName == null ) {
            printHelp();
        }
        else {
            printCommandHelp();
        }
    }

    private void printHelp() throws CliException {
        Environment env = new Environment();

        try {
            consoleLogger.info( "Help to {}", env.getString( "application.name" ) );
            consoleLogger.info( "" );
            consoleLogger.info( "Commands:" );
            consoleLogger.info( "" );

            Registry registry = new Registry( Lists.newArrayList( ServiceLoader.load( RegistryModule.class ).iterator() ));

            registry.getInstances( CommandDefinition.class )
                .forEach( command -> consoleLogger.info( "{} - {}", command.name(), command.description() ) );
        }
        catch( EnvironmentException ex ) {
            throw new CliException( ex.getMessage(), ex );
        }
    }

    private void printCommandHelp() throws CliException {
        throw new CliException( "Not implemented yet!" );
    }
}
