package dk.sunepoulsen.adopt.core.environment.services;

import dk.sunepoulsen.adopt.core.environment.api.EnvironmentProvider;

import java.util.HashMap;
import java.util.Map;

public class EnvironmentSystemProperties implements EnvironmentProvider {
    @Override
    public Map<String, Object> readEnvironment() {
        Map<String, Object> systemProperties = new HashMap<>();

        System.getProperties()
            .forEach( ( key, value ) -> systemProperties.put( key.toString(), value ) );

        return systemProperties;
    }
}
