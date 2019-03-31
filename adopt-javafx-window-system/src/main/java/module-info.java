module adopt.javafx.window.system {
    requires javafx.fxml;
    requires javafx.graphics;

    requires slf4j.api;

    requires adopt.javafx.window.system.api;

    exports dk.sunepoulsen.adopt.javafx.window.system.mainwindow;

    opens dk.sunepoulsen.adopt.javafx.window.system.mainwindow to adopt.javafx.application, javafx.fxml;
    opens dk.sunepoulsen.adopt.javafx.window.system.modes to javafx.fxml;
}