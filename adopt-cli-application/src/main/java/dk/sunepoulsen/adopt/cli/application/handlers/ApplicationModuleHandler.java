package dk.sunepoulsen.adopt.cli.application.handlers;

import com.google.common.collect.Lists;
import dk.sunepoulsen.adopt.cli.application.api.AdoptCliApplicationModule;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.ServiceLoader;

public class ApplicationModuleHandler {
    private ArrayList<AdoptCliApplicationModule> modules;

    public ApplicationModuleHandler() {
        this.modules = Lists.newArrayList( ServiceLoader.load( AdoptCliApplicationModule.class ).iterator() );
    }

    public void modulesStartup() {
        modules.stream()
            .sorted( Comparator.comparing( AdoptCliApplicationModule::getExecutionOrder ) )
            .forEach( AdoptCliApplicationModule::applicationStartup );
    }

    public void modulesShutdown() {
        modules.stream()
            .sorted( Comparator.comparing( AdoptCliApplicationModule::getExecutionOrder ).reversed() )
            .forEach( AdoptCliApplicationModule::applicationShutdown );
    }
}
