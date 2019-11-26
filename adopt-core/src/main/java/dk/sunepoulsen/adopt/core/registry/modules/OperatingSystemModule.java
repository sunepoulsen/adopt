package dk.sunepoulsen.adopt.core.registry.modules;

import dk.sunepoulsen.adopt.core.os.OperatingSystemFactory;
import dk.sunepoulsen.adopt.core.os.api.OperatingSystem;
import dk.sunepoulsen.adopt.core.registry.api.AbstractRegistryModule;
import dk.sunepoulsen.adopt.core.registry.api.RegistryException;

import java.io.IOException;

public class OperatingSystemModule extends AbstractRegistryModule {
    @Override
    protected void configure() throws RegistryException {
        try {
            bind( OperatingSystem.class ).toInstance( OperatingSystemFactory.getInstance() );
        }
        catch( IOException ex ) {
            throw new RegistryException( ex.getMessage(), ex);
        }
    }
}
