package com.adeptumadeptumfaces.showcase.model;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@ApplicationScoped
public class ProductService {
        
        private List<Product> products;

        public ProductService() {
                init();
        }

        private void init() {
                products = new ArrayList<>();

                products.add(new Product(1000, "f230fh0g3", "Bamboo Watch", "Product Description", "bamboo-watch.jpg", 65,
                        "Accessories", 24, "INSTOCK", 5));
                products.add(new Product(1001, "nvklal433", "Black Watch", "Product Description", "black-watch.jpg", 72,
                        "Accessories", 61, "INSTOCK", 4));
                products.add(new Product(1002, "zz21cz3c1", "Blue Band", "Product Description", "blue-band.jpg", 79,
                        "Fitness", 2, "LOWSTOCK", 3));
                products.add(new Product(1003, "244wgerg2", "Blue T-Shirt", "Product Description", "blue-t-shirt.jpg", 29,
                        "Clothing", 25, "INSTOCK", 5));
                products.add(new Product(1004, "h456wer53", "Bracelet", "Product Description", "bracelet.jpg", 15,
                        "Accessories", 73, "INSTOCK", 4));
                products.add(new Product(1005, "av2231fwg", "Brown Purse", "Product Description", "brown-purse.jpg", 120,
                        "Accessories", 0, "OUTOFSTOCK", 4));
        }

        public List<Product> getProducts() {
                return new ArrayList<>(products);
        }

        public List<Product> getProducts(int size) {
                if (size > products.size()) {
                        Random rand = new Random();
                        List<Product> randomList = new ArrayList<>();

                for (int i = 0; i < size; i++) {
                        int randomIndex = rand.nextInt(products.size());
                        randomList.add(products.get(randomIndex));
                }

                return randomList;
                } else {
                        return new ArrayList<>(products.subList(0, size));
                }
        }

        public List<Product> getClonedProducts(int size) {
                List<Product> results = new ArrayList<>();
                List<Product> originals = getProducts(size);

                for (Product original : originals) {
                        Product clone = original.clone();
                        clone.setCode(generateCode());
                        results.add(clone);
                }

                return results;
        }

        private String generateCode() {
                return UUID.randomUUID().toString().replace("-", "").substring(0, 8);
        }
        
}
