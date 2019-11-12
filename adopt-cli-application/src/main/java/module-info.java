module adopt.cli.application {
    requires adopt.core;

    requires com.google.common;

    requires slf4j.api;
    requires adopt.cli.commandline;
    requires adopt.cli.command.api;

    exports dk.sunepoulsen.adopt.cli.application;
}