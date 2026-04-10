package com.adeptum.adeptumfaces.commandbutton;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

class CommandButtonRendererTest {

	@Test
	void buildAjaxRequestUsesProvidedRenderTarget() {
		String script = CommandButtonRenderer.buildAjaxRequest(":counterPanel");

		assertEquals(
			"faces.ajax.request(this,event,{execute:'@this',render:':counterPanel'});return false;",
			script
		);
	}

	@Test
	void defaultRenderTargetFallsBackToClientId() {
		String renderTarget = CommandButtonRenderer.defaultRenderTarget("form:reset", null);

		assertEquals("form:reset", renderTarget);
	}

	@Test
	void defaultRenderTargetKeepsExplicitTarget() {
		String renderTarget = CommandButtonRenderer.defaultRenderTarget(
			"form:increment",
			":counterPanel messages"
		);

		assertEquals(":counterPanel messages", renderTarget);
	}

	@Test
	void joinRenderTargetsUsesResolvedClientIds() {
		String renderTarget = CommandButtonRenderer.joinRenderTargets(
			List.of("form:counterPanel", "form:messages"),
			"counterPanel messages"
		);

		assertEquals("form:counterPanel form:messages", renderTarget);
	}

	@Test
	void joinRenderTargetsFallsBackToOriginalExpression() {
		String renderTarget = CommandButtonRenderer.joinRenderTargets(
			List.of(),
			":counterPanel"
		);

		assertEquals(":counterPanel", renderTarget);
	}
}
