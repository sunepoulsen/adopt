package dk.sunepoulsen.adopt.core.os;

import com.google.common.collect.Lists;
import dk.sunepoulsen.adopt.core.os.api.OperatingSystem;

import java.io.IOException;
import java.util.Optional;
import java.util.Properties;
import java.util.ServiceLoader;

/**
 * Created by sunepoulsen on 15/06/2017.
 */
public class OperatingSystemFactory {
    private static OperatingSystem operatingSystem = null;

    public static OperatingSystem getInstance() throws IOException {
        if( operatingSystem == null ) {
            operatingSystem = createOperatingSystem();
        }

        return operatingSystem;
    }

    private static OperatingSystem createOperatingSystem() throws IOException {
        return createOperatingSystem( System.getProperties() );
    }

    private static OperatingSystem createOperatingSystem( Properties properties ) throws IOException {
        String osName = properties.getProperty( "os.name" );
        if( properties.containsKey( "adopt.os.name" ) ) {
            osName = properties.getProperty( "adopt.os.name" );
        }

        final String operatingSystemName = osName;
        Optional<OperatingSystem> os = Lists.newArrayList( ServiceLoader.load( OperatingSystem.class ).iterator() ).stream()
            .filter( osSystem -> osSystem.matchOperatingSystemName( operatingSystemName ) )
            .findFirst();

        if( os.isPresent() ) {
            return os.get();
        }

        throw new IOException( "Unsupported operating system: " + osName );
    }
}
