package dk.sunepoulsen.adopt.cli.command.api;

public interface CommandExecutor {
    String CONSOLE_LOGGER_NAME = "adopt.cli.console.output.logger";

    void validateArguments() throws CliException;
    void performAction() throws CliException;
}
