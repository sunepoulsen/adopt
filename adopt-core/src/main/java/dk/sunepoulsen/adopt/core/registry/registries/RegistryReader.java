package dk.sunepoulsen.adopt.core.registry.registries;

import dk.sunepoulsen.adopt.core.registry.api.Registry;
import dk.sunepoulsen.adopt.core.registry.api.RegistryException;
import dk.sunepoulsen.adopt.core.registry.repository.RegistryRepository;
import dk.sunepoulsen.adopt.core.registry.repository.RegistryRepositoryBinding;

import java.util.List;
import java.util.stream.Collectors;

public class RegistryReader implements Registry {
    private RegistryRepository registryRepository;

    public RegistryReader( RegistryRepository registryRepository ) {
        this.registryRepository = registryRepository;
    }

    public RegistryRepository getRegistryRepository() {
        return registryRepository;
    }

    @Override
    public <T> T getInstance( Class<T> clazz ) throws RegistryException {
        List<RegistryRepositoryBinding> bindings = findBindings( clazz );

        if( bindings.size() != 1 ) {
            throw new RegistryException( "Found more than one instance of " + clazz.getName() + " in registry" );
        }

        return clazz.cast(bindings.get( 0 ).getInstanceFactory().getInstance());
    }

    @Override
    public <T> T getInstance( Class<T> clazz, String qualifier ) throws RegistryException {
        List<RegistryRepositoryBinding> bindings = findBindings( clazz, qualifier );

        if( bindings.size() != 1 ) {
            throw new RegistryException( "Found more than one instance of " + clazz.getName() + " in registry" );
        }

        return clazz.cast(bindings.get( 0 ).getInstanceFactory().getInstance());
    }

    @Override
    public <T> List<T> getInstances( Class<T> clazz ) throws RegistryException {
        List<RegistryRepositoryBinding> bindings = findBindings( clazz );

        return bindings.stream()
            .map(binding -> clazz.cast(binding.getInstanceFactory().getInstance()))
            .collect( Collectors.toList());
    }

    @Override
    public <T> List<T> getInstances( Class<T> clazz, String qualifier ) throws RegistryException {
        List<RegistryRepositoryBinding> bindings = findBindings( clazz, qualifier );

        return bindings.stream()
            .map(binding -> clazz.cast(binding.getInstanceFactory().getInstance()))
            .collect( Collectors.toList());
    }

    private <T> List<RegistryRepositoryBinding> findBindings( Class<T> clazz ) throws RegistryException {
        List<RegistryRepositoryBinding> bindings = registryRepository.findBindings( clazz );
        if( bindings.isEmpty()) {
            throw new RegistryException( "No bindings found for class " + clazz.getName() );
        }

        return bindings;
    }

    private <T> List<RegistryRepositoryBinding> findBindings( Class<T> clazz, String qualifier ) throws RegistryException {
        List<RegistryRepositoryBinding> bindings = registryRepository.findBindings( clazz, qualifier );
        if( bindings.isEmpty()) {
            throw new RegistryException( "No bindings found for class " + clazz.getName() + " with qualifier: " + qualifier);
        }

        return bindings;
    }
}
