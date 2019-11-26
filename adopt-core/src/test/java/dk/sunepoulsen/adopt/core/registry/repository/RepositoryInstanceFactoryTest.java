package dk.sunepoulsen.adopt.core.registry.repository;

import dk.sunepoulsen.adopt.core.registry.api.binder.RegistryInstanceScope;
import dk.sunepoulsen.adopt.core.registry.providers.RepositoryInstanceProvider;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RepositoryInstanceFactoryTest {
    @Test
    public void getInstance_SingleScope_MultipleCalls() {
        ByteArrayInputStream inputStream1 = new ByteArrayInputStream( new byte[] {1, 2, 3, 4} );
        ByteArrayInputStream inputStream2 = new ByteArrayInputStream( new byte[] {10, 20, 30, 40} );

        RepositoryInstanceProvider<InputStream> provider = (RepositoryInstanceProvider<InputStream>) mock(RepositoryInstanceProvider.class);
        when(provider.getInstance()).thenReturn( inputStream1, inputStream2 );

        RepositoryInstanceFactory factory = new RepositoryInstanceFactory( RegistryInstanceScope.SINGLE, provider );
        assertThat( factory.getInstance(), equalTo(inputStream1));
        assertThat( factory.getInstance(), equalTo(inputStream1));
    }

    @Test
    public void getInstance_MultipleScope_MultipleCalls() {
        ByteArrayInputStream inputStream1 = new ByteArrayInputStream( new byte[] {1, 2, 3, 4} );
        ByteArrayInputStream inputStream2 = new ByteArrayInputStream( new byte[] {10, 20, 30, 40} );

        RepositoryInstanceProvider<InputStream> provider = (RepositoryInstanceProvider<InputStream>) mock(RepositoryInstanceProvider.class);
        when(provider.getInstance()).thenReturn( inputStream1, inputStream2 );

        RepositoryInstanceFactory factory = new RepositoryInstanceFactory( RegistryInstanceScope.MULTIPLE, provider );
        assertThat( factory.getInstance(), equalTo(inputStream1));
        assertThat( factory.getInstance(), equalTo(inputStream2));
    }
}
