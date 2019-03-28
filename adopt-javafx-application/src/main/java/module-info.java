module adopt.javafx.application {
    requires javafx.controls;
    requires adopt.javafx.mainwindow;

    requires slf4j.api;

    exports dk.sunepoulsen.adopt.javafx.application;
}