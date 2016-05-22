package pt.gois.dtServices.business;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pt.gois.dtServices.util.SearchPageCtrl;

public abstract class GeneralSB<T> implements GeneralSBLocal<T> {
	private Class<T> entityClass;

	@PersistenceContext(unitName = "recover_house")
	private EntityManager em;

	public GeneralSB(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	protected EntityManager getEM() {
		return em;
	}

	public void create(Object entity) {
		getEM().persist(entity);
	}

	public void save(Object entity) {
		getEM().merge(entity);
	}

	public void delete(Object entity) {
		getEM().remove(getEM().merge(entity));
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
			if( searchPageCtrl.getJpql() != null ){
				queryJPQL = searchPageCtrl.getJpql();
			}else{
				queryJPQL = "SELECT distinct obj FROM " + entityClass.getName() + " obj where 1 = 1 ";
			}
			
			if( searchPageCtrl.getJpqlCount() != null ){
				queryJPQLCount = searchPageCtrl.getJpqlCount();
			}else{
				queryJPQLCount = "SELECT count(distinct obj) FROM " + entityClass.getName() + " obj where 1 = 1 ";
			}

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
			queryJPQL = queryJPQL + filter;
			queryJPQLCount = queryJPQLCount + filter;

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

			Query queryCount = getEM().createQuery(queryJPQLCount);

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
		}
		return searchPageCtrl;
	}

}
