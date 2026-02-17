/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.adeptum.adeptumfaces.util;



public final class StringUtils {
        
        private StringUtils(){}
        
        public static boolean isBlank(String value) {
                return value == null || value.trim().isEmpty();
        }
        
        public static boolean isNotBlank(String value) {
                return !isBlank(value);
        }
        
}
