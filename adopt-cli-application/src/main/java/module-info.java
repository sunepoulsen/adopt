module adopt.cli.application {
    requires adopt.core;

    requires com.google.common;

    requires slf4j.api;
    requires adopt.cli.commandline;
    requires adopt.cli.command.api;

    uses dk.sunepoulsen.adopt.cli.application.api.AdoptCliApplicationModule;
    uses dk.sunepoulsen.adopt.core.registry.api.RegistryModule;

    exports dk.sunepoulsen.adopt.cli.application;
    exports dk.sunepoulsen.adopt.cli.application.api;
}