package pt.gois.dtServices.business;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import pt.gois.dtServices.entity.EstadosProcesso;

public class TestaJPQL {

	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence
                .createEntityManagerFactory("financas");

		EntityManager manager = entityManagerFactory.createEntityManager();
		
		String sQuery = "select * from EstadosProcesso";
		TypedQuery<EstadosProcesso> query = manager.createQuery(sQuery, EstadosProcesso.class);
		List<EstadosProcesso> resultList = query.getResultList();
		
		for (EstadosProcesso estadosProcesso : resultList) {
			System.out.println(estadosProcesso);
		}
		
		manager.close();

	}

}
