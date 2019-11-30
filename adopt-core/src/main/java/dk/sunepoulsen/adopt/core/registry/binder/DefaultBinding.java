package dk.sunepoulsen.adopt.core.registry.binder;

import dk.sunepoulsen.adopt.core.registry.api.RegistryException;
import dk.sunepoulsen.adopt.core.registry.api.binder.RegistryBinding;
import dk.sunepoulsen.adopt.core.registry.api.binder.RegistryInstanceProvider;
import dk.sunepoulsen.adopt.core.registry.api.binder.RegistryInstanceScope;
import dk.sunepoulsen.adopt.core.registry.providers.RepositoryClassInstanceProvider;
import dk.sunepoulsen.adopt.core.registry.providers.RepositoryRegistryInstanceProviderInstanceProvider;
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
        to(RegistryInstanceScope.SINGLE, clazz);
    }

    @Override
    public void to( RegistryInstanceScope scope, Class<? extends T> clazz ) throws RegistryException {
        repositoryBinding.setInstanceFactory( new RepositoryInstanceFactory( scope, new RepositoryClassInstanceProvider<>( registryRepository, clazz ) ) );
        this.registryRepository.add(repositoryBinding);
    }

    @Override
    public void toInstance( T value ) throws RegistryException {
        toInstance(RegistryInstanceScope.SINGLE, value);
    }

    @Override
    public void toInstance( RegistryInstanceScope scope, T value ) throws RegistryException {
        repositoryBinding.setInstanceFactory( new RepositoryInstanceFactory( scope, new RepositoryValueInstanceProvider<>( value ) ) );
        this.registryRepository.add(repositoryBinding);
    }

    @Override
    public void toProvider( Class<? extends RegistryInstanceProvider<T>> providerClass )  throws RegistryException {
        toProvider(RegistryInstanceScope.SINGLE, providerClass);
    }

    @Override
    public void toProvider( RegistryInstanceScope scope, Class<? extends RegistryInstanceProvider<T>> providerClass )  throws RegistryException {
        repositoryBinding.setInstanceFactory( new RepositoryInstanceFactory( scope, new RepositoryRegistryInstanceProviderInstanceProvider<>( registryRepository, providerClass ) ) );
        this.registryRepository.add(repositoryBinding);
    }
}
