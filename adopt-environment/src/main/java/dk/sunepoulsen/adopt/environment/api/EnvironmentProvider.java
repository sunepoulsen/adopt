package dk.sunepoulsen.adopt.environment.api;

import java.util.Map;

public interface EnvironmentProvider {
    Map<String, Object> readEnvironment();
}
