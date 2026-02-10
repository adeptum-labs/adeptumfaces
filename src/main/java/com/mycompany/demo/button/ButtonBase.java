/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.demo.Button;

import jakarta.faces.component.html.HtmlOutcomeTargetButton;


public abstract class ButtonBase extends HtmlOutcomeTargetButton {

        public static final String COMPONENT_FAMILY = "demo.components";
    
        @Override
        public String getFamily(){
                return COMPONENT_FAMILY;
        }
    
        public abstract boolean isDisabled();
        public abstract String getIcon();
        public abstract String getIconPos();
        public abstract String getTarget();
        public abstract boolean isEscape();
        public abstract boolean isInline();
        public abstract String getAriaLabel();
}
