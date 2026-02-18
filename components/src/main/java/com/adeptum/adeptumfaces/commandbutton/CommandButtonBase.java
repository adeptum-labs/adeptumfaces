
package com.adeptum.adeptumfaces.commandbutton;

import jakarta.el.MethodExpression;
import jakarta.faces.component.FacesComponent;
import jakarta.faces.component.html.HtmlCommandButton;

@FacesComponent(CommandButtonBase.COMPONENT_TYPE)
public abstract class CommandButtonBase extends HtmlCommandButton {

    public static final String COMPONENT_TYPE =
            "com.adeptum.adeptumfaces.commandbutton.CommandButton";

    public static final String COMPONENT_FAMILY =
            "com.adeptum.adeptumfaces.component";

    public static final String DEFAULT_RENDERER =
            "com.adeptum.adeptumfaces.commandbutton.CommandButtonRenderer";

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
        confirmationScript
    }

    public CommandButtonBase() {
        setRendererType(DEFAULT_RENDERER);
    }

    @Override
    public String getFamily() {
        return COMPONENT_FAMILY;
    }

    public MethodExpression getAction() {
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

    public boolean requiresConfirmation() {
        return getConfirmationScript() != null;
    }
}