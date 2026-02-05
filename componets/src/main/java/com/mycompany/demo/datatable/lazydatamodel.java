package com.mycompany.demo.datatable;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

import org.primefaces.model.LazyDataModel;
import com.mycompany.demo.ListModel;

@Named
@RequestScoped
public class lazydatamodel {

    private LazyDataModel<ListModel> lazyModel;

    
}
