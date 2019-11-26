package dk.sunepoulsen.adopt.core.registry.repository;

import dk.sunepoulsen.adopt.core.registry.api.binder.RegistryInstanceScope;
import dk.sunepoulsen.adopt.core.registry.providers.RepositoryInstanceProvider;

public class RepositoryInstanceFactory {
    private RegistryInstanceScope scope;
    private RepositoryInstanceProvider<?> provider;
    private Object instance;

    public RepositoryInstanceFactory( RegistryInstanceScope scope, RepositoryInstanceProvider<?> provider ) {
        this.scope = scope;
        this.provider = provider;
    }

    public Object getInstance() {
        if( scope.equals(RegistryInstanceScope.MULTIPLE)) {
            return provider.getInstance();
        }

        if( instance == null ) {
            instance = provider.getInstance();
        }

        return instance;
    }
}
