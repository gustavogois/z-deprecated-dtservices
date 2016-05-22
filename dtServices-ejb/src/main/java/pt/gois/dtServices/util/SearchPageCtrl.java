package pt.gois.dtServices.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchPageCtrl<T> {
	String jpql;
	String jpqlCount;
	List<T> rows;
	Integer count = 0;
	Integer pageSize = 20;
	Integer position = 0;
	Map<String, String> fieldToSort = new HashMap<String,String>();
	Map<String, Object> filters = new HashMap<String,Object>();
	Boolean andFilter = false;

	public String getJpql() {
		return jpql;
	}
	public void setJpql(String jpql) {
		this.jpql = jpql;
	}
	public String getJpqlCount() {
		return jpqlCount;
	}
	public void setJpqlCount(String jpqlCount) {
		this.jpqlCount = jpqlCount;
	}
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getPosition() {
		return position;
	}
	public void setPosition(Integer position) {
		this.position = position;
	}
	public Map<String, String> getFieldToSort() {
		return fieldToSort;
	}
	public void setFieldToSort(Map<String, String> fieldToSort) {
		this.fieldToSort = fieldToSort;
	}
	public Map<String, Object> getFilters() {
		return filters;
	}
	public void setFilters(Map<String, Object> filters) {
		this.filters = filters;
	}
	public Boolean getAndFilter() {
		return andFilter;
	}
	public void setAndFilter(Boolean andFilter) {
		this.andFilter = andFilter;
	}
	
	
}
