/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.adeptum.adeptumfaces.util;

import jakarta.faces.context.FacesContext;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class FacesComponentUtils {

    private FacesComponentUtils() {}

    public static Map<String, List<String>> getRequestParams() {
        FacesContext context = FacesContext.getCurrentInstance();

        if (context == null) {
            return Map.of();
        }

        return context
                .getExternalContext()
                .getRequestParameterValuesMap()
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> Arrays.asList(entry.getValue())
                ));
    }

    public static Map<String, String> getRequestParamMap() {
        FacesContext context = FacesContext.getCurrentInstance();

        if (context == null) {
            return Map.of();
        }

        return context
                .getExternalContext()
                .getRequestParameterMap();
    }
}