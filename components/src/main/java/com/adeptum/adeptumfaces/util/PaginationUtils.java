/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.adeptum.adeptumfaces.util;


import java.util.List;

public class PaginationUtils {
        
        public static <T> Page<T> paginate(List<T> data, int first, int pageSize) {
                int total = data.size();
             if (first > total) {
                first = 0;
                }

                int toIndex = Math.min(first + pageSize, total);
                
                List<T> pageContent = data.subList(first, toIndex);

                return new Page<>(pageContent, total, pageSize, first);
        }
        
}
