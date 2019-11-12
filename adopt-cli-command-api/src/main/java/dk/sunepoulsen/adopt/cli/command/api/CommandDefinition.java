package dk.sunepoulsen.adopt.cli.command.api;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;

public interface CommandDefinition {
    String name();
    String description();
    Options options();

    CommandExecutor createExecutor(CommandLine commandLine) throws CliException;
}
