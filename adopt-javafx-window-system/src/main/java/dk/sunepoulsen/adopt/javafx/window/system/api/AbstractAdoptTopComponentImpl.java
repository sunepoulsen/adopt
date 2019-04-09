package dk.sunepoulsen.adopt.javafx.window.system.api;

public abstract class AbstractAdoptTopComponentImpl implements AdoptTopComponent {
    private String modeIdentifier;
    private AdoptTopComponentWindowModeOptions windowModeOptions;
    private WindowSystemModelIntegrator windowSystemModelIntegrator;
    private boolean createOnStartup;

    public AbstractAdoptTopComponentImpl( String modeIdentifier, boolean createOnStartup ) {
        this.modeIdentifier = modeIdentifier;
        this.createOnStartup = createOnStartup;
    }

    @Override
    public String modeIdentifier() {
        return this.modeIdentifier;
    }

    @Override
    public AdoptTopComponentWindowModeOptions getWindowModeOptions() {
        return this.windowModeOptions;
    }

    @Override
    public void setWindowModeOptions( AdoptTopComponentWindowModeOptions windowModeOptions ) {
        this.windowModeOptions = windowModeOptions;
    }

    public WindowSystemModelIntegrator getWindowSystemModelIntegrator() {
        return windowSystemModelIntegrator;
    }

    @Override
    public void setWindowSystemModelIntegrator( WindowSystemModelIntegrator windowSystemModelIntegrator ) {
        this.windowSystemModelIntegrator = windowSystemModelIntegrator;
    }

    @Override
    public boolean createOnStartup() {
        return this.createOnStartup;
    }
}
