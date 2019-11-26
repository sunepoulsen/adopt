package dk.sunepoulsen.adopt.core.registry.providers;

import dk.sunepoulsen.adopt.core.registry.api.RegistryException;
import org.junit.Test;

import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

public class RepositoryClassInstanceProviderTest {
    @Test
    public void testGetInstance_Success() {
        RepositoryClassInstanceProvider<String> provider = new RepositoryClassInstanceProvider<>(String.class);

        assertThat( provider.getInstance(), notNullValue() );
    }

    @Test(expected = RegistryException.class )
    public void testGetInstance_NoConstructorWithNoArgs() {
        RepositoryClassInstanceProvider<Integer> provider = new RepositoryClassInstanceProvider<>(Integer.class);

        provider.getInstance();
    }
}
