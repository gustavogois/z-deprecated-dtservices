package pt.gois.dtServices.business;

import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pt.gois.dtServices.util.SearchPageCtrl;

public abstract class GeneralSB<T> implements GeneralSBLocal<T> {
	private Class<T> entityClass;

	@PersistenceContext(unitName = "dtServices")
	private EntityManager em;
	
	@EJB
	private LogSBLocal sbLog;

	public GeneralSB(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	protected EntityManager getEM() {
		return em;
	}

	public LogSBLocal getSbLog() {
	    return sbLog;
	}

	public void setSbLog(LogSBLocal sbLog) {
	    this.sbLog = sbLog;
	}

	public void create(Object entity) {
		getEM().persist(entity);
		getSbLog().writeLog( entity.getClass().getName() + " %s criada", new Object[] { entity });
	}

	public void save(Object entity) {
		getEM().merge(entity);
		getSbLog().writeLog( entity.getClass().getName() + " %s alterada", new Object[] { entity });
	}

	public void delete(Object entity) {
		getEM().remove(getEM().merge(entity));
		getSbLog().writeLog( entity.getClass().getName() + " %s removida", new Object[] { entity });
	}

	public T findById(Object id) {
		if( id != null ){
			return getEM().find(entityClass, id);
		}
		return null;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<T> findAll() {
		javax.persistence.criteria.CriteriaQuery cq = getEM().getCriteriaBuilder().createQuery();
		cq.select(cq.from(entityClass));
		return getEM().createQuery(cq).getResultList();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public int count() {
		javax.persistence.criteria.CriteriaQuery cq = getEM().getCriteriaBuilder().createQuery();
		javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
		cq.select(getEM().getCriteriaBuilder().count(rt));
		javax.persistence.Query q = getEM().createQuery(cq);
		return ((Long) q.getSingleResult()).intValue();
	}

	@SuppressWarnings("hiding")
	@Override
	public <T> T findByID(Class<T> c, Object id ){
		return (T)em.find(c, id);
	}

	@SuppressWarnings({ "unchecked", "hiding" })
	public <T> SearchPageCtrl<T> find(SearchPageCtrl<T> searchPageCtrl) {

		String queryJPQL;
		String queryJPQLCount;
		try {
			String classeName = ( (ParameterizedType)this.getClass().getGenericSuperclass() ).getActualTypeArguments()[0].getTypeName();
			queryJPQL = "SELECT distinct obj FROM " + classeName + " obj where 1 = 1 ";
			queryJPQLCount = "SELECT count(distinct obj) FROM " + classeName + " obj where 1 = 1 ";

			String filter = "";
			Map<String,Object> filters = searchPageCtrl.getFilters();
			if (filters != null){
				for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
					if( filter.length() != 0 ){
						filter += searchPageCtrl.getAndFilter()? " and ": " or ";
					}
					String filterProperty = it.next();
					Object filterValue = filters.get(filterProperty);
					String param = filterProperty.replace('.', '_');
					if( filterValue instanceof String ){
						filter += filterProperty + " like :" + param + " ";
					}else{
						filter += filterProperty + " = :" + param + " ";
					}
				}
			}

			if( filter.length() > 0 ){
				filter = " and ( " + filter + " ) ";
			}
			
			queryJPQL += filter;
			queryJPQLCount += filter;
			
			filter = "";
			Map<String,Object> textualFilters = searchPageCtrl.getTextualFilters();
			if (filters != null){
				for (Iterator<String> it = textualFilters.keySet().iterator(); it.hasNext();) {
					if( filter.length() != 0 ){
						filter += searchPageCtrl.getAndFilter()? " and ": " or ";
					}
					String filterProperty = it.next();
					Object filterValue = textualFilters.get(filterProperty);
					filter += filterProperty.replaceAll( ":query", "'" + filterValue.toString() + "'" );
				}
			}

			if( filter.length() > 0 ){
				filter = " and ( " + filter + " ) ";
			}
			
			queryJPQL += filter;
			queryJPQLCount += filter;
			// sort
			Map<String,String> fieldToSort = searchPageCtrl.getFieldToSort();
			if (fieldToSort.size() > 0) {
				String orderBy = "";
				for( String field : fieldToSort.keySet() ){
					String sortOrder = fieldToSort.get(field);
					if( orderBy.length() > 0 ){
						orderBy += ",";
					}
					orderBy += field + " " + sortOrder;
				}
				queryJPQL += "order by " + orderBy;
			}
			searchPageCtrl.setJpql(queryJPQL);
			searchPageCtrl.setJpqlCount(queryJPQLCount);
			
			Query queryCount = getEM().createQuery(queryJPQLCount);

			getSbLog().writeLog("Pesquisaando: %s - Params( %s )", new Object[] { searchPageCtrl.getJpql(), searchPageCtrl.getFilters() }); 

			Query query = getEM().createQuery(queryJPQL);
			if (filters != null){
				for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
					String filterProperty = it.next();
					Object filterValue = filters.get(filterProperty);
					String param = filterProperty.replace('.', '_');
					if( filterValue instanceof Integer ){
						query.setParameter(param, (Integer)filterValue);
						queryCount.setParameter(param, (Integer)filterValue);
					}else if( filterValue instanceof Date ){
						query.setParameter(param, (Date)filterValue);
						queryCount.setParameter(param, (Date)filterValue);
					}else if( filterValue instanceof Boolean ){
						query.setParameter(param, (Boolean)filterValue);
						queryCount.setParameter(param, (Boolean)filterValue);
					}else {
						query.setParameter(param, (String)filterValue);
						queryCount.setParameter(param, (String)filterValue);
					}
				}
			}
			
			Integer count = ( (Long) queryCount.getSingleResult() ).intValue();

			// paginate
			Integer position = searchPageCtrl.getPosition();
			if ((position > count) || (count == 0)) {
				position = 0;
			}
			if( searchPageCtrl.getPageSize() > 0 ){
				query.setMaxResults(searchPageCtrl.getPageSize());
			}
			query.setFirstResult(position);
			
			searchPageCtrl.setRows( query.getResultList() );
			searchPageCtrl.setCount(count);
		} catch (Exception e) {
			e.printStackTrace();
			getSbLog().writeLog("Pesquisando: %s - %s Erro: %s", new Object[] { searchPageCtrl.getJpql(), searchPageCtrl.getFilters(), e.getMessage() }); 
		}
		return searchPageCtrl;
	}


}
