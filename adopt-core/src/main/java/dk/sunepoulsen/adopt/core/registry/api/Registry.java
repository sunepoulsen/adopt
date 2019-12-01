package dk.sunepoulsen.adopt.core.registry.api;

import java.util.List;

public interface Registry {
    <T> T getInstance( Class<T> clazz ) throws RegistryException;
    <T> T getInstance( Class<T> clazz, String qualifier ) throws RegistryException;
    <T> List<T> getInstances( Class<T> clazz ) throws RegistryException;
    <T> List<T> getInstances( Class<T> clazz, String qualifier ) throws RegistryException;
    <T> T newInstance( Class<T> clazz ) throws RegistryException;
}
