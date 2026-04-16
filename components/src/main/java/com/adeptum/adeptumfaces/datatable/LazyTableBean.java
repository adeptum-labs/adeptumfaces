
package com.adeptum.adeptumfaces.datatable;

import java.util.List;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Named
@RequestScoped
public class LazyTableBean {
	/*private final TableRepository repository = new TableRepository();

	private int first = 0;
	private int pageSize = 10;

	public Page<ListModel> loadPage() {
		return repository.findPage(first, pageSize);
	}

	public int getFirst() {
		return first;
	}

	public void setFirst(int first) {
		this.first = first;
	}
		public int getDataCount(List<?> data) {
    if (data == null) {
        return 0;
    }
    return data.size();
}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}*/

	public boolean hasData(List <?> data) {
		return data != null && !data.isEmpty();
	}

	public int getDataCount(List<?> data) {
    if (data == null) {
        return 0; // list finns ej och reurnera 0
    }
    return data.size(); // returnera antal rader på list
	}

}
