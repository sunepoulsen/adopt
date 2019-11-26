package dk.sunepoulsen.adopt.core.registry.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RegistryRepository {
    private List<RegistryRepositoryBinding> bindings;

    public RegistryRepository() {
        this.bindings = new ArrayList<>();
    }

    public void add( RegistryRepositoryBinding binding ) {
        this.bindings.add( binding );
    }

    public List<RegistryRepositoryBinding> findBindings( Class<?> bindClass ) {
        if( bindClass == null ) {
            throw new IllegalArgumentException( "bindClass may not be null" );
        }

        return bindings.stream()
            .filter( binding -> this.matchBindClassWithNoQualifier(binding, bindClass) )
            .collect( Collectors.toList());
    }

    public List<RegistryRepositoryBinding> findBindings( Class<?> bindClass, String qualifier ) {
        if( bindClass == null ) {
            throw new IllegalArgumentException( "bindClass may not be null" );
        }
        if( qualifier == null ) {
            throw new IllegalArgumentException( "qualifier may not be null" );
        }
        if( qualifier.isEmpty()) {
            throw new IllegalArgumentException( "qualifier may not be empty" );
        }

        return bindings.stream()
            .filter( binding -> this.matchBindClassWithQualifier(binding, bindClass, qualifier) )
            .collect( Collectors.toList());
    }

    private boolean matchBindClassWithNoQualifier( RegistryRepositoryBinding binding, Class<?> bindClass ) {
        return binding.getBindClass().equals(bindClass) && binding.getQualifier() == null;
    }

    private boolean matchBindClassWithQualifier( RegistryRepositoryBinding binding, Class<?> bindClass, String qualifier ) {
        return binding.getBindClass().equals(bindClass) && qualifier.equals(binding.getQualifier());
    }
}
