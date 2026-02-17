/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.demo.utils;

import java.util.List;


public class Page<T> {
        
        private final List<T> content;
        private final int totalElements;
        private final int pageSize;
        private final int first;
        
        public Page(List<T> content, int totalElements, int pageSize, int first) {
                this.content = content;
                this.totalElements = totalElements;
                this.pageSize = pageSize;
                this.first = first;
        }
        
        public List<T> getContent() {
                return content;
        }

        public int getTotalElements() {
                return totalElements;
        }

        public int getPageSize() {
                return pageSize;
        }

        public int getFirst() {
                return first;
        }

        public int getTotalPages() {
                return (int) Math.ceil((double) totalElements / pageSize);
        }
}
