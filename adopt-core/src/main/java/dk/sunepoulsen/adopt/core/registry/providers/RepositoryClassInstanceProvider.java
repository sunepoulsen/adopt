package dk.sunepoulsen.adopt.core.registry.providers;

import dk.sunepoulsen.adopt.core.registry.api.RegistryException;

import java.lang.reflect.InvocationTargetException;

public class RepositoryClassInstanceProvider<T> implements RepositoryInstanceProvider<T> {
    private Class<T> clazz;

    public RepositoryClassInstanceProvider( Class<T> clazz ) {
        this.clazz = clazz;
    }

    @Override
    public T getInstance() throws RegistryException {
        try {
            return clazz.getDeclaredConstructor().newInstance();
        }
        catch( InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException ex ) {
            throw new RegistryException( "Unable to crete instance of class " + clazz.getName(), ex );
        }
    }

}
