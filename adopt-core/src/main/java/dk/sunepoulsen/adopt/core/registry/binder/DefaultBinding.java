package dk.sunepoulsen.adopt.core.registry.binder;

import dk.sunepoulsen.adopt.core.registry.api.RegistryException;
import dk.sunepoulsen.adopt.core.registry.api.binder.RegistryBinding;
import dk.sunepoulsen.adopt.core.registry.api.binder.RegistryInstanceScope;
import dk.sunepoulsen.adopt.core.registry.providers.RepositoryClassInstanceProvider;
import dk.sunepoulsen.adopt.core.registry.providers.RepositoryValueInstanceProvider;
import dk.sunepoulsen.adopt.core.registry.repository.RegistryRepository;
import dk.sunepoulsen.adopt.core.registry.repository.RegistryRepositoryBinding;
import dk.sunepoulsen.adopt.core.registry.repository.RepositoryInstanceFactory;

public class DefaultBinding<T> implements RegistryBinding<T> {
    private RegistryRepository registryRepository;
    private RegistryRepositoryBinding repositoryBinding;

    public DefaultBinding( RegistryRepository registryRepository, RegistryRepositoryBinding repositoryBinding ) {
        this.registryRepository = registryRepository;
        this.repositoryBinding = repositoryBinding;
    }

    @Override
    public void to( Class<? extends T> clazz ) throws RegistryException {
        repositoryBinding.setInstanceFactory( new RepositoryInstanceFactory( RegistryInstanceScope.SINGLE, new RepositoryClassInstanceProvider<>( clazz ) ) );
        this.registryRepository.add(repositoryBinding);
    }

    @Override
    public void toInstance( T value ) throws RegistryException {
        repositoryBinding.setInstanceFactory( new RepositoryInstanceFactory( RegistryInstanceScope.SINGLE, new RepositoryValueInstanceProvider<>( value ) ) );
        this.registryRepository.add(repositoryBinding);
    }
}
