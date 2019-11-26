package dk.sunepoulsen.adopt.core.registry.api;

import dk.sunepoulsen.adopt.core.registry.api.binder.RegistryBinder;

public interface RegistryModule {
    void configure( RegistryBinder binder ) throws RegistryException;
}
