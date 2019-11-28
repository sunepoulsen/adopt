package dk.sunepoulsen.adopt.core.registry.providers.test;

import dk.sunepoulsen.adopt.core.registry.api.Inject;

import java.util.Objects;

public class InjectSimpleTypesTestCase {
    private String text;
    private Integer number;

    @Inject
    public InjectSimpleTypesTestCase( String text, Integer number ) {
        this.text = text;
        this.number = number;
    }

    public String getText() {
        return text;
    }

    public Integer getNumber() {
        return number;
    }

    @Override
    public boolean equals( Object o ) {
        if( this == o ) {
            return true;
        }
        if( !( o instanceof InjectSimpleTypesTestCase ) ) {
            return false;
        }
        InjectSimpleTypesTestCase that = ( InjectSimpleTypesTestCase ) o;
        return Objects.equals( text, that.text ) &&
            Objects.equals( number, that.number );
    }

    @Override
    public int hashCode() {
        return Objects.hash( text, number );
    }

    @Override
    public String toString() {
        return "InjectSimpleTypesTestCase{" +
            "text='" + text + '\'' +
            ", number=" + number +
            '}';
    }
}
