package dk.sunepoulsen.adopt.javafx.application.handlers;

import com.google.common.collect.Lists;
import dk.sunepoulsen.adopt.javafx.application.api.AdoptJavaFXApplicationModule;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.ServiceLoader;

public class ApplicationModuleHandler {
    private ArrayList<AdoptJavaFXApplicationModule> modules;

    public ApplicationModuleHandler() {
        this.modules = Lists.newArrayList( ServiceLoader.load( AdoptJavaFXApplicationModule.class ).iterator() );
    }

    public void modulesStartup() {
        modules.stream()
            .sorted( Comparator.comparing( AdoptJavaFXApplicationModule::getExecutionOrder ) )
            .forEach( AdoptJavaFXApplicationModule::applicationStartup );
    }

    public void modulesShutdown() {
        modules.stream()
            .sorted( Comparator.comparing( AdoptJavaFXApplicationModule::getExecutionOrder ).reversed() )
            .forEach( AdoptJavaFXApplicationModule::applicationShutdown );
    }
}
