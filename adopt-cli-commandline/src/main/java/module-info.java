module adopt.cli.commandline {
    requires adopt.cli.command.api;
    requires adopt.core;
    requires com.google.common;
    requires commons.cli;
    requires slf4j.api;

    exports dk.sunepoulsen.adopt.cli.commandline;
    exports dk.sunepoulsen.adopt.cli.command.help to adopt.core;

    uses dk.sunepoulsen.adopt.core.registry.api.RegistryModule;

    provides dk.sunepoulsen.adopt.core.registry.api.RegistryModule with
        dk.sunepoulsen.adopt.cli.commandline.CommandLineModule;
}