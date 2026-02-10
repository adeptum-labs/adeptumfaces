/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.demo.Button;

import com.mycompany.demo.utils.HtmlConstants;
import com.mycompany.demo.utils.JsEscapeUtils;
import com.mycompany.demo.utils.StringUtils;

import jakarta.faces.context.FacesContext;
import jakarta.faces.context.ResponseWriter;
import jakarta.faces.render.FacesRenderer;
import jakarta.faces.render.Renderer;

import java.io.IOException;


@FacesRenderer(componentFamily = ButtonBase.COMPONENT_FAMILY, rendererType = "demo.ButtonRenderer")
public class ButtonRenderer extends Renderer {

        @Override
        public void encodeEnd(FacesContext context, jakarta.faces.component.UIComponent comp) throws IOException {
                Button component = (Button) comp;
                ResponseWriter writer = context.getResponseWriter();
                String clientid = component.getClientId(context);
        
                writer.startElement("button", component);
                writer.writeAttribute("id", clientId, null);
                writer.writeAttribute("name", clientId, null);
                writer.writeAttribute("type", "button", null);
                writer.writeAttribute("class", component.resolveStyleClass(), null);
        
        if (component.isDisabled()){
                writer.writeAttribute("disabled", "disabled", null);
        }
        
        String onclick = buildOnclick(context, component);
        if (StringUtils.isNotBlank(onclick)) {
                writer.writeAttribute("onclick", onclick, null);
        }
        
        // Icon
        if (StringUtils.isNotBlank(component.getIcon())) {
                String iconClass = component.getIconPos().equals("left")
                        ? HtmlConstants.BUTTON_LEFT_ICON
                        : HtmlConstants.BUTTON_RIGHT_ICON;

                writer.startElement("span", null);
                writer.writeAttribute("class", iconClass + " " + component.getIcon(), null);
                writer.endElement("span");
        }
        
        // Text
        writer.startElement("span", null);
        writer.writeAttribute("class", HtmlConstants.BUTTON_TEXT, null);

        Object value = component.getValue();
        if (value != null) {
            writer.writeText(value.toString(), null);
        }

        writer.endElement("span");
        writer.endElement("button");
        }

        private String buildOnclick(FacesContext context, Button component) {
                StringBuilder sb = new StringBuilder();

                if (component.getOnclick() != null) {
                        sb.append(component.getOnclick()).append(";");
                }

                String url = component.getOutcome();
                if (url != null) {
                        sb.append("window.open('")
                        .append(JsEscapeUtils.escape(url))
                        .append("','")
                        .append(JsEscapeUtils.escape(component.getTarget()))
                        .append("');");
                }

                return sb.toString();
        }
}
