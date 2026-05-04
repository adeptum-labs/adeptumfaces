package com.adeptum.adeptumfaces.datatable;

import jakarta.faces.component.UIComponentBase;
import jakarta.faces.component.FacesComponent;

@FacesComponent(value = DataTable.COMPONENT_TYPE)
public class DataTable extends UIComponentBase {
        
        public static final String COMPONENT_TYPE = "com.adeptum.adeptumfaces.datatable.DataTable";
        public static final String COMPONENT_FAMILY = "com.adeptum.adeptumfaces.datatable";
        public static final String DEFAULT_RENDERER = "com.adeptum.adeptumfaces.datatable.DataTableRenderer";

        @Override
        public String getFamily() {
                return COMPONENT_FAMILY;
        }
        
        @Override
        public String getRendererType() {
                return DEFAULT_RENDERER;
        }
        
        @Override
        public boolean getRendersChildren(){
                return true;
        }

        public Object getValue() {
                return getStateHelper().eval("value");
        }

        public void setValue(Object value) {
                getStateHelper().put("value", value);
        }

        public String getVar() {
                return (String) getStateHelper().eval("var");
        }

        public void setVar(String var) {
                getStateHelper().put("var", var);
        }
        
        public int getRows(){
                return (int) getStateHelper().eval("rows", 5);
        }
        
        public void setRows(int rows){
                getStateHelper().put("rows", rows);
        }
        
        public int getPage() {
                return (int) getStateHelper().eval("page", 0);
        }

        public void setPage(int page) {
                getStateHelper().put("page", page);
        }
        
        public void nextPage(){
                int page = getPage();
                setPage (page + 1);
        }
        
        public void previousPage(){
                int page = getPage();
                if (page > 0){
                        setPage(page - 1);
                }
        }
}
