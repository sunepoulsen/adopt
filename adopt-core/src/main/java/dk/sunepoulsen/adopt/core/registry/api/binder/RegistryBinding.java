package dk.sunepoulsen.adopt.core.registry.api.binder;

import dk.sunepoulsen.adopt.core.registry.api.RegistryException;

public interface RegistryBinding<T> {
    void to( Class<? extends T> clazz ) throws RegistryException;
    void toInstance( T value ) throws RegistryException;
}
