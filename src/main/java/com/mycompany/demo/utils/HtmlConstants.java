/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.demo.utils;

import java.util.Set;

public final class HtmlConstants {
        
        private HtmlConstants() {
        
                // Utility class

        }

    // Button CSS classes
    public static final String BUTTON_TEXT_ONLY = "ui-button-text-only";
    public static final String BUTTON_TEXT_ICON_LEFT = "ui-button-text-icon-left";
    public static final String BUTTON_TEXT_ICON_RIGHT = "ui-button-text-icon-right";
    public static final String BUTTON_ICON_ONLY = "ui-button-icon-only";

    public static final String BUTTON_LEFT_ICON = "ui-button-icon-left";
    public static final String BUTTON_RIGHT_ICON = "ui-button-icon-right";
    public static final String BUTTON_TEXT = "ui-button-text";

    // Pass-through attributes
    public static final Set<String> BUTTON_WITHOUT_CLICK_ATTRS = Set.of(
            "style", "title", "tabindex", "accesskey"
    );
        
}
