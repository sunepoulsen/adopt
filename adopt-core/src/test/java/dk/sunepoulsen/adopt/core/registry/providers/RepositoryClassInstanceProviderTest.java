package dk.sunepoulsen.adopt.core.registry.providers;

import dk.sunepoulsen.adopt.core.registry.api.RegistryException;
import dk.sunepoulsen.adopt.core.registry.api.binder.RegistryInstanceScope;
import dk.sunepoulsen.adopt.core.registry.providers.test.InjectClassTypeTestCase;
import dk.sunepoulsen.adopt.core.registry.providers.test.InjectClassTypeTestCase_InjectClassWithInjections;
import dk.sunepoulsen.adopt.core.registry.providers.test.InjectSimpleTypesTestCase;
import dk.sunepoulsen.adopt.core.registry.repository.RegistryRepository;
import dk.sunepoulsen.adopt.core.registry.repository.RegistryRepositoryBinding;
import dk.sunepoulsen.adopt.core.registry.repository.RepositoryInstanceFactory;
import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

public class RepositoryClassInstanceProviderTest {
    @Test
    public void testGetInstance_Success() {
        RepositoryClassInstanceProvider<String> provider = new RepositoryClassInstanceProvider<>(new RegistryRepository(), String.class);

        assertThat( provider.getInstance(), notNullValue() );
    }

    @Test(expected = RegistryException.class )
    public void testGetInstance_NoConstructorWithNoArgs() {
        RepositoryClassInstanceProvider<Integer> provider = new RepositoryClassInstanceProvider<>(new RegistryRepository(), Integer.class);

        provider.getInstance();
    }

    @Test
    public void testGetInstance_InjectSimpleTypesTestCase() {
        RegistryRepositoryBinding binding;
        RegistryRepository repository = new RegistryRepository();

        binding = new RegistryRepositoryBinding( String.class );
        binding.setInstanceFactory( new RepositoryInstanceFactory( RegistryInstanceScope.SINGLE, new RepositoryValueInstanceProvider<>( "text" ) ));
        repository.add( binding );

        binding = new RegistryRepositoryBinding( Integer.class );
        binding.setInstanceFactory( new RepositoryInstanceFactory( RegistryInstanceScope.SINGLE, new RepositoryValueInstanceProvider<>( 17 ) ));
        repository.add( binding );

        RepositoryClassInstanceProvider<InjectSimpleTypesTestCase> provider = new RepositoryClassInstanceProvider<>(repository, InjectSimpleTypesTestCase.class);

        assertThat( provider.getInstance(), equalTo( new InjectSimpleTypesTestCase("text", 17)) );
    }

    @Test
    public void testGetInstance_InjectClassTypeTestCase() {
        RegistryRepositoryBinding binding;
        RegistryRepository repository = new RegistryRepository();

        binding = new RegistryRepositoryBinding( String.class );
        binding.setInstanceFactory( new RepositoryInstanceFactory( RegistryInstanceScope.SINGLE, new RepositoryValueInstanceProvider<>( "text" ) ));
        repository.add( binding );

        binding = new RegistryRepositoryBinding( Integer.class );
        binding.setInstanceFactory( new RepositoryInstanceFactory( RegistryInstanceScope.SINGLE, new RepositoryValueInstanceProvider<>( 17 ) ));
        repository.add( binding );

        binding = new RegistryRepositoryBinding( InjectClassTypeTestCase_InjectClassWithInjections.class );
        binding.setInstanceFactory( new RepositoryInstanceFactory( RegistryInstanceScope.SINGLE, new RepositoryClassInstanceProvider<>( repository, InjectClassTypeTestCase_InjectClassWithInjections.class ) ));
        repository.add( binding );

        RepositoryClassInstanceProvider<InjectClassTypeTestCase> provider = new RepositoryClassInstanceProvider<>(repository, InjectClassTypeTestCase.class);

        assertThat( provider.getInstance(), equalTo( new InjectClassTypeTestCase(new InjectClassTypeTestCase_InjectClassWithInjections("text", 17)) ));
    }
}
