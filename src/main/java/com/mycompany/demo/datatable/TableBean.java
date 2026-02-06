package com.mycompany.demo.datatable;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class TableBean {

    public List<ListModel> getItems() {
        return List.of(
            new ListModel(1L, "NAME"),
            new ListModel(2L, "TEST")
        );
    }
}
