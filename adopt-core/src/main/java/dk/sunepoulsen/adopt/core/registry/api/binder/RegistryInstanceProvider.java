package dk.sunepoulsen.adopt.core.registry.api.binder;

import dk.sunepoulsen.adopt.core.registry.api.RegistryException;

public interface RegistryInstanceProvider<T> {
    T getInstance() throws RegistryException;
}
