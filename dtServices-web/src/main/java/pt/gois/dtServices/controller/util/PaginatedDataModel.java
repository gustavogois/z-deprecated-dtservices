package pt.gois.dtServices.controller.util;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.model.SortOrder;

import pt.gois.dtServices.business.GeneralSBLocal;
import pt.gois.dtServices.util.SearchPageCtrl;

public class PaginatedDataModel<T> extends org.primefaces.model.LazyDataModel<T> {
	private static final long serialVersionUID = 1L;

	static Log logger = LogFactory.getLog(PaginatedDataModel.class);

	SearchPageCtrl<T> searchPageCtrl;
	GeneralSBLocal<T> generalSB;

	public PaginatedDataModel(SearchPageCtrl<T> searchPageCtrl, GeneralSBLocal<T> generalSB) {
		this.searchPageCtrl = searchPageCtrl;
		this.generalSB = generalSB;
	}

	@Override
	public T getRowData(String rowKey) {
		try {
			logger.debug(rowKey);
			return null;// TODO
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Object getRowKey(T item) {
		Method m = null;
		try {
			m = item.getClass().getMethod("getId", new Class[] {});
			return "" + m.invoke(item, new Object[] {});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<T> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,Object> filters) {
		searchPageCtrl.setPosition(first);
		searchPageCtrl.setPageSize(pageSize);
//		searchPageCtrl.setFilters(filters);
		searchPageCtrl = generalSB.find(searchPageCtrl);
		return searchPageCtrl.getRows();
	}
	@Override
	public int getRowCount(){
		return searchPageCtrl.getCount();
	}

	public SearchPageCtrl<T> getSearchPageCtrl() {
		return searchPageCtrl;
	}

	public void setSearchPageCtrl(SearchPageCtrl<T> searchPageCtrl) {
		this.searchPageCtrl = searchPageCtrl;
	}

	public GeneralSBLocal<T> getGeneralSB() {
		return generalSB;
	}

	public void setGeneralSB(GeneralSBLocal<T> generalSB) {
		this.generalSB = generalSB;
	}
}
