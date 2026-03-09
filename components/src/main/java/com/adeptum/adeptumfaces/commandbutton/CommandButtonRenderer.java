package com.adeptum.adeptumfaces.commandbutton;

import java.io.IOException;

import jakarta.faces.component.UIComponent;
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
        String type = resolveButtonType(button);
        Object value = button.getValue();

        writer.startElement("button", button);
        writer.writeAttribute("id", clientId, null);
        writer.writeAttribute("name", clientId, null);
        writer.writeAttribute("type", type, null);
        writer.writeAttribute("onclick", buildOnclick(context, button), null);

        // ----- Text -----
        writer.startElement("span", null);
        writer.writeAttribute("class", "button-text", null);

        if (value != null) {
            if (button.isEscape()) {
                writer.writeText(value.toString(), null);
            } else {
                writer.write(value.toString());
            }
        }

        writer.endElement("span");
        writer.endElement("button");
    }

    private String resolveButtonType(CommandButton button) {
        String type = button.getType();
        return (type != null) ? type : "submit";
    }

    // ===== Korrekt buildOnclick =====
    private String buildOnclick(FacesContext context, CommandButton button) {
        if (button.isDisabled()) {
            return null;
        }

        String clientId = button.getClientId(context);
        String script = null;

        if (button.isAjax()) {
            script = buildAjaxRequest(clientId);
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
}