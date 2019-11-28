module adopt.core {
    requires slf4j.api;
    requires com.google.common;

    exports dk.sunepoulsen.adopt.core.bundles;

    exports dk.sunepoulsen.adopt.core.os;
    exports dk.sunepoulsen.adopt.core.os.api;

    exports dk.sunepoulsen.adopt.core.environment.api;
    exports dk.sunepoulsen.adopt.core.environment;

    exports dk.sunepoulsen.adopt.core.registry.api;
    exports dk.sunepoulsen.adopt.core.registry.api.binder;

    uses dk.sunepoulsen.adopt.core.environment.api.EnvironmentProvider;

    provides dk.sunepoulsen.adopt.core.environment.api.EnvironmentProvider with
        dk.sunepoulsen.adopt.core.environment.services.EnvironmentSystemProperties,
        dk.sunepoulsen.adopt.core.environment.services.EnvironmentApplicationProperties;

    uses dk.sunepoulsen.adopt.core.os.api.OperatingSystem;

    provides dk.sunepoulsen.adopt.core.os.api.OperatingSystem with
        dk.sunepoulsen.adopt.core.os.services.LocalOS,
        dk.sunepoulsen.adopt.core.os.services.MacOS;

    uses dk.sunepoulsen.adopt.core.registry.api.RegistryModule;

    provides dk.sunepoulsen.adopt.core.registry.api.RegistryModule with
        dk.sunepoulsen.adopt.core.registry.modules.EnvironmentModule,
        dk.sunepoulsen.adopt.core.registry.modules.OperatingSystemModule;
}