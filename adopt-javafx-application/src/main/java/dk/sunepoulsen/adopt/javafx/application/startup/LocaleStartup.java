package dk.sunepoulsen.adopt.javafx.application.startup;

import dk.sunepoulsen.adopt.core.environment.Environment;
import dk.sunepoulsen.adopt.core.environment.EnvironmentException;

import java.util.Locale;

/**
 * Helper class to set Locale doing startup.
 */
public class LocaleStartup {
    public static void initializeLocale( Environment env ) throws EnvironmentException {
        if( env.containsKey( "application.locale" ) ) {
            Locale.setDefault( Locale.forLanguageTag( env.getString( "application.locale" ) ) );
        }
    }
}
