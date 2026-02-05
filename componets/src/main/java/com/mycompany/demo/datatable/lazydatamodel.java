package com.mycompany.demo.datatable;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import com.mycompany.demo.ListModel;

@Named
@RequestScoped
public class lazydatamodel {

    private LazyDataModel<ListModel> lazyModel;

    public LazyTableBean() {

        lazyModel = new LazyDataModel<ListModel>() {
            @Override
            public List <ListModel> load(int first, int pageSize, String sortFiled, sortOrder , Map <String, Object> Fileters){

                List<ListModel> allItems = new TableBean().getItems();
                
            
                
            }
        };

       
    }

    
}
