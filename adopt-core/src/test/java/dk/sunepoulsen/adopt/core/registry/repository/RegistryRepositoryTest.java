package dk.sunepoulsen.adopt.core.registry.repository;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class RegistryRepositoryTest {
    @Test( expected = IllegalArgumentException.class )
    public void findBindings_BindClass_ClassIsNull() {
        RegistryRepository repository = new RegistryRepository();

        repository.findBindings( null );
    }

    @Test
    public void findBindings_BindClass_EmptyBindings_NotFound() {
        RegistryRepository repository = new RegistryRepository();

        List<RegistryRepositoryBinding> actual = repository.findBindings( InputStream.class );
        assertThat( actual, equalTo( Collections.emptyList() ) );
    }

    @Test
    public void findBindings_BindClass_HasBindings_NotFound() {
        RegistryRepository repository = new RegistryRepository();
        repository.add( new RegistryRepositoryBinding( String.class ) );

        List<RegistryRepositoryBinding> actual = repository.findBindings( InputStream.class );
        assertThat( actual, equalTo( Collections.emptyList() ) );
    }

    @Test
    public void findBindings_BindClass_HasBindings_NotFound_ButHasInheritanceMatch() {
        RegistryRepository repository = new RegistryRepository();
        repository.add( new RegistryRepositoryBinding( ByteArrayInputStream.class ) );

        List<RegistryRepositoryBinding> actual = repository.findBindings( InputStream.class );
        assertThat( actual, equalTo( Collections.emptyList() ) );
    }

    @Test
    public void findBindings_BindClass_HasBindings_NotFound_MatchButHasQualifier() {
        RegistryRepository repository = new RegistryRepository();
        repository.add( new RegistryRepositoryBinding( ByteArrayInputStream.class, "qualifier" ) );

        List<RegistryRepositoryBinding> actual = repository.findBindings( InputStream.class );
        assertThat( actual, equalTo( Collections.emptyList() ) );
    }

    @Test
    public void findBindings_BindClass_HasBindings_Found() {
        RegistryRepository repository = new RegistryRepository();
        repository.add( new RegistryRepositoryBinding( InputStream.class ) );

        List<RegistryRepositoryBinding> actual = repository.findBindings( InputStream.class );
        assertThat( actual, equalTo( Collections.singletonList( new RegistryRepositoryBinding( InputStream.class ) ) ) );
    }

    @Test
    public void findBindings_BindClass_HasBindings_FoundMultiple() {
        RegistryRepository repository = new RegistryRepository();
        repository.add( new RegistryRepositoryBinding( InputStream.class ) );
        repository.add( new RegistryRepositoryBinding( InputStream.class ) );
        repository.add( new RegistryRepositoryBinding( InputStream.class ) );

        List<RegistryRepositoryBinding> actual = repository.findBindings( InputStream.class );
        assertThat( actual, equalTo( Arrays.asList(
            new RegistryRepositoryBinding( InputStream.class ),
            new RegistryRepositoryBinding( InputStream.class ),
            new RegistryRepositoryBinding( InputStream.class ) ) )
        );
    }

    @Test( expected = IllegalArgumentException.class )
    public void findBindings_BindClassAndQualifier_ClassIsNull() {
        RegistryRepository repository = new RegistryRepository();

        repository.findBindings( null, "qualifier" );
    }

    @Test( expected = IllegalArgumentException.class )
    public void findBindings_BindClassAndQualifier_QualifierIsNull() {
        RegistryRepository repository = new RegistryRepository();

        repository.findBindings( String.class, null );
    }

    @Test( expected = IllegalArgumentException.class )
    public void findBindings_BindClassAndQualifier_QualifierIsEmpty() {
        RegistryRepository repository = new RegistryRepository();

        repository.findBindings( String.class, "" );
    }

    @Test
    public void findBindings_BindClassAndQualifier_EmptyBindings_NotFound() {
        RegistryRepository repository = new RegistryRepository();

        List<RegistryRepositoryBinding> actual = repository.findBindings( InputStream.class, "qualifier" );
        assertThat( actual, equalTo( Collections.emptyList() ) );
    }

    @Test
    public void findBindings_BindClassAndQualifier_HasBindings_ClassBindNotFound() {
        RegistryRepository repository = new RegistryRepository();
        repository.add( new RegistryRepositoryBinding( String.class, "qualifier" ) );

        List<RegistryRepositoryBinding> actual = repository.findBindings( InputStream.class );
        assertThat( actual, equalTo( Collections.emptyList() ) );
    }

    @Test
    public void findBindings_BindClassAndQualifier_HasBindings_MatchButQualifierNotFound() {
        RegistryRepository repository = new RegistryRepository();
        repository.add( new RegistryRepositoryBinding( String.class, "qualifier" ) );

        List<RegistryRepositoryBinding> actual = repository.findBindings( String.class, "wrong-qualifier" );
        assertThat( actual, equalTo( Collections.emptyList() ) );
    }

    @Test
    public void findBindings_BindClassAndQualifier_HasBindings_NotFound_ButHasInheritanceMatch() {
        RegistryRepository repository = new RegistryRepository();
        repository.add( new RegistryRepositoryBinding( ByteArrayInputStream.class, "qualifier" ) );

        List<RegistryRepositoryBinding> actual = repository.findBindings( InputStream.class, "qualifier" );
        assertThat( actual, equalTo( Collections.emptyList() ) );
    }

    @Test
    public void findBindings_BindClassAndQualifier_HasBindings_Found() {
        RegistryRepository repository = new RegistryRepository();
        repository.add( new RegistryRepositoryBinding( InputStream.class, "qualifier" ) );

        List<RegistryRepositoryBinding> actual = repository.findBindings( InputStream.class, "qualifier" );
        assertThat( actual, equalTo( Collections.singletonList( new RegistryRepositoryBinding( InputStream.class, "qualifier" ) ) ) );
    }

    @Test
    public void findBindings_BindClassAndQualifier_HasBindings_FoundMultiple() {
        RegistryRepository repository = new RegistryRepository();
        repository.add( new RegistryRepositoryBinding( InputStream.class, "qualifier" ) );
        repository.add( new RegistryRepositoryBinding( InputStream.class, "qualifier" ) );
        repository.add( new RegistryRepositoryBinding( InputStream.class, "qualifier" ) );

        List<RegistryRepositoryBinding> actual = repository.findBindings( InputStream.class, "qualifier" );
        assertThat( actual, equalTo( Arrays.asList(
            new RegistryRepositoryBinding( InputStream.class, "qualifier" ),
            new RegistryRepositoryBinding( InputStream.class, "qualifier" ),
            new RegistryRepositoryBinding( InputStream.class, "qualifier" ) ) )
        );
    }
}
