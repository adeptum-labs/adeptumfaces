/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.demo.Button;

import org.primefaces.cdk.api.FacesComponentDescription;
import org.primefaces.util.ComponentUtils;
import org.primefaces.util.HTML;
import org.primefaces.util.LangUtils;

import java.util.List;
import java.util.Map;

import jakarta.faces.application.ResourceDependency;
import jakarta.faces.component.FacesComponent;

@FacesComponent(value = Button.COMPONENT_TYPE, namespace = Button.COMPONENT_FAMILY)
@FacesComponentDescription("Button is an extension to the standard h:button component with skinning capabilities.")
@ResourceDependency(library = "primefaces", name = "components.css")
@ResourceDependency(library = "primefaces", name = "jquery/jquery.js")
@ResourceDependency(library = "primefaces", name = "core.js")
@ResourceDependency(library = "primefaces", name = "components.js")
public class Button extends ButtonBaseImpl {

    public static final String COMPONENT_TYPE = "org.primefaces.component.Button";

    public String resolveStyleClass() {
        String icon = getIcon();
        Object value = getValue();
        String styleClass = "";

        if (value != null && LangUtils.isBlank(icon)) {
            styleClass = HTML.BUTTON_TEXT_ONLY_BUTTON_CLASS;
        }
        else if (value != null && LangUtils.isNotBlank(icon)) {
            styleClass = getIconPos().equals("left") ? HTML.BUTTON_TEXT_ICON_LEFT_BUTTON_CLASS : HTML.BUTTON_TEXT_ICON_RIGHT_BUTTON_CLASS;
        }
        else if (value == null && LangUtils.isNotBlank(icon)) {
            styleClass = HTML.BUTTON_ICON_ONLY_BUTTON_CLASS;
        }

        if (isDisabled()) {
            styleClass = styleClass + " ui-state-disabled";
        }

        String userStyleClass = getStyleClass();
        if (userStyleClass != null) {
            styleClass = styleClass + " " + userStyleClass;
        }

        return styleClass;
    }

    @Override
    public Map<String, List<String>> getParams() {
        return ComponentUtils.getUIParams(this);
    }
}