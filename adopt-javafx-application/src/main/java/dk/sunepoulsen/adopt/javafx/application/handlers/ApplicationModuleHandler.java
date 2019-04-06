package dk.sunepoulsen.adopt.javafx.application.handlers;

import com.google.common.collect.Lists;
import dk.sunepoulsen.adopt.javafx.application.api.AdoptJavaFXApplicationModule;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.ServiceLoader;

public class ApplicationModuleHandler {
    public static void modulesStartup() {
        loadModules().stream()
            .sorted( Comparator.comparing( AdoptJavaFXApplicationModule::getExecutionOrder ) )
            .forEach( AdoptJavaFXApplicationModule::applicationStartup );
    }

    public static void modulesShutdown() {
        loadModules().stream()
            .sorted( Comparator.comparing( AdoptJavaFXApplicationModule::getExecutionOrder ).reversed() )
            .forEach( AdoptJavaFXApplicationModule::applicationShutdown );
    }

    private static ArrayList<AdoptJavaFXApplicationModule> loadModules() {
        ServiceLoader<AdoptJavaFXApplicationModule> loader = ServiceLoader.load( AdoptJavaFXApplicationModule.class );

        return Lists.newArrayList( loader.iterator() );
    }
}
