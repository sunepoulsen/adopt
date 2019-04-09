package dk.sunepoulsen.adopt.core.os.api;

public class OperatingSystemException extends Exception {
    public OperatingSystemException() {
    }

    public OperatingSystemException( String message ) {
        super( message );
    }

    public OperatingSystemException( String message, Throwable cause ) {
        super( message, cause );
    }

    public OperatingSystemException( Throwable cause ) {
        super( cause );
    }

    public OperatingSystemException( String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace ) {
        super( message, cause, enableSuppression, writableStackTrace );
    }
}
