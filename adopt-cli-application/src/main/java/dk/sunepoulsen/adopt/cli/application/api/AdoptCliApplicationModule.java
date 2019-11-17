package dk.sunepoulsen.adopt.cli.application.api;

public abstract class AdoptCliApplicationModule {
    private Integer executionOrder;

    public AdoptCliApplicationModule() {
        this( Integer.MIN_VALUE );
    }

    public AdoptCliApplicationModule( Integer executionOrder ) {
        this.executionOrder = executionOrder;
    }

    public Integer getExecutionOrder() {
        return this.executionOrder;
    }

    public abstract void applicationStartup();

    public abstract void applicationShutdown();
}
