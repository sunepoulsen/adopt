package dk.sunepoulsen.adopt.core.registry.api;

import dk.sunepoulsen.adopt.core.registry.binder.DefaultBinder;
import dk.sunepoulsen.adopt.core.registry.repository.RegistryRepository;
import dk.sunepoulsen.adopt.core.registry.repository.RegistryRepositoryBinding;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Registry {
    private List<RegistryModule> modules;
    private RegistryRepository registryRepository;

    public Registry() {
        this( Collections.emptyList(), new RegistryRepository() );
    }

    public Registry( List<RegistryModule> modules ) {
        this( modules, new RegistryRepository() );
    }

    Registry( List<RegistryModule> modules, RegistryRepository registryRepository ) {
        this.modules = modules;
        this.registryRepository = registryRepository;

        registerModules();
    }

    public <T> T getInstance( Class<T> clazz ) throws RegistryException {
        List<RegistryRepositoryBinding> bindings = findBindings( clazz );

        return clazz.cast(bindings.get( 0 ).getInstanceFactory().getInstance());
    }

    public <T> T getInstance( Class<T> clazz, String qualifier ) throws RegistryException {
        List<RegistryRepositoryBinding> bindings = findBindings( clazz, qualifier );

        return clazz.cast(bindings.get( 0 ).getInstanceFactory().getInstance());
    }

    public <T> List<T> getInstances( Class<T> clazz ) {
        List<RegistryRepositoryBinding> bindings = findBindings( clazz );

        return bindings.stream()
            .map(binding -> clazz.cast(binding.getInstanceFactory().getInstance()))
            .collect( Collectors.toList());
    }

    public <T> List<T> getInstances( Class<T> clazz, String qualifier ) {
        List<RegistryRepositoryBinding> bindings = findBindings( clazz, qualifier );

        return bindings.stream()
            .map(binding -> clazz.cast(binding.getInstanceFactory().getInstance()))
            .collect( Collectors.toList());
    }

    private void registerModules() {
        this.modules.forEach( registryModule -> registryModule.configure( new DefaultBinder(registryRepository) ) );
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
