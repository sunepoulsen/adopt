package dk.sunepoulsen.adopt.cli.commandline;

import dk.sunepoulsen.adopt.cli.command.api.CommandDefinition;
import dk.sunepoulsen.adopt.cli.command.help.HelpCommandDefinition;
import dk.sunepoulsen.adopt.core.registry.api.AbstractRegistryModule;
import dk.sunepoulsen.adopt.core.registry.api.RegistryException;

public class CommandLineModule extends AbstractRegistryModule {
    @Override
    protected void configure() throws RegistryException {
        bind( CommandDefinition.class ).to( HelpCommandDefinition.class );
    }
}
