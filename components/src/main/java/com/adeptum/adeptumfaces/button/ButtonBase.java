/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.adeptum.adeptumfaces.button;

import jakarta.faces.component.html.HtmlOutcomeTargetButton;


public abstract class ButtonBase extends HtmlOutcomeTargetButton {

        public static final String COMPONENT_FAMILY = HtmlOutcomeTargetButton.COMPONENT_FAMILY;
    
        @Override
        public String getFamily(){
                return COMPONENT_FAMILY;
        }
}
