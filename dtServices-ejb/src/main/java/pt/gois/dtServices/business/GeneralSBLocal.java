package pt.gois.dtServices.business;

import java.util.List;

import javax.ejb.EJBException;
import javax.ejb.Local;

import pt.gois.dtServices.util.SearchPageCtrl;

@Local
public interface GeneralSBLocal<T> {
	
	public static final String MSG_FATAL_ERRO = "Erro desconhecido. Favor contactar o administrador do sistema.";
	public static final String CONSTRAINT_VIOLATION_EXCEPTION = "org.hibernate.exception.ConstraintViolationException";

	public boolean isCauseException(String nomeClasseException, EJBException e);
	
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
