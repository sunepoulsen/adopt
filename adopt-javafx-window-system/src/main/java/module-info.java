module adopt.javafx.window.system {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    requires slf4j.api;

    requires com.google.common;

    requires adopt.javafx.window.system.api;

    exports dk.sunepoulsen.adopt.javafx.window.system.mainwindow;
    exports dk.sunepoulsen.adopt.javafx.window.system.modes;

    opens dk.sunepoulsen.adopt.javafx.window.system.mainwindow to adopt.javafx.application, javafx.fxml;
    opens dk.sunepoulsen.adopt.javafx.window.system.modes to javafx.fxml;

    uses dk.sunepoulsen.adopt.javafx.window.system.api.AdoptTopComponent;
}