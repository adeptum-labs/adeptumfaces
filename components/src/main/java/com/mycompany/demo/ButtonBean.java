/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.demo;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Named
@RequestScoped
public class ButtonBean {
    
    private ButtonModel buttonModel =
            new ButtonModel("Click me", false);
    
    public ButtonModel getButtonModel(){
        return buttonModel;
    }
    
    public void onClick(){
        System.out.println("Button clicked");
    }
}
