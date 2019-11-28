package dk.sunepoulsen.adopt.core.registry.providers.test;

import dk.sunepoulsen.adopt.core.registry.api.Inject;

import java.util.Objects;

public class InjectClassTypeTestCase {
    private InjectClassTypeTestCase_InjectClassWithInjections injected;

    @Inject
    public InjectClassTypeTestCase( InjectClassTypeTestCase_InjectClassWithInjections injected ) {
        this.injected = injected;
    }

    @Override
    public boolean equals( Object o ) {
        if( this == o ) {
            return true;
        }
        if( !( o instanceof InjectClassTypeTestCase ) ) {
            return false;
        }
        InjectClassTypeTestCase that = ( InjectClassTypeTestCase ) o;
        return Objects.equals( injected, that.injected );
    }

    @Override
    public int hashCode() {
        return Objects.hash( injected );
    }

    @Override
    public String toString() {
        return "InjectClassTypeTestCase{" +
            "injected=" + injected +
            '}';
    }
}
