package dk.sunepoulsen.adopt.core.registry.api.binder;

import dk.sunepoulsen.adopt.core.registry.api.RegistryException;

public interface RegistryBinder {
    <T> RegistryBinding<T> bind( Class<T> clazz ) throws RegistryException;
    <T> RegistryBinding<T> bind( Class<T> clazz, final String qualifier ) throws RegistryException;
}
