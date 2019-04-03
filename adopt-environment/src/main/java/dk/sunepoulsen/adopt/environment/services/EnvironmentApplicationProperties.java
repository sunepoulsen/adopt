package dk.sunepoulsen.adopt.environment.services;

import dk.sunepoulsen.adopt.environment.api.EnvironmentProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

public class EnvironmentApplicationProperties implements EnvironmentProvider {
    private static Logger log = LoggerFactory.getLogger( EnvironmentApplicationProperties.class );

    @Override
    public Map<String, Object> readEnvironment() {
        Map<String, Object> applicationProperties = new HashMap<>();

        Properties resourceProperties = new Properties();
        try {
            resourceProperties.load( Objects.requireNonNull( ClassLoader.getSystemResourceAsStream( "application.properties" ) ) );
            resourceProperties
                .forEach( ( key, value ) -> applicationProperties.put( key.toString(), value ) );
        }
        catch( IOException ex ) {
            log.error( "Unable to load resource /application.properties", ex );
        }

        return applicationProperties;
    }
}
