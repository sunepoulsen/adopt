module adopt.environment {
    requires slf4j.api;
    requires com.google.common;

    exports dk.sunepoulsen.adopt.environment.api;
    exports dk.sunepoulsen.adopt.environment;

    uses dk.sunepoulsen.adopt.environment.api.EnvironmentProvider;

    provides dk.sunepoulsen.adopt.environment.api.EnvironmentProvider with
        dk.sunepoulsen.adopt.environment.services.EnvironmentSystemProperties,
        dk.sunepoulsen.adopt.environment.services.EnvironmentApplicationProperties;
}