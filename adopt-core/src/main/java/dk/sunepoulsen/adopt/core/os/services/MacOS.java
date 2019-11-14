package dk.sunepoulsen.adopt.core.os.services;

import dk.sunepoulsen.adopt.core.environment.services.EnvironmentApplicationProperties;
import dk.sunepoulsen.adopt.core.os.api.OperatingSystem;
import dk.sunepoulsen.adopt.core.os.api.OperatingSystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Map;

/**
 * Created by sunepoulsen on 15/06/2017.
 */
public class MacOS implements OperatingSystem {
    private static Logger log = LoggerFactory.getLogger( MacOS.class );
    private static final String APP_DATA_DIR_PATTERN = "%s/Library/Application Support/%s/%s";

    public boolean matchOperatingSystemName( String osName ) {
        return osName.equals( "Mac OS X" );
    }

    @Override
    public File applicationDataDirectory() throws OperatingSystemException {
        Map<String, Object> map = new EnvironmentApplicationProperties().readEnvironment();

        String homeDir = System.getProperty( "user.home" );
        String applicationName = map.get( "application.name" ).toString();
        String applicationVersion = map.get( "application.version" ).toString();
        String appDataPath = String.format( APP_DATA_DIR_PATTERN, homeDir, applicationName, applicationVersion );

        return new File( appDataPath );
    }
}
