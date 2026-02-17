package com.adeptum.adeptumfaces.button;

import com.adeptum.adeptumfaces.util.FacesComponentUtils;
import jakarta.faces.component.FacesComponent;
import jakarta.faces.component.html.HtmlOutcomeTargetButton;
import java.util.List;
import java.util.Map;

@FacesComponent(value = Button.COMPONENT_TYPE)
public class Button extends HtmlOutcomeTargetButton {
        public static final String COMPONENT_TYPE = "com.adeptum.adeptumfaces.button.Button";

        public Map<String, List<String>> getParams() {
                return FacesComponentUtils.getRequestParams();
        }
}