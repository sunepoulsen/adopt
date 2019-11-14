package dk.sunepoulsen.adopt.core.os.services;

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
        String localDirectory = System.getProperty( "adopt.local.directory" );
        if( localDirectory == null ) {
            throw new OperatingSystemException( "The system property 'adopt.local.directory' does not exist" );
        }

        return new File( localDirectory );
    }
}
