package dk.sunepoulsen.adopt.javafx.application.api;

public abstract class AdoptJavaFXApplicationModule {
    private Integer executionOrder;

    public AdoptJavaFXApplicationModule() {
        this( Integer.MIN_VALUE );
    }

    public AdoptJavaFXApplicationModule( Integer executionOrder ) {
        this.executionOrder = executionOrder;
    }

    public Integer getExecutionOrder() {
        return this.executionOrder;
    }

    public abstract void applicationStartup();

    public abstract void applicationShutdown();
}
