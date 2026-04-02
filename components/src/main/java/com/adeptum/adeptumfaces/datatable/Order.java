package com.adeptum.adeptumfaces.datatable;

import java.io.Serializable;
import java.util.Objects;

public class Order implements Serializable{
        
        private final int number;
        private final String imagePath;

        public Order(int number, String imagePath) {
                this.number = number;
                this.imagePath = imagePath;
        }

        public int getNumber() {
                return number;
        }

        public String getImagePath() {
                return imagePath;
        }

        public boolean isValid () {
                return number > 0; 
                
        }

        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (!(o instanceof Order)) return false;
                Order order = (Order) o;
                return number == order.number;
        }

        @Override
        public int hashCode() {
                return Objects.hash(number);
        }
        
}
