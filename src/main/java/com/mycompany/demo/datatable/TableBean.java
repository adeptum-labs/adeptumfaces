package com.mycompany.demo.datatable;

import com.mycompany.demo.utils.Page;
import com.mycompany.demo.utils.PaginationUtils;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class TableBean {

    public Page<ListModel> getItems() {
        return Page.of(
            new ListModel(1L, "NAME"),
            new ListModel(2L, "TEST")
        );
    }
}
