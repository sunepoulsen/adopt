package dk.sunepoulsen.adopt.core.registry.api;

import dk.sunepoulsen.adopt.core.registry.api.binder.RegistryInstanceScope;
import dk.sunepoulsen.adopt.core.registry.binder.DefaultBinder;
import dk.sunepoulsen.adopt.core.registry.providers.RepositoryValueInstanceProvider;
import dk.sunepoulsen.adopt.core.registry.repository.RegistryRepository;
import dk.sunepoulsen.adopt.core.registry.repository.RegistryRepositoryBinding;
import dk.sunepoulsen.adopt.core.registry.repository.RepositoryInstanceFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

@RunWith( MockitoJUnitRunner.class )
public class RegistryTest {
    @Mock
    private RegistryRepository registryRepository;

    @Test
    public void testRegisterModules() {
        RegistryModule module = mock( RegistryModule.class);

        new Registry( Collections.singletonList( module ), registryRepository );

        verify( module ).configure( any( DefaultBinder.class ) );
    }

    @Test(expected = RegistryException.class)
    public void testGetInstance_Class_NotFound() {
        when(registryRepository.findBindings( eq(String.class) )).thenReturn( Collections.emptyList() );

        new Registry( Collections.emptyList(), registryRepository ).getInstance( String.class );
    }

    @Test
    public void testGetInstance_Class_Found() {
        RegistryRepositoryBinding binding = new RegistryRepositoryBinding( String.class );
        binding.setInstanceFactory( new RepositoryInstanceFactory( RegistryInstanceScope.SINGLE, new RepositoryValueInstanceProvider<>( "value" ) ));
        when(registryRepository.findBindings( eq(String.class) )).thenReturn( Collections.singletonList(binding) );

        Registry registry = new Registry( Collections.emptyList(), registryRepository );
        assertThat( registry.getInstance( String.class ), equalTo("value"));
    }

    @Test(expected = RegistryException.class)
    public void testGetInstance_ClassAndQualifier_NotFound() {
        when(registryRepository.findBindings( eq(String.class), eq("qualifier") )).thenReturn( Collections.emptyList() );

        new Registry( Collections.emptyList(), registryRepository ).getInstance( String.class, "qualifier" );
    }

    @Test
    public void testGetInstance_ClassAndQualifier_Found() {
        RegistryRepositoryBinding binding = new RegistryRepositoryBinding( String.class, "qualifier" );
        binding.setInstanceFactory( new RepositoryInstanceFactory( RegistryInstanceScope.SINGLE, new RepositoryValueInstanceProvider<>( "value" ) ));
        when(registryRepository.findBindings( eq(String.class), eq("qualifier") )).thenReturn( Collections.singletonList(binding) );

        Registry registry = new Registry( Collections.emptyList(), registryRepository );
        assertThat( registry.getInstance( String.class, "qualifier" ), equalTo("value"));
    }

    @Test(expected = RegistryException.class)
    public void testGetInstances_Class_NotFound() {
        when(registryRepository.findBindings( eq(String.class) )).thenReturn( Collections.emptyList() );

        new Registry( Collections.emptyList(), registryRepository ).getInstances( String.class );
    }

    @Test
    public void testGetInstances_Class_Found() {
        RegistryRepositoryBinding binding1 = new RegistryRepositoryBinding( String.class );
        binding1.setInstanceFactory( new RepositoryInstanceFactory( RegistryInstanceScope.SINGLE, new RepositoryValueInstanceProvider<>( "value-1" ) ));

        RegistryRepositoryBinding binding2 = new RegistryRepositoryBinding( String.class );
        binding2.setInstanceFactory( new RepositoryInstanceFactory( RegistryInstanceScope.SINGLE, new RepositoryValueInstanceProvider<>( "value-2" ) ));

        RegistryRepositoryBinding binding3 = new RegistryRepositoryBinding( String.class );
        binding3.setInstanceFactory( new RepositoryInstanceFactory( RegistryInstanceScope.SINGLE, new RepositoryValueInstanceProvider<>( "value-3" ) ));

        when(registryRepository.findBindings( eq(String.class) )).thenReturn( Arrays.asList( binding1, binding2, binding3 ) );

        Registry registry = new Registry( Collections.emptyList(), registryRepository );
        assertThat( registry.getInstances( String.class ), equalTo(Arrays.asList( "value-1", "value-2", "value-3")));
    }

    @Test(expected = RegistryException.class)
    public void testGetInstances_ClassAndQualifier_NotFound() {
        when(registryRepository.findBindings( eq(String.class), eq("qualifier") )).thenReturn( Collections.emptyList() );

        new Registry( Collections.emptyList(), registryRepository ).getInstances( String.class, "qualifier" );
    }

    @Test
    public void testGetInstances_ClassAndQualifier_Found() {
        RegistryRepositoryBinding binding1 = new RegistryRepositoryBinding( String.class, "qualifier" );
        binding1.setInstanceFactory( new RepositoryInstanceFactory( RegistryInstanceScope.SINGLE, new RepositoryValueInstanceProvider<>( "value-1" ) ));

        RegistryRepositoryBinding binding2 = new RegistryRepositoryBinding( String.class, "qualifier" );
        binding2.setInstanceFactory( new RepositoryInstanceFactory( RegistryInstanceScope.SINGLE, new RepositoryValueInstanceProvider<>( "value-2" ) ));

        RegistryRepositoryBinding binding3 = new RegistryRepositoryBinding( String.class, "qualifier" );
        binding3.setInstanceFactory( new RepositoryInstanceFactory( RegistryInstanceScope.SINGLE, new RepositoryValueInstanceProvider<>( "value-3" ) ));

        when(registryRepository.findBindings( eq(String.class), eq("qualifier") )).thenReturn( Arrays.asList( binding1, binding2, binding3 ) );

        Registry registry = new Registry( Collections.emptyList(), registryRepository );
        assertThat( registry.getInstances( String.class, "qualifier" ), equalTo(Arrays.asList( "value-1", "value-2", "value-3")));
    }
}
