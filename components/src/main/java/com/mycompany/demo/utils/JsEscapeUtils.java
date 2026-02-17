/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.demo.utils;

        /**
         * Utility method att hoppa över från Javascript values,
         */

public final class JsEscapeUtils {
        
        private JsEscapeUtils() {
            // Utility class
        }

    public static String escape(String value) {
        if (value == null) {
            return null;
        }
        return value
                .replace("\\", "\\\\")
                .replace("'", "\\'")
                .replace("\"", "\\\"");
    }
    
}
