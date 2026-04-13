package com.adeptum.adeptumfaces.datatable;

import jakarta.faces.component.UIComponent;
import jakarta.faces.component.UIColumn;
import jakarta.faces.context.FacesContext;
import jakarta.faces.context.ResponseWriter;
import jakarta.faces.render.FacesRenderer;
import jakarta.faces.render.Renderer;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@FacesRenderer(
    componentFamily = "com.adeptum.adeptumfaces.datatable",
    rendererType = "com.adeptum.adeptumfaces.datatable.DataTableRenderer"
)

public class DataTableRenderer extends Renderer {
        
        @Override
        public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
                ResponseWriter writer = context.getResponseWriter();

                writer.startElement("table", component);
                writer.writeAttribute("border", "1", null);

                renderHeader(context, component);
        }

        @Override
        public void encodeChildren(FacesContext context, UIComponent component) throws IOException {
                DataTable table = (DataTable) component;

                List<?> data = (List<?>) table.getValue();
                String var = table.getVar();
                
                int rows = table.getRows();
                int page = Math.max(0, table.getPage());

                int totalPages = (int) Math.ceil((double) data.size() / rows);

                if (page >= totalPages) {
                        page = 0;
                        table.setPage(0);
                }

                int start = page * rows;
                int end = Math.min(start + rows, data.size());

                if (data == null || var == null) {
                        return;
                }

                ResponseWriter writer = context.getResponseWriter();
                Map<String, Object> requestMap = context.getExternalContext().getRequestMap();

                writer.startElement("tbody", null);

                for (int i = start; i < end; i++) /*for (Object row : data)*/ {
                        Object row = data.get(i);
                        Object oldVar = requestMap.put(var, row);

                        writer.startElement("tr", null);

                        for (UIComponent child : component.getChildren()) {
                                if (child instanceof UIColumn) {
                                        UIColumn column = (UIColumn) child;

                                        writer.startElement("td", null);

                                        for (UIComponent cellChild : column.getChildren()) {
                                                cellChild.encodeAll(context);
                                        }

                                        writer.endElement("td");
                                }
                        }

                        writer.endElement("tr");

                        if (oldVar != null) {
                                requestMap.put(var, oldVar);
                        }
                        else {
                                requestMap.remove(var);
                        }
                }

                writer.endElement("tbody");
        }
        
        @Override
        public void decode(FacesContext context, UIComponent component) {
                DataTable table = (DataTable) component;

                String pageParam = context.getExternalContext()
                        .getRequestParameterMap()
                        .get("page");

                if (pageParam != null) {
                        try {
                                table.setPage(Integer.parseInt(pageParam));
                        }
                        catch (NumberFormatException e) {
                                table.setPage(0);
                        }
                }
}

        /*@Override
        public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
                renderFooter(context, component);

                context.getResponseWriter().endElement("table");
        }

        @Override
        public boolean getRendersChildren() {
                return true;
        }*/
        @Override
        public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
                DataTable table = (DataTable) component;
                List<?> data = (List<?>) table.getValue();

                renderFooter(context, component);

                ResponseWriter writer = context.getResponseWriter();

                writer.endElement("table");

                if (data == null || data.isEmpty()) {
                        return;
                }

                int rows = table.getRows();
                int currentPage = table.getPage();
                int totalPages = (int) Math.ceil((double) data.size() / rows);

                writer.startElement("div", null);
                writer.writeAttribute("style", "margin-top:10px;", null);

                if (currentPage > 0) {
                        writer.startElement("a", null);
                        writer.writeAttribute("href", "?page=" + (currentPage - 1), null);
                        writer.writeText("Previous ", null);
                        writer.endElement("a");
                }

                writer.writeText(" Page " + (currentPage + 1) + " of " + totalPages + " ", null);

                if (currentPage < totalPages - 1) {
                        writer.startElement("a", null);
                        writer.writeAttribute("href", "?page=" + (currentPage + 1), null);
                        writer.writeText("Next", null);
                        writer.endElement("a");
                }

                writer.endElement("div");
        }

        private void renderHeader(FacesContext context, UIComponent component) throws IOException {
                ResponseWriter writer = context.getResponseWriter();

                UIComponent tableHeader = component.getFacet("header");

                writer.startElement("thead", null);

                if (tableHeader != null) {
                        writer.startElement("tr", null);
                        writer.startElement("th", null);
                        writer.writeAttribute("colspan", component.getChildCount(), null);

                        tableHeader.encodeAll(context);

                        writer.endElement("th");
                        writer.endElement("tr");
                }

                writer.startElement("tr", null);

                for (UIComponent child : component.getChildren()) {
                        if (child instanceof UIColumn) {
                                UIColumn column = (UIColumn) child;

                                writer.startElement("th", null);

                                UIComponent headerFacet = column.getFacet("header");
                                if (headerFacet != null) {
                                        headerFacet.encodeAll(context);
                                }

                                writer.endElement("th");
                        }
                }

                writer.endElement("tr");
                writer.endElement("thead");
        }

        private void renderFooter(FacesContext context, UIComponent component) throws IOException {
                ResponseWriter writer = context.getResponseWriter();

                UIComponent tableFooter = component.getFacet("footer");

                if (tableFooter == null) {
                        return;
                }

                writer.startElement("tfoot", null);
                writer.startElement("tr", null);

                writer.startElement("td", null);
                writer.writeAttribute("colspan", component.getChildCount(), null);

                tableFooter.encodeAll(context);

                writer.endElement("td");
                writer.endElement("tr");
                writer.endElement("tfoot");
        }
}
