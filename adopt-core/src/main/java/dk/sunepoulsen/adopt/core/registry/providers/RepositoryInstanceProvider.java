package dk.sunepoulsen.adopt.core.registry.providers;

import dk.sunepoulsen.adopt.core.registry.api.RegistryException;

public interface RepositoryInstanceProvider<T> {
    T getInstance() throws RegistryException;
}
