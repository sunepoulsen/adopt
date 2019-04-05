package dk.sunepoulsen.adopt.core.bundles;

import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceBundles {
    //-------------------------------------------------------------------------
    //              Resource Bundles
    //-------------------------------------------------------------------------

    public static <T> ResourceBundle getBundle( Class<T> clazz ) {
        return getBundle( clazz, Locale.getDefault() );
    }

    public static <T> ResourceBundle getBundle( Class<T> clazz, Locale locale ) {
        String baseName = clazz.getName();
        return ResourceBundle.getBundle( baseName, locale, clazz.getModule() );
    }
}
