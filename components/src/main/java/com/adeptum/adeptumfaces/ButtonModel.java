/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.adeptum.adeptumfaces;

public class ButtonModel {
    
    private String label;
    private boolean disabled;
    
    public ButtonModel(String label, boolean disabled) {
        this.label = label;
        this.disabled = disabled;
    }
    
    public String getLabel() {
        return label;
    }
    
    public boolean isDisabled(){
        return disabled;
    }
}
