/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.demo.utils;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;

import java.util.List;
import java.util.Map;

public class FacesComponentUtils {
        
        private FacesComponentUtils(){}
        
        public static Map<String, List<String>> getRequestParams() {
        return FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestParameterValuesMap();
    }
        
}
