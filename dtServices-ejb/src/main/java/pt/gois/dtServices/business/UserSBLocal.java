package pt.gois.dtServices.business;

import java.util.List;

import javax.ejb.Local;

import pt.gois.dtServices.entity.TipoDeUser;
import pt.gois.dtServices.entity.User;

@Local
public interface UserSBLocal extends GeneralSBLocal<User>{
    User findByUsername(String username);
    
    List<TipoDeUser> getTipoDeUsers();
}
