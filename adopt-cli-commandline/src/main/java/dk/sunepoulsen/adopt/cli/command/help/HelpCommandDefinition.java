package dk.sunepoulsen.adopt.cli.command.help;

import dk.sunepoulsen.adopt.cli.command.api.CliException;
import dk.sunepoulsen.adopt.cli.command.api.CommandDefinition;
import dk.sunepoulsen.adopt.cli.command.api.CommandExecutor;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;

public class HelpCommandDefinition implements CommandDefinition {
    @Override
    public String name() {
        return "help";
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
            return new HelpCommandExecutor();
        }

        if( commandLine.getArgList().size() == 1 ) {
            return new HelpCommandExecutor( commandLine.getArgList().get( 0 ) );
        }

        throw new CliException( "The command 'help' only accepts one argument" );
    }
}
