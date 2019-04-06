package dk.sunepoulsen.adopt.core.os.api;

import java.io.File;

/**
 * Created by sunepoulsen on 15/06/2017.
 */
public interface OperatingSystem {
    boolean matchOperatingSystemName( final String osName );

    File applicationDataDirectory() throws OperatingSystemException;
}
