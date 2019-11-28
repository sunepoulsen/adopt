package dk.sunepoulsen.adopt.cli.commandline;

import dk.sunepoulsen.adopt.cli.command.api.CliException;
import dk.sunepoulsen.adopt.cli.command.api.CommandDefinition;
import dk.sunepoulsen.adopt.cli.command.api.CommandExecutor;
import dk.sunepoulsen.adopt.cli.command.help.HelpCommandDefinition;
import dk.sunepoulsen.adopt.core.registry.api.Registry;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.ParseException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class CommandLineInterpreter {
    private Registry registry;

    public CommandLineInterpreter( Registry registry ) {
        this.registry = registry;
    }

    public CommandExecutor parse( List<String> arguments ) throws CliException {
        if( arguments.isEmpty() ) {
            return parse( Collections.singletonList( HelpCommandDefinition.COMMAND_NAME ) );
        }

        CommandDefinition commandDefinition = findCommand( arguments.get( 0 ) );

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

    private CommandDefinition findCommand( String commandName ) throws CliException {
        Optional<CommandDefinition> foundCommand = registry.getInstances( CommandDefinition.class ).stream()
            .filter( commandDefinition -> commandDefinition.name().equals(commandName) )
            .findFirst();

        if( foundCommand.isPresent() ) {
            return foundCommand.get();
        }

        throw new CliException( "The command {} does not exist", commandName );
    }
}
