module adopt.cli.commandline {
    requires adopt.cli.command.api;
    requires adopt.core;
    requires com.google.common;
    requires commons.cli;
    requires slf4j.api;

    exports dk.sunepoulsen.adopt.cli.commandline;

    uses dk.sunepoulsen.adopt.cli.command.api.CommandDefinition;

    provides dk.sunepoulsen.adopt.cli.command.api.CommandDefinition with
        dk.sunepoulsen.adopt.cli.command.help.HelpCommandDefinition;
}