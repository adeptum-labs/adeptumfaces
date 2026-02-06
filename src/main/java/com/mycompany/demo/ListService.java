/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.demo;
import java.util.List;
import java.util.Arrays;

public class ListService {
    
    public List<ListModel> findALL(){
        return Arrays.asList(
            new ListModel(1L, "NAME"),
            new ListModel(2L, "TEST")
            );
    }
    
}
