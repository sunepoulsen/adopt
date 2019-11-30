package dk.sunepoulsen.adopt.core.registry.api.binder;

import dk.sunepoulsen.adopt.core.registry.api.RegistryException;

public interface RegistryBinding<T> {
    void to( Class<? extends T> clazz ) throws RegistryException;
    void to( RegistryInstanceScope scope, Class<? extends T> clazz ) throws RegistryException;
    void toInstance( T value ) throws RegistryException;
    void toInstance( RegistryInstanceScope scope, T value ) throws RegistryException;
    void toProvider( Class<? extends RegistryInstanceProvider<T>> providerClass ) throws RegistryException;
    void toProvider( RegistryInstanceScope scope, Class<? extends RegistryInstanceProvider<T>> providerClass ) throws RegistryException;
}
