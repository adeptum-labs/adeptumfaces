package com.mycompany.demo.datatable;

import java.util.ArrayList;
import java.util.List;

public class TableRepository {

    private static final List<ListModel> DATA = new ArrayList<>();

    static {
        for (long i = 1; i <= 100; i++) {
            DATA.add(new ListModel(i, "Item " + i));
        }
    }

    public List<ListModel> find(int first, int pageSize) {
        int toIndex = Math.min(first + pageSize, DATA.size());
        return DATA.subList(first, toIndex);
    }

    public int count() {
        return DATA.size();
    }
}
