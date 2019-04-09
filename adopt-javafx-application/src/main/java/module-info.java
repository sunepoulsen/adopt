module adopt.javafx.application {
    requires adopt.core;
    requires adopt.javafx.application.api;
    requires adopt.javafx.window.system;

    requires javafx.controls;
    requires javafx.fxml;

    requires com.google.common;

    requires slf4j.api;

    uses dk.sunepoulsen.adopt.javafx.application.api.AdoptJavaFXApplicationModule;

    exports dk.sunepoulsen.adopt.javafx.application;
}