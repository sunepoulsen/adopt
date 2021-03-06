package dk.sunepoulsen.adopt.cli.commandline;

import dk.sunepoulsen.adopt.cli.command.api.CliException;
import dk.sunepoulsen.adopt.cli.command.api.CommandDefinition;
import dk.sunepoulsen.adopt.cli.command.api.CommandExecutor;
import dk.sunepoulsen.adopt.cli.command.help.HelpCommandDefinition;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.ParseException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class CommandLineInterpreter {
    private CommandRegistry commandRegistry;

    public CommandLineInterpreter() {
        this.commandRegistry = new CommandRegistry();
    }

    public CommandExecutor parse( List<String> arguments ) throws CliException {
        if( arguments.isEmpty() ) {
            return parse( Collections.singletonList( new HelpCommandDefinition().name() ) );
        }

        Optional<CommandDefinition> foundCommand = commandRegistry.findCommand( arguments.get( 0 ) );
        if( foundCommand.isEmpty() ) {
            throw new CliException( "The command '" + arguments.get( 0 ) + "' does not exist" );
        }
        CommandDefinition commandDefinition = foundCommand.get();

        List<String> commandArgs = arguments.subList( 1, arguments.size() );
        CommandLine commandLine;
        try {
            commandLine = parseCommandArguments(commandDefinition, commandArgs);
        }
        catch( ParseException ex ) {
            throw new CliException(ex.getMessage(), ex);
        }

        return commandDefinition.createExecutor( commandLine );
    }

    private CommandLine parseCommandArguments(CommandDefinition commandDefinition, List<String> args) throws ParseException {
        String[] commandArgs = new String[ args.size() ];
        args.toArray( commandArgs );

        DefaultParser parser = new DefaultParser();
        return parser.parse( commandDefinition.options(), commandArgs );
    }

}
