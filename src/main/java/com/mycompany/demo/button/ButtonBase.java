/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.demo.Button;


import org.primefaces.cdk.api.FacesComponentBase;
import org.primefaces.cdk.api.Property;
import org.primefaces.component.api.UIOutcomeTarget;
import org.primefaces.component.api.Widget;

import jakarta.faces.component.html.HtmlOutcomeTargetButton;

@FacesComponentBase
public abstract class ButtonBase extends HtmlOutcomeTargetButton implements Widget, UIOutcomeTarget {

    public static final String COMPONENT_FAMILY = "org.primefaces.component";

    public static final String DEFAULT_RENDERER = "org.primefaces.component.ButtonRenderer";

    public ButtonBase() {
        setRendererType(DEFAULT_RENDERER);
    }

    @Override
    public String getFamily() {
        return COMPONENT_FAMILY;
    }

    @Property(defaultValue = "false", description = "Disables button.")
    @Override
    public abstract boolean isDisabled();

    @Property(description = "Icon of the button.")
    public abstract String getIcon();

    @Property(defaultValue = "left", description = "Position of the icon.")
    public abstract String getIconPos();

    @Property(defaultValue = "_self", description = "The window target.")
    public abstract String getTarget();

    @Property(defaultValue = "true", description = "Defines if label of the component is escaped or not.")
    public abstract boolean isEscape();

    @Property(defaultValue = "false", description = "Displays button inline instead of fitting the content width, only used by mobile.")
    public abstract boolean isInline();

    @Property(description = "The aria-label attribute is used to define a string that labels the current element for accessibility.")
    public abstract String getAriaLabel();
}
