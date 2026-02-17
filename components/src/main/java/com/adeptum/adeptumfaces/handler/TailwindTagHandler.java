package com.adeptum.adeptumfaces.handler;

import jakarta.faces.component.UIComponent;
import jakarta.faces.component.UIOutput;
import jakarta.faces.component.UIViewRoot;
import jakarta.faces.view.facelets.ComponentConfig;
import jakarta.faces.view.facelets.ComponentHandler;
import jakarta.faces.view.facelets.FaceletContext;
import java.io.IOException;
import java.util.Objects;

public class TailwindTagHandler extends ComponentHandler {
	private static final String KEY = "adeptumfaces.tailwind";
	private static final String LIB = "adeptumfaces";
	private static final String CSS = "tailwind.css";

	public TailwindTagHandler(ComponentConfig config) {
		super(config);
	}

	@Override
	public void apply(FaceletContext fc, UIComponent component) throws IOException {
		System.out.println("KÖR TAGHANDLERN?!?!?!");

		final UIViewRoot root = fc.getFacesContext().getViewRoot();

		if (Objects.isNull(root.getViewMap().get(KEY))) {
			final UIOutput css = (UIOutput) fc.getFacesContext().getApplication().createComponent(UIOutput.COMPONENT_TYPE);

			css.setRendererType("jakarta.faces.resource.Stylesheet");
			css.getAttributes().put("library", LIB);
			css.getAttributes().put("name", CSS);
			fc.getFacesContext().getViewRoot().addComponentResource(fc.getFacesContext(), css, "head");
			root.getViewMap().put(KEY, Boolean.TRUE);
		}

		super.apply(fc, component);
	}

}
