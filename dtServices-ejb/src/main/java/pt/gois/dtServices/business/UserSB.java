package pt.gois.dtServices.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import pt.gois.dtServices.entity.TipoDeUser;
import pt.gois.dtServices.entity.User;

@Stateless
public class UserSB extends GeneralSB<User> implements UserSBLocal{

	public UserSB() {
		super(User.class);
	}

	@Override
	public User findByUsername(String username) {
		Query query = getEM().createQuery("SELECT u FROM User u WHERE u.username = :username");
		query.setParameter("username", username);
		User user = (User)query.getSingleResult();
		return user;
	}
	
	@SuppressWarnings("unchecked")
	public List<TipoDeUser> getTipoDeUsers(){
		Query query = getEM().createQuery("SELECT tu FROM TipoDeUser tu WHERE tu.id > 0");
		return (List<TipoDeUser>)query.getResultList();
	}
	
}
