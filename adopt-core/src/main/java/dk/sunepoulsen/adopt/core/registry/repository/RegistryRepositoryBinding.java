package dk.sunepoulsen.adopt.core.registry.repository;

import java.util.Objects;

public class RegistryRepositoryBinding {
    private Class<?> bindClass;
    private String qualifier;
    private RepositoryInstanceFactory instanceFactory;

    public RegistryRepositoryBinding() {
        this( null );
    }

    public RegistryRepositoryBinding( Class<?> bindClass ) {
        this( bindClass, null );
    }

    public RegistryRepositoryBinding( Class<?> bindClass, String qualifier ) {
        this.bindClass = bindClass;
        this.qualifier = qualifier;
    }

    public Class<?> getBindClass() {
        return bindClass;
    }

    public void setBindClass( Class<?> bindClass ) {
        this.bindClass = bindClass;
    }

    public String getQualifier() {
        return qualifier;
    }

    public void setQualifier( String qualifier ) {
        this.qualifier = qualifier;
    }

    public RepositoryInstanceFactory getInstanceFactory() {
        return instanceFactory;
    }

    public void setInstanceFactory( RepositoryInstanceFactory instanceFactory ) {
        this.instanceFactory = instanceFactory;
    }

    @Override
    public boolean equals( Object o ) {
        if( this == o ) {
            return true;
        }
        if( !( o instanceof RegistryRepositoryBinding ) ) {
            return false;
        }
        RegistryRepositoryBinding that = ( RegistryRepositoryBinding ) o;
        return Objects.equals( bindClass, that.bindClass ) &&
            Objects.equals( qualifier, that.qualifier ) &&
            Objects.equals( instanceFactory, that.instanceFactory );
    }

    @Override
    public int hashCode() {
        return Objects.hash( bindClass, qualifier, instanceFactory );
    }

    @Override
    public String toString() {
        return "RegistryRepositoryBinding{" +
            "bindClass=" + bindClass +
            ", qualifier='" + qualifier + '\'' +
            ", instanceFactory=" + instanceFactory +
            '}';
    }
}
