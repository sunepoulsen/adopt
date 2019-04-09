package dk.sunepoulsen.adopt.core.os.services;

import dk.sunepoulsen.adopt.core.environment.Environment;
import dk.sunepoulsen.adopt.core.environment.EnvironmentException;
import dk.sunepoulsen.adopt.core.os.api.OperatingSystem;
import dk.sunepoulsen.adopt.core.os.api.OperatingSystemException;

import java.io.File;

public class LocalOS implements OperatingSystem {
    @Override
    public boolean matchOperatingSystemName( String osName ) {
        return osName.equalsIgnoreCase( "localOS" );
    }

    @Override
    public File applicationDataDirectory() throws OperatingSystemException {
        Environment env = new Environment();

        try {
            return new File( env.getString( "adopt.local.directory" ) );
        }
        catch( EnvironmentException ex ) {
            throw new OperatingSystemException( "Unable to OperatingSystem for Local", ex );
        }
    }
}
