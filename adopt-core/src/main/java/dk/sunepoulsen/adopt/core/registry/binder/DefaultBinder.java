package dk.sunepoulsen.adopt.core.registry.binder;

import dk.sunepoulsen.adopt.core.registry.api.RegistryException;
import dk.sunepoulsen.adopt.core.registry.api.binder.RegistryBinder;
import dk.sunepoulsen.adopt.core.registry.api.binder.RegistryBinding;
import dk.sunepoulsen.adopt.core.registry.repository.RegistryRepository;
import dk.sunepoulsen.adopt.core.registry.repository.RegistryRepositoryBinding;

public class DefaultBinder implements RegistryBinder {
    private RegistryRepository registryRepository;

    public DefaultBinder( RegistryRepository registryRepository ) {
        this.registryRepository = registryRepository;
    }

    @Override
    public <T> RegistryBinding<T> bind( Class<T> clazz ) throws RegistryException {
        return bind( clazz, null);
    }

    @Override
    public <T> RegistryBinding<T> bind( Class<T> clazz, String qualifier ) throws RegistryException {
        RegistryRepositoryBinding binding = new RegistryRepositoryBinding();
        binding.setBindClass( clazz );
        binding.setQualifier( qualifier );

        return new DefaultBinding<>( registryRepository, binding );
    }
}
