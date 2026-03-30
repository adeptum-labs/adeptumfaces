
package com.adeptum.adeptumfaces.datatable;

import java.io.Serializable;
import java.util.List;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;


@Named("basicView")
@ViewScoped
public class BasicView implements Serializable {
        
	private List<Product> products;

        @Inject
        private ProductService service;

        @PostConstruct
        public void init() {
                products = service.getProducts(10);
        }

        public List<Product> getProducts() {
                return products;
        }

        public void setService(ProductService service) {
                this.service = service;
        }
}
