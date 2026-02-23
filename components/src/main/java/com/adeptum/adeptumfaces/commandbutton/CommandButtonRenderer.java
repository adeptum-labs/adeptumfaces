package com.adeptum.adeptumfaces.commandbutton;

import java.io.IOException;

import jakarta.faces.component.UIComponent;
import jakarta.faces.component.UIForm;
import jakarta.faces.context.FacesContext;
import jakarta.faces.context.ResponseWriter;
import jakarta.faces.event.ActionEvent;
import jakarta.faces.render.FacesRenderer;
import jakarta.faces.render.Renderer;

@FacesRenderer(
        componentFamily = CommandButton.COMPONENT_FAMILY,
        rendererType = CommandButton.DEFAULT_RENDERER
)
public class CommandButtonRenderer extends Renderer {

        @Override
        public void decode(FacesContext context, UIComponent component) {
                CommandButton button = (CommandButton) component;

                if (button.isDisabled()) {
                        return;
                }

                String clientId = button.getClientId(context);

                if (context.getExternalContext()
                        .getRequestParameterMap()
                        .containsKey(clientId)) {

                button.queueEvent(new ActionEvent(button));
                }
        }

        @Override
        public void encodeEnd(FacesContext context, UIComponent component) throws IOException {

                CommandButton button = (CommandButton) component;
                ResponseWriter writer = context.getResponseWriter();

                String clientId = button.getClientId(context);
                /*String type = resolveButtonType(button);*/
                /*String styleClass = button.resolveStyleClass();*/
                String icon = button.getIcon();
                Object value = button.getValue();

                writer.startElement("button", button);

                writer.writeAttribute("id", clientId, null);
                writer.writeAttribute("name", clientId, null);
                /*writer.writeAttribute("type", type, null);*/

                /*if (styleClass != null) {
                        writer.writeAttribute("class", styleClass, null);
                }

                if (button.getAriaLabel() != null) {
                        writer.writeAttribute("aria-label", button.getAriaLabel(), null);
                }

                if (button.isDisabled()) {
                        writer.writeAttribute("disabled", "disabled", null);
                }

                String onclick = buildOnclick(context, button);

                if (onclick != null) {
                        writer.writeAttribute("onclick", onclick, null);
                }

                // ----- Icon -----
                if (icon != null && !icon.trim().isEmpty()) {
                        writer.startElement("span", null);

                        String iconClass = "button-icon " +
                        ("right".equals(button.getIconPos())
                                    ? "button-icon-right "
                                    : "button-icon-left ")
                                + icon;

                        writer.writeAttribute("class", iconClass.trim(), null);
                        writer.endElement("span");
                }*/

                // ----- Text -----
                writer.startElement("span", null);
                writer.writeAttribute("class", "button-text", null);

                if (value != null) {
                        if (button.isEscape()) {
                                writer.writeText(value.toString(), null);
                        }
                        else {
                                writer.write(value.toString());
                        }
                }

                writer.endElement("span");
                writer.endElement("button");
        }

        /*private String resolveButtonType(CommandButton button) {
                String type = button.getType();

                if (type == null) {
                        return "submit";
                }

                return type;
        }*/

        private String buildOnclick(FacesContext context, CommandButton button) {

                if (button.isDisabled()) {
                        return null;
                }

                boolean ajax = button.isAjax();
                String clientId = button.getClientId(context);

                String script;

                if (ajax) {
                        script = buildAjaxRequest(clientId);
                }
                else {
                        script = null;
                }

                if (button.requiresConfirmation()) {
                        String confirmScript = button.getConfirmationScript();
                        return confirmScript + (script != null ? script : "");
                }

                return script;
        }

        private String buildAjaxRequest(String clientId) {
                return "jsf.ajax.request('" + clientId + "', event); return false;";
        }

        /*private UIForm findClosestForm(UIComponent component) {
                UIComponent parent = component;

                while (parent != null && !(parent instanceof UIForm)) {
                        parent = parent.getParent();
                }

                return (UIForm) parent;
        }*/
}