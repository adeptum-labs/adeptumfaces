package org.butterfaces.component.showcase;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class TextInputShowcase extends AbstractInputShowcase implements Serializable {

    @Override
    protected Object initValue() {
        return "Start value";
    }

    @Override
    public String getReadableValue() {
        return getValue() != null ? getValue().toString() : "";
    }

    @Override
    public boolean isReadableValueSupported() {
        return true;
    }
}
