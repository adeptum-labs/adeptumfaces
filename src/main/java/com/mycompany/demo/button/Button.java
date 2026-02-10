/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.demo.Button;

import com.mycompany.demo.utils.HtmlConstants;
import com.mycompany.demo.utils.StringUtils;
import com.mycompany.demo.utils.FacesComponentUtils;

import jakarta.faces.component.FacesComponent;
import java.util.List;
import java.util.Map;

@FacesComponent(value = Button.COMPONENT_TYPE)
public class Button extends ButtonBase {

        public static final String COMPONENT_TYPE = "demo.Button";

        public String resolveStyleClass() {
                String icon = getIcon();
                Object value = getValue();
                String styleClass = "";

                if (value != null && StringUtils.isBlank(icon)) {
                        styleClass = HtmlConstants.BUTTON_TEXT_ONLY;
                }
                
                else if (value != null && StringUtils.isNotBlank(icon)) {
                        styleClass = getIconPos().equals("left")
                        ? HtmlConstants.BUTTON_TEXT_ICON_LEFT
                        : HtmlConstants.BUTTON_TEXT_ICON_RIGHT;
                }
                
                else if (value == null && StringUtils.isNotBlank(icon)) {
                        styleClass = HtmlConstants.BUTTON_ICON_ONLY;
                }

                if (isDisabled()) {
                        styleClass += " ui-state-disabled";
                }

                if (getStyleClass() != null) {
                        styleClass += " " + getStyleClass();
                }

                return styleClass.trim();
        }

        public Map<String, List<String>> getParams() {
                return FacesComponentUtils.getRequestParams();
        }
}