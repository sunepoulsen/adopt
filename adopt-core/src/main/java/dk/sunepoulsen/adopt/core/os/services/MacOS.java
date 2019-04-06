package dk.sunepoulsen.adopt.core.os.services;

import dk.sunepoulsen.adopt.core.environment.Environment;
import dk.sunepoulsen.adopt.core.environment.EnvironmentException;
import dk.sunepoulsen.adopt.core.os.api.OperatingSystem;
import dk.sunepoulsen.adopt.core.os.api.OperatingSystemException;

import java.io.File;

/**
 * Created by sunepoulsen on 15/06/2017.
 */
public class MacOS implements OperatingSystem {
    private static final String APP_DATA_DIR_PATTERN = "%s/Library/Application Support/%s/%s";

    public boolean matchOperatingSystemName( String osName ) {
        return osName.equals( "Mac OS X" );
    }

    @Override
    public File applicationDataDirectory() throws OperatingSystemException {
        Environment env = new Environment();

        try {
            String homeDir = System.getProperty( "user.home" );
            String applicationName = env.getString( "application.name" );
            String applicationVersion = env.getString( "application.version" );
            String appDataPath = String.format( APP_DATA_DIR_PATTERN, homeDir, applicationName, applicationVersion );

            return new File( appDataPath );
        }
        catch( EnvironmentException ex ) {
            throw new OperatingSystemException( "Unable to OperatingSystem for Mac", ex );
        }
    }
}
