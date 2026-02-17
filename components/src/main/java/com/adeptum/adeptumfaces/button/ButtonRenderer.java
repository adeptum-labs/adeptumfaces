
package com.adeptum.adeptumfaces.button;

import com.adeptum.adeptumfaces.util.HtmlConstants;
import com.adeptum.adeptumfaces.util.JsEscapeUtils;
import com.adeptum.adeptumfaces.util.StringUtils;
import jakarta.faces.component.UIComponent;

import jakarta.faces.context.FacesContext;
import jakarta.faces.context.ResponseWriter;
import jakarta.faces.render.FacesRenderer;
import jakarta.faces.render.Renderer;

import java.io.IOException;

@FacesRenderer(componentFamily = ButtonBase.COMPONENT_FAMILY, rendererType = "com.adeptum.adeptumfaces.button.ButtonRenderer")
public class ButtonRenderer extends Renderer {
	@Override
	public void encodeEnd(FacesContext context, UIComponent comp) throws IOException {
		System.out.println("HEJSAN SVEJSAN!");

		Button component = (Button) comp;
		ResponseWriter writer = context.getResponseWriter();
		String clientid = component.getClientId(context);

		writer.startElement("button", component);
		writer.writeAttribute("id", comp.getId(), null);
		writer.writeAttribute("name", comp.getId(), null);
		writer.writeAttribute("type", "button", null);
		/*writer.writeAttribute("class", component.resolveStyleClass(), null);*/

		if (component.isDisabled()) {
			writer.writeAttribute("disabled", "disabled", null);
		}

		String onclick = buildOnclick(context, component);
		if (StringUtils.isNotBlank(onclick)) {
			writer.writeAttribute("onclick", onclick, null);
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

		/*String url = component.getOutcome();
		if (url != null) {
			sb.append("window.open('")
				.append(JsEscapeUtils.escape(url))
				.append("','")
				.append(JsEscapeUtils.escape(component.getTarget()))
				.append("');");
		}*/

		return sb.toString();
	}
}
