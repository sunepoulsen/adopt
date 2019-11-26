package dk.sunepoulsen.adopt.core.registry.api;

import dk.sunepoulsen.adopt.core.registry.api.binder.RegistryBinder;
import dk.sunepoulsen.adopt.core.registry.api.binder.RegistryBinding;

public abstract class AbstractRegistryModule implements RegistryModule {
    private RegistryBinder binder;

    @Override
    public void configure( RegistryBinder binder ) throws RegistryException {
        this.binder = binder;
        configure();
    }

    protected abstract void configure() throws RegistryException;

    protected <T> RegistryBinding<T> bind( Class<T> clazz ) throws RegistryException {
        return this.binder.bind( clazz );
    }

    protected <T> RegistryBinding<T> bind( Class<T> clazz, final String qualifier ) throws RegistryException {
        return this.binder.bind( clazz, qualifier );
    }
}
