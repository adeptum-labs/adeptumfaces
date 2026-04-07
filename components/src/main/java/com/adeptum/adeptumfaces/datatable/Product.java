package com.adeptum.adeptumfaces.datatable;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Product implements Serializable{
        
        private int id;
        private String code;
        private String name;
        private String description;
        private String image;
        private double price;
        private String category;
        private int quantity;
        private String inventoryStatus;
        private int rating;
        private List<String> orders;

        public Product() {
        }

        public Product(int id, String code, String name, String description, String image,
                        double price, String category, int quantity,
                        String inventoryStatus, int rating) {
                this.id = id;
                this.code = code;
                this.name = name;
                this.description = description;
                this.image = image;
                this.price = price;
                this.category = category;
                this.quantity = quantity;
                this.inventoryStatus = inventoryStatus;
                this.rating = rating;
        }

        @Override
        public Product clone() {
                return new Product(id, code, name, description, image, price,
                                category, quantity, inventoryStatus, rating);
        }

        public int getId() { return id; }
        public void setId(int id) { this.id = id; }

        public String getCode() { return code; }
        public void setCode(String code) { this.code = code; }

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }

        public String getImage() { return image; }
        public void setImage(String image) { this.image = image; }

        public double getPrice() { return price; }
        public void setPrice(double price) { this.price = price; }

        public String getCategory() { return category; }
        public void setCategory(String category) { this.category = category; }

        public int getQuantity() { return quantity; }
        public void setQuantity(int quantity) { this.quantity = quantity; }

        public String getInventoryStatus() { return inventoryStatus; }
        public void setInventoryStatus(String inventoryStatus) { this.inventoryStatus = inventoryStatus; }

        public int getRating() { return rating; }
        public void setRating(int rating) { this.rating = rating; }

        public List<String> getOrders() { return orders; }
        public void setOrders(List<String> orders) { this.orders = orders; }

        @Override
        public int hashCode() {
                return Objects.hash(code);
        }

        @Override
        public boolean equals(Object obj) {
                if (this == obj) return true;
                if (!(obj instanceof Product)) return false;
                Product other = (Product) obj;
                return Objects.equals(code, other.code);
        }

        /**
        * Hämta om priset har kr på slutet
        * price = 99.99 → getFormattedPrice() returnerar "99.99 kr"
        * price = 200.0 → getFormattedPrice() returnerar "200.0 kr"
        */
        public String getFormattedPrice() {
        return price + " kr";
        }
        
        }
