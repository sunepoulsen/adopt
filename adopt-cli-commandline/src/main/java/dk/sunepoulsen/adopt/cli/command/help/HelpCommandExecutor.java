package dk.sunepoulsen.adopt.cli.command.help;

import dk.sunepoulsen.adopt.cli.command.api.CliException;
import dk.sunepoulsen.adopt.cli.command.api.CommandDefinition;
import dk.sunepoulsen.adopt.cli.command.api.CommandExecutor;
import dk.sunepoulsen.adopt.core.environment.Environment;
import dk.sunepoulsen.adopt.core.environment.EnvironmentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class HelpCommandExecutor implements CommandExecutor {
    private static Logger consoleLogger = LoggerFactory.getLogger( CommandExecutor.CONSOLE_LOGGER_NAME );

    private List<CommandDefinition> commands;
    private String commandName;

    public HelpCommandExecutor(List<CommandDefinition> commands) {
        this( commands, null );
    }

    public HelpCommandExecutor( List<CommandDefinition> commands, String commandName ) {
        this.commands = commands;
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

            commands.forEach( command -> consoleLogger.info( "{} - {}", command.name(), command.description() ) );
        }
        catch( EnvironmentException ex ) {
            throw new CliException( ex.getMessage(), ex );
        }
    }

    private void printCommandHelp() throws CliException {
        throw new CliException( "Not implemented yet!" );
    }
}
