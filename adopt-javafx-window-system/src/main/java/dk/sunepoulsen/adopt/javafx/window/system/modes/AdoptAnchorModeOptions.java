package dk.sunepoulsen.adopt.javafx.window.system.modes;

import dk.sunepoulsen.adopt.javafx.window.system.api.AdoptTopComponentWindowModeOptions;

import java.util.Objects;

public class AdoptAnchorModeOptions implements AdoptTopComponentWindowModeOptions {
    private Double topAnchor;
    private Double leftAnchor;
    private Double rightAnchor;
    private Double bottomAnchor;

    public AdoptAnchorModeOptions() {
        this( 0.0, 0.0, 0.0, 0.0 );
    }

    public AdoptAnchorModeOptions( Double topAnchor, Double leftAnchor, Double rightAnchor, Double bottomAnchor ) {
        this.topAnchor = topAnchor;
        this.leftAnchor = leftAnchor;
        this.rightAnchor = rightAnchor;
        this.bottomAnchor = bottomAnchor;
    }

    public Double getTopAnchor() {
        return topAnchor;
    }

    public void setTopAnchor( Double topAnchor ) {
        this.topAnchor = topAnchor;
    }

    public Double getLeftAnchor() {
        return leftAnchor;
    }

    public void setLeftAnchor( Double leftAnchor ) {
        this.leftAnchor = leftAnchor;
    }

    public Double getRightAnchor() {
        return rightAnchor;
    }

    public void setRightAnchor( Double rightAnchor ) {
        this.rightAnchor = rightAnchor;
    }

    public Double getBottomAnchor() {
        return bottomAnchor;
    }

    public void setBottomAnchor( Double bottomAnchor ) {
        this.bottomAnchor = bottomAnchor;
    }

    @Override
    public boolean equals( Object o ) {
        if( this == o ) {
            return true;
        }
        if( !( o instanceof AdoptAnchorModeOptions ) ) {
            return false;
        }
        AdoptAnchorModeOptions that = ( AdoptAnchorModeOptions ) o;
        return Objects.equals( topAnchor, that.topAnchor ) &&
            Objects.equals( leftAnchor, that.leftAnchor ) &&
            Objects.equals( rightAnchor, that.rightAnchor ) &&
            Objects.equals( bottomAnchor, that.bottomAnchor );
    }

    @Override
    public int hashCode() {
        return Objects.hash( topAnchor, leftAnchor, rightAnchor, bottomAnchor );
    }

    @Override
    public String toString() {
        return "AdoptAnchorModeOptions{" +
            "topAnchor=" + topAnchor +
            ", leftAnchor=" + leftAnchor +
            ", rightAnchor=" + rightAnchor +
            ", bottomAnchor=" + bottomAnchor +
            '}';
    }
}
