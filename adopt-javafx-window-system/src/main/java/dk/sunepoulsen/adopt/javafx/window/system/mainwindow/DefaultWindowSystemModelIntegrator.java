package dk.sunepoulsen.adopt.javafx.window.system.mainwindow;

import dk.sunepoulsen.adopt.javafx.window.system.api.WindowSystemModelIntegrator;

public class DefaultWindowSystemModelIntegrator implements WindowSystemModelIntegrator {
    private WindowSystemModel windowSystemModel;

    public DefaultWindowSystemModelIntegrator( WindowSystemModel windowSystemModel ) {
        this.windowSystemModel = windowSystemModel;
    }
}
