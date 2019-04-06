package dk.sunepoulsen.adopt.core.environment.api;

import java.util.Map;

public interface EnvironmentProvider {
    Map<String, Object> readEnvironment();
}
