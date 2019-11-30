package dk.sunepoulsen.adopt.core.registry.providers;

import dk.sunepoulsen.adopt.core.registry.api.RegistryException;
import dk.sunepoulsen.adopt.core.registry.api.binder.RegistryInstanceProvider;
import dk.sunepoulsen.adopt.core.registry.repository.RegistryRepository;

public class RepositoryRegistryInstanceProviderInstanceProvider<T extends RegistryInstanceProvider<R>, R> implements RepositoryInstanceProvider<R> {
    private RepositoryClassInstanceProvider<T> providerClassProvider;

    public RepositoryRegistryInstanceProviderInstanceProvider( RegistryRepository registryRepository, Class<T> clazz ) {
        this.providerClassProvider = new RepositoryClassInstanceProvider<>( registryRepository, clazz );
    }

    @Override
    public R getInstance() throws RegistryException {
        T providerClassProviderInstance = providerClassProvider.getInstance();
        return providerClassProviderInstance.getInstance();
    }
}
