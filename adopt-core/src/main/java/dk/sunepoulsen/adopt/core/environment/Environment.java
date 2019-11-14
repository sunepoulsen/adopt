package dk.sunepoulsen.adopt.core.environment;

import com.google.common.collect.Lists;
import dk.sunepoulsen.adopt.core.environment.api.EnvironmentProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Stream;

public class Environment {
    private static Logger log = LoggerFactory.getLogger( Environment.class );

    private Map<String, Object> properties;

    public Environment() {
        this.properties = new HashMap<>();
        readEnvironmentFromProviders();
    }

    public void logProperties() {
        if( log.isDebugEnabled() ) {
            log.debug( "Environment properties:" );
            log.debug( "=======================" );
            properties.entrySet().stream()
                .sorted( Comparator.comparing( Map.Entry::getKey ) )
                .forEach( entry -> log.debug( "{} ==> {}", entry.getKey(), entry.getValue() ) );
        }
    }

    public Stream<Map.Entry<String, Object>> stream() {
        return properties.entrySet().stream();
    }

    public boolean containsKey( String key ) {
        return this.properties.containsKey( key );
    }

    public Object getProperty( String key ) throws EnvironmentException {
        if( !properties.containsKey( key ) ) {
            throw new EnvironmentException( String.format( "The property '%s' does not exist", key ) );
        }

        return properties.get( key );
    }

    public Object getProperty( String key, Object defaultValue ) throws EnvironmentException {
        if( !properties.containsKey( key ) ) {
            return defaultValue;
        }

        return getProperty( key );
    }

    public <T> T getProperty( String key, Class<T> clazz ) throws EnvironmentException {
        if( !properties.containsKey( key ) ) {
            throw new EnvironmentException( String.format( "The property '%s' does not exist", key ) );
        }

        Object value = properties.get( key );
        if( clazz.isAssignableFrom( value.getClass() ) ) {
            return ( T ) value;
        }

        throw new EnvironmentException( String.format( "The found property with key '%s' is not assignable from %s", key, clazz.getName() ) );
    }

    public <T> T getProperty( String key, Class<T> clazz, T defaultValue ) throws EnvironmentException {
        if( !properties.containsKey( key ) ) {
            return defaultValue;
        }

        return getProperty( key, clazz );
    }

    public String getString( String key ) throws EnvironmentException {
        return getProperty( key, String.class );
    }

    public String getString( String key, String defaultValue ) throws EnvironmentException {
        return getProperty( key, String.class, defaultValue );
    }

    public Boolean getBoolean( String key ) throws EnvironmentException {
        return getProperty( key, Boolean.class );
    }

    public Boolean getBoolean( String key, Boolean defaultValue ) throws EnvironmentException {
        return getProperty( key, Boolean.class, defaultValue );
    }

    private void readEnvironmentFromProviders() {
        ServiceLoader<EnvironmentProvider> loader = ServiceLoader.load( EnvironmentProvider.class );

        ArrayList<EnvironmentProvider> providers = Lists.newArrayList( loader.iterator() );
        providers.stream()
            .forEach( environmentProvider -> this.properties.putAll( environmentProvider.readEnvironment() ) );
    }
}
