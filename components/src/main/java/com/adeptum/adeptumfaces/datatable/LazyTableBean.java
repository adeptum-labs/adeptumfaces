
package com.adeptum.adeptumfaces.datatable;

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

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}*/
}
