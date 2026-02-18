
package com.adeptum.adeptumfaces.commandbutton;

import jakarta.faces.application.ResourceDependency;
import jakarta.faces.component.FacesComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.FacesEvent;

import jakarta.faces.component.FacesComponent;

@FacesComponent(CommandButtonBase.COMPONENT_TYPE)
public class CommandButton extends CommandButtonBase {

    /*public String resolveStyleClass() {

        String icon = getIcon();
        Object value = getValue();

        StringBuilder styleClass = new StringBuilder("ui-button");

        if (value != null && (icon == null || icon.isBlank())) {
            styleClass.append(" ui-button-text-only");
        }
        else if (value != null && icon != null && !icon.isBlank()) {
            if ("right".equals(getIconPos())) {
                styleClass.append(" ui-button-text-icon-right");
            }
            else {
                styleClass.append(" ui-button-text-icon-left");
            }
        }
        else if (value == null && icon != null && !icon.isBlank()) {
            styleClass.append(" ui-button-icon-only");
        }

        if (isDisabled()) {
            styleClass.append(" ui-state-disabled");
        }

        String userStyleClass = getStyleClass();
        if (userStyleClass != null) {
            styleClass.append(" ").append(userStyleClass);
        }

        return styleClass.toString();
    }*/
    
    public boolean isAjaxified() {
        String type = getType();
        return !"reset".equals(type)
                && !"button".equals(type)
                && isAjax();
    }
}
