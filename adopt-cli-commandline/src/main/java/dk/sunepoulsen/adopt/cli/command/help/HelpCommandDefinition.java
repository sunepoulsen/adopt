package dk.sunepoulsen.adopt.cli.command.help;

import dk.sunepoulsen.adopt.cli.command.api.CliException;
import dk.sunepoulsen.adopt.cli.command.api.CommandDefinition;
import dk.sunepoulsen.adopt.cli.command.api.CommandExecutor;
import dk.sunepoulsen.adopt.core.registry.api.Inject;
import dk.sunepoulsen.adopt.core.registry.api.Registry;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;

public class HelpCommandDefinition implements CommandDefinition {
    public static final String COMMAND_NAME = "help";

    private Registry registry;

    @Inject
    public HelpCommandDefinition( Registry registry ) {
        this.registry = registry;
    }

    @Override
    public String name() {
        return COMMAND_NAME;
    }

    @Override
    public String description() {
        return "Describe one or all commands";
    }

    @Override
    public Options options() {
        return new Options();
    }

    @Override
    public CommandExecutor createExecutor( CommandLine commandLine ) throws CliException {
        if( commandLine.getArgList().isEmpty() ) {
            return new HelpCommandExecutor( registry.getInstances( CommandDefinition.class ) );
        }

        if( commandLine.getArgList().size() == 1 ) {
            return new HelpCommandExecutor( registry.getInstances( CommandDefinition.class ), commandLine.getArgList().get( 0 ) );
        }

        throw new CliException( "The command 'help' only accepts one argument" );
    }
}
