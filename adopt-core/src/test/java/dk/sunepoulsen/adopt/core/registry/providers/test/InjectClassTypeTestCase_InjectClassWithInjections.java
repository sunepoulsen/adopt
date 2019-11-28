package dk.sunepoulsen.adopt.core.registry.providers.test;

import dk.sunepoulsen.adopt.core.registry.api.Inject;

import java.util.Objects;

public class InjectClassTypeTestCase_InjectClassWithInjections {
    private String text;
    private Integer number;

    @Inject
    public InjectClassTypeTestCase_InjectClassWithInjections( String text, Integer number ) {
        this.text = text;
        this.number = number;
    }

    @Override
    public boolean equals( Object o ) {
        if( this == o ) {
            return true;
        }
        if( !( o instanceof InjectClassTypeTestCase_InjectClassWithInjections ) ) {
            return false;
        }
        InjectClassTypeTestCase_InjectClassWithInjections that = ( InjectClassTypeTestCase_InjectClassWithInjections ) o;
        return Objects.equals( text, that.text ) &&
            Objects.equals( number, that.number );
    }

    @Override
    public int hashCode() {
        return Objects.hash( text, number );
    }

    @Override
    public String toString() {
        return "InjectClassTypeTestCase_InjectClassWithInjections{" +
            "text='" + text + '\'' +
            ", number=" + number +
            '}';
    }
}
