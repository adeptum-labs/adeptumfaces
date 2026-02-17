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

    private FacesComponentUtils() {
    }

    public static Map<String, List<String>> getRequestParams() {
	return FacesContext.getCurrentInstance().getExternalContext()
                .getRequestParameterValuesMap().entrySet().stream().collect(
		Collectors.toMap(Map.Entry::getKey, v -> Arrays.asList(v.getValue())
	));
    }

    public static Map<String, String> getRequestParamMap() {
        return FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap();
    }
}
