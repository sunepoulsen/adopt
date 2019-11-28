package dk.sunepoulsen.adopt.core.registry.providers;

import dk.sunepoulsen.adopt.core.registry.api.Inject;
import dk.sunepoulsen.adopt.core.registry.api.Qualifier;
import dk.sunepoulsen.adopt.core.registry.api.RegistryException;
import dk.sunepoulsen.adopt.core.registry.registries.RegistryReader;
import dk.sunepoulsen.adopt.core.registry.repository.RegistryRepository;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class RepositoryClassInstanceProvider<T> implements RepositoryInstanceProvider<T> {
    RegistryReader registryReader;
    private Class<T> clazz;

    public RepositoryClassInstanceProvider( RegistryRepository registryRepository, Class<T> clazz ) {
        this.registryReader = new RegistryReader( registryRepository );
        this.clazz = clazz;
    }

    @Override
    public T getInstance() throws RegistryException {
        try {
            Optional<Constructor<?>> injectableConstructor = findInjectableConstructor();
            if( injectableConstructor.isEmpty() ) {
                return clazz.getDeclaredConstructor().newInstance();
            }

            List<Object> constructorArgs = Arrays.stream( injectableConstructor.get().getParameterTypes() )
                .map( this::mapParamClazzToInstance )
                .collect( Collectors.toList() );

            return (T)(injectableConstructor.get().newInstance(constructorArgs.toArray()));

        }
        catch( InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException ex ) {
            throw new RegistryException( "Unable to crete instance of class " + clazz.getName(), ex );
        }
    }

    private Object mapParamClazzToInstance(Class<?> paramClazz) {
        Qualifier qualifier = paramClazz.getAnnotation( Qualifier.class );
        if( qualifier == null ) {
            return registryReader.getInstance( paramClazz );
        }

        return registryReader.getInstance( paramClazz, qualifier.value() );
    }

    private Optional<Constructor<?>> findInjectableConstructor() {
        return Arrays.stream(clazz.getConstructors())
            .filter( constructor -> constructor.getAnnotation( Inject.class ) != null)
            .findFirst();
    }
}
