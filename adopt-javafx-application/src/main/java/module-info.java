module adopt.javafx.application {
    requires adopt.javafx.window.system;
    requires javafx.controls;
    requires javafx.fxml;

    requires slf4j.api;

    exports dk.sunepoulsen.adopt.javafx.application;
}