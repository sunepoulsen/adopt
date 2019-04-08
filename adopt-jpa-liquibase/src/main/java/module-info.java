module adopt.jpa.liquibase {
    requires adopt.core;
    requires adopt.javafx.application.api;
    requires liquibase.core;
    requires java.sql;

    requires slf4j.api;

    provides dk.sunepoulsen.adopt.javafx.application.api.AdoptJavaFXApplicationModule with
        dk.sunepoulsen.adopt.persistence.liquibase.MigrateLiquebaseDatabase;

}