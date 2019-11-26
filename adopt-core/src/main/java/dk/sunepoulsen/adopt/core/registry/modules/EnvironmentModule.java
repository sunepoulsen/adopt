package dk.sunepoulsen.adopt.core.registry.modules;

import dk.sunepoulsen.adopt.core.environment.Environment;
import dk.sunepoulsen.adopt.core.registry.api.AbstractRegistryModule;

public class EnvironmentModule extends AbstractRegistryModule {
    @Override
    protected void configure() throws RuntimeException {
        bind( Environment.class ).to( Environment.class );
    }
}
