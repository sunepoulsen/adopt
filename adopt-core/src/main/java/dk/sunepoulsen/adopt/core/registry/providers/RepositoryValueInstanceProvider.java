package dk.sunepoulsen.adopt.core.registry.providers;

import dk.sunepoulsen.adopt.core.registry.api.RegistryException;

public class RepositoryValueInstanceProvider<T> implements RepositoryInstanceProvider<T> {
    private T value;

    public RepositoryValueInstanceProvider( T value ) {
        this.value = value;
    }

    @Override
    public T getInstance() throws RegistryException {
        return value;
    }
}
