package dk.sunepoulsen.adopt.core.registry.api;

public class RegistryException extends RuntimeException {
    public RegistryException( String message ) {
        super( message );
    }

    public RegistryException( String message, Throwable throwable ) {
        super( message, throwable );
    }
}
