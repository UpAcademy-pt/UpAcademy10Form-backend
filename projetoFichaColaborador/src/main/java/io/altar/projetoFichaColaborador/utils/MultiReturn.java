package io.altar.projetoFichaColaborador.utils;

import java.util.List;

public class MultiReturn<T> {

	private long filterSize;
	private List<T> object;

	public MultiReturn(List<T> object, long filterSize) {
		this.object = object;
		this.filterSize = filterSize;
	}

	public long getFilterSize() {
		return filterSize;
	}

	public List<T> getObject() {
		return object;
	}
	
	public void setObject(List<T> object) {
		this.object = object;
	}
	
	public void setFilterSize(long filterSize) {
		this.filterSize = filterSize;
	}
}
