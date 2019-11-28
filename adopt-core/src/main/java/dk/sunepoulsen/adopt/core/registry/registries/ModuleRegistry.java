package dk.sunepoulsen.adopt.core.registry.registries;

import dk.sunepoulsen.adopt.core.registry.api.Registry;
import dk.sunepoulsen.adopt.core.registry.api.RegistryModule;
import dk.sunepoulsen.adopt.core.registry.binder.DefaultBinder;
import dk.sunepoulsen.adopt.core.registry.repository.RegistryRepository;

import java.util.List;

public class ModuleRegistry extends RegistryReader {
    private List<RegistryModule> modules;

    public ModuleRegistry( List<RegistryModule> modules ) {
        this( modules, new RegistryRepository() );
    }

    ModuleRegistry( List<RegistryModule> modules, RegistryRepository registryRepository ) {
        super(registryRepository);
        this.modules = modules;

        registerModules();
    }

    private void registerModules() {
        DefaultBinder binder = new DefaultBinder( getRegistryRepository() );
        binder.bind( Registry.class ).toInstance( this );

        this.modules.forEach( registryModule -> registryModule.configure( binder ) );
    }
}
