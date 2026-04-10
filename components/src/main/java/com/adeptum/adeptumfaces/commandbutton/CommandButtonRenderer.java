
package com.adeptum.adeptumfaces.commandbutton;

import java.io.IOException;
import java.util.List;

import jakarta.faces.component.UIComponent;
import jakarta.faces.component.UIOutput;
import jakarta.faces.component.UIViewRoot;
import jakarta.faces.component.behavior.ClientBehaviorContext;
import jakarta.faces.component.search.SearchExpressionContext;
import jakarta.faces.context.FacesContext;
import jakarta.faces.context.ResponseWriter;
import jakarta.faces.event.ActionEvent;
import jakarta.faces.event.PhaseId;
import jakarta.faces.render.FacesRenderer;
import jakarta.faces.render.Renderer;
import java.util.Map;

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

		final String clientId = button.getClientId(context);
		final Map<String, String> params = context.getExternalContext().getRequestParameterMap();

		if (!(params.containsKey(clientId)
			|| clientId.equals(params.get(ClientBehaviorContext.BEHAVIOR_SOURCE_PARAM_NAME)))) {
			return;
		}

		final ActionEvent event = new ActionEvent(button);
		event.setPhaseId(PhaseId.INVOKE_APPLICATION);
		button.queueEvent(event);
	}

	@Override
	public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
		CommandButton button = (CommandButton) component;
		ResponseWriter writer = context.getResponseWriter();

		String clientId = button.getClientId(context);
		String type = resolveButtonType(button);
		Object value = button.getValue();

		if (button.isAjax()) {
			ensureFacesAjaxScript(context);
		}

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

		String script = null;

		if (button.isAjax()) {
			String clientId = button.getClientId(context);
			script = buildAjaxRequest(resolveRenderTarget(context, button, clientId));
		}

		if (button.requiresConfirmation()) {
			String confirmScript = button.getConfirmationScript();
			return confirmScript + (script != null ? script : "");
		}

		return script;
	}

	static String buildAjaxRequest(String renderTarget) {
		return "faces.ajax.request(this,event,{execute:'@this',render:'"
			+ renderTarget + "'}); return false;";
	}

	static String defaultRenderTarget(String clientId, String renderTarget) {
		if (renderTarget == null || renderTarget.isBlank()) {
			return clientId;
		}

		return renderTarget;
	}

	static String joinRenderTargets(List<String> clientIds, String renderTarget) {
		if (clientIds == null || clientIds.isEmpty()) {
			return renderTarget;
		}

		return String.join(" ", clientIds);
	}

	private String resolveRenderTarget(FacesContext context,
		CommandButton button, String clientId) {

		final String renderTarget = defaultRenderTarget(clientId, button.getRender());

		if (clientId.equals(renderTarget)) {
			return renderTarget;
		}

		SearchExpressionContext searchContext = SearchExpressionContext
			.createSearchExpressionContext(context, button);

		final List<String> clientIds = context.getApplication()
			.getSearchExpressionHandler()
			.resolveClientIds(searchContext, renderTarget);

		return joinRenderTargets(clientIds, renderTarget);
	}

	private void ensureFacesAjaxScript(FacesContext context) {
		UIViewRoot viewRoot = context.getViewRoot();

		if (viewRoot == null || hasFacesAjaxScript(context, viewRoot)) {
			return;
		}

		UIOutput script = (UIOutput) context.getApplication().createComponent(UIOutput.COMPONENT_TYPE);
		script.setRendererType("jakarta.faces.resource.Script");
		script.getAttributes().put("name", "faces.js");
		script.getAttributes().put("library", "jakarta.faces");
		viewRoot.addComponentResource(context, script, "head");
	}

	private boolean hasFacesAjaxScript(FacesContext context, UIViewRoot viewRoot) {
		for (UIComponent resource : viewRoot.getComponentResources(context, "head")) {
			Object name = resource.getAttributes().get("name");
			Object library = resource.getAttributes().get("library");

			if ("faces.js".equals(name) && "jakarta.faces".equals(library)) {
				return true;
			}
		}

		return false;
	}
}
