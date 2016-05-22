package pt.gois.dtServices.business;

import java.util.List;

import javax.ejb.Local;

import pt.gois.dtServices.util.SearchPageCtrl;

@Local
public interface GeneralSBLocal<T> {

    void create(Object t);

    void save(Object t);

    void delete(Object t);
    
    T findById(Object id);
    
    @SuppressWarnings("hiding")
	<T> T findByID(Class<T> c, Object id );

    List<T> findAll();

    @SuppressWarnings("hiding")
	<T> SearchPageCtrl<T> find(SearchPageCtrl<T> searchPageCtrl);

    int count();
    
}
