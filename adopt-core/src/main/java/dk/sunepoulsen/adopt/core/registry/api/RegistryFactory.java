package dk.sunepoulsen.adopt.core.registry.api;

import com.google.common.collect.Lists;
import dk.sunepoulsen.adopt.core.registry.registries.ModuleRegistry;

import java.util.ServiceLoader;

public class RegistryFactory {
    public static Registry createRegistryFromModules() {
        return new ModuleRegistry( Lists.newArrayList( ServiceLoader.load( RegistryModule.class ).iterator() ));
    }
}
