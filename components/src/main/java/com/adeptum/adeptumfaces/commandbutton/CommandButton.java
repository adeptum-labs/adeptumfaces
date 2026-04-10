
package com.adeptum.adeptumfaces.commandbutton;

import com.adeptum.adeptumfaces.annotation.Attribute;
import jakarta.el.MethodExpression;
import jakarta.faces.component.FacesComponent;
import jakarta.faces.component.html.HtmlCommandButton;
import jakarta.faces.component.html.HtmlOutcomeTargetButton;

@FacesComponent(CommandButton.COMPONENT_TYPE)
public class CommandButton extends HtmlCommandButton {

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
    
    /*public boolean isAjaxified() {
        String type = getType();
        return !"reset".equals(type)
                && !"button".equals(type)
                && isAjax();
    }*/
    
    public static final String COMPONENT_TYPE =
            "com.adeptum.adeptumfaces.commandbutton.CommandButton";

    public static final String COMPONENT_FAMILY =
            "com.adeptum.adeptumfaces.component";

    public static final String DEFAULT_RENDERER =
            "com.adeptum.adeptumfaces.commandbutton.CommandButtonRenderer";

    @Attribute(name = "icon", required = false, description = "Icon of the button.")
    private String icon;

    protected enum PropertyKeys {
        ajax,
        icon,
        iconPos,
        escape,
        validateClient,
        enabledByValidateClient,
        renderDisabledClick,
        ariaLabel,
        disableOnAjax,
        confirmationScript,
        type,
        render
    }

    public CommandButton() {
        setRendererType(DEFAULT_RENDERER);
    }

    @Override
    public String getFamily() {
        return COMPONENT_FAMILY;
    }

    public MethodExpression getAction() {
            System.out.println("test");
        return getActionExpression();
    }

    public boolean isAjax() {
        return (Boolean) getStateHelper().eval(PropertyKeys.ajax, true);
    }

    public void setAjax(boolean ajax) {
        getStateHelper().put(PropertyKeys.ajax, ajax);
    }

    public String getIcon() {
        return (String) getStateHelper().eval(PropertyKeys.icon);
    }

    public void setIcon(String icon) {
        getStateHelper().put(PropertyKeys.icon, icon);
    }

    public String getIconPos() {
        return (String) getStateHelper().eval(PropertyKeys.iconPos, "left");
    }

    public void setIconPos(String iconPos) {
        getStateHelper().put(PropertyKeys.iconPos, iconPos);
    }

    public boolean isEscape() {
        return (Boolean) getStateHelper().eval(PropertyKeys.escape, true);
    }

    public void setEscape(boolean escape) {
        getStateHelper().put(PropertyKeys.escape, escape);
    }

    public boolean isValidateClient() {
        return (Boolean) getStateHelper().eval(PropertyKeys.validateClient, false);
    }

    public void setValidateClient(boolean validateClient) {
        getStateHelper().put(PropertyKeys.validateClient, validateClient);
    }

    public boolean isEnabledByValidateClient() {
        return (Boolean) getStateHelper().eval(PropertyKeys.enabledByValidateClient, false);
    }

    public void setEnabledByValidateClient(boolean enabled) {
        getStateHelper().put(PropertyKeys.enabledByValidateClient, enabled);
    }

    public boolean isRenderDisabledClick() {
        return (Boolean) getStateHelper().eval(PropertyKeys.renderDisabledClick, false);
    }

    public void setRenderDisabledClick(boolean renderDisabledClick) {
        getStateHelper().put(PropertyKeys.renderDisabledClick, renderDisabledClick);
    }

    public String getAriaLabel() {
        return (String) getStateHelper().eval(PropertyKeys.ariaLabel);
    }

    public void setAriaLabel(String ariaLabel) {
        getStateHelper().put(PropertyKeys.ariaLabel, ariaLabel);
    }

    public boolean isDisableOnAjax() {
        return (Boolean) getStateHelper().eval(PropertyKeys.disableOnAjax, true);
    }

    public void setDisableOnAjax(boolean disableOnAjax) {
        getStateHelper().put(PropertyKeys.disableOnAjax, disableOnAjax);
    }

    public String getConfirmationScript() {
        return (String) getStateHelper().eval(PropertyKeys.confirmationScript);
    }

    public void setConfirmationScript(String script) {
        getStateHelper().put(PropertyKeys.confirmationScript, script);
    }

    public String getFullLabel() {
    String label = getValue() != null ? getValue().toString() : "";
    String aria = getAriaLabel();

    if (aria != null && !aria.isBlank()) {
        return label + " - " + aria;
    }

    return label;
    }
    
    public boolean requiresConfirmation() {
        return getConfirmationScript() != null;
    }
    
    public String getType() {
            return (String) getStateHelper().eval(PropertyKeys.type, "submit");
    }
    
    public void setType(String type) {
        getStateHelper().put(PropertyKeys.type, type);
    }
    
    public String getRender() {
                return (String) getStateHelper().eval(PropertyKeys.render, null);
        }
    public void setRender(String render) {
                getStateHelper().put(PropertyKeys.render, render);
        }
}
