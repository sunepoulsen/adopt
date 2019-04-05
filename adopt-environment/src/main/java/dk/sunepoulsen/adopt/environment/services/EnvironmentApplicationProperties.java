package dk.sunepoulsen.adopt.environment.services;

import dk.sunepoulsen.adopt.environment.api.EnvironmentProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class EnvironmentApplicationProperties implements EnvironmentProvider {
    private static Logger log = LoggerFactory.getLogger( EnvironmentApplicationProperties.class );

    @Override
    public Map<String, Object> readEnvironment() {
        Map<String, Object> map = new HashMap<>( readFromResource( "application.properties" ) );

        if( System.getProperties().containsKey( "adopt.profile.name" ) ) {
            map.putAll( readFromResource( "application-" + System.getProperties().getProperty( "adopt.profile.name" ) + ".properties" ) );
        }

        return map;
    }

    private Map<String, Object> readFromResource( String resourceName ) {
        Map<String, Object> map = new HashMap<>();

        Properties properties = new Properties();
        try {
            InputStream resourceStream = ClassLoader.getSystemResourceAsStream( resourceName );
            if( resourceStream == null ) {
                log.error( "Unable to load resource /" + resourceName );
            }
            else {
                properties.load( resourceStream );
                properties
                    .forEach( ( key, value ) -> map.put( key.toString(), value ) );
            }
        }
        catch( IOException ex ) {
            log.error( "Unable to read properties from resource /" + resourceName, ex );
        }

        return map;
    }
}
