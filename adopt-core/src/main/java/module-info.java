module adopt.core {
    requires slf4j.api;
    requires com.google.common;

    exports dk.sunepoulsen.adopt.core.bundles;

    exports dk.sunepoulsen.adopt.core.os;
    exports dk.sunepoulsen.adopt.core.os.api;

    exports dk.sunepoulsen.adopt.core.environment.api;
    exports dk.sunepoulsen.adopt.core.environment;

    uses dk.sunepoulsen.adopt.core.environment.api.EnvironmentProvider;

    provides dk.sunepoulsen.adopt.core.environment.api.EnvironmentProvider with
        dk.sunepoulsen.adopt.core.environment.services.EnvironmentSystemProperties,
        dk.sunepoulsen.adopt.core.environment.services.EnvironmentApplicationProperties;

    provides dk.sunepoulsen.adopt.core.os.api.OperatingSystem with
        dk.sunepoulsen.adopt.core.os.services.LocalOS,
        dk.sunepoulsen.adopt.core.os.services.MacOS;

    uses dk.sunepoulsen.adopt.core.os.api.OperatingSystem;
}