package com.mycompany.demo.datatable;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.FilterMeta;

import java.util.List;
import java.util.Map;

@Named
@RequestScoped
public class LazyTableBean {

    private LazyDataModel<ListModel> lazyModel;
    private TableRepository repository = new TableRepository();

    public LazyTableBean() {
        lazyModel = new LazyDataModel<>() {

            @Override
            public List<ListModel> load(int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
                List<ListModel> page = repository.find(first, pageSize);
                setRowCount(repository.count());
                return page;
            }
        };
    }

    public LazyDataModel<ListModel> getLazyModel() {
        return lazyModel;
    }
}
