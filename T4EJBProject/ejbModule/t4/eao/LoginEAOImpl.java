package t4.eao;

import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import t4.entities.Login;

@Stateless
public class LoginEAOImpl implements LoginEAOLocal {

	@PersistenceContext(unitName="MSSQLT4")
	private EntityManager em;
	
    public LoginEAOImpl() {
    }
    
    public ArrayList<Login> findAllLogins() {
    	Query query = em.createQuery("select l from Login l");
    	return (ArrayList<Login>) query.getResultList();
    }

    public Login createLogin(Login login) {
    	em.persist(login);
    	return login;
    }
    
    public Login updateLogin(Login login) {
    	em.merge(login);
    	return login;
    }
    
    public void deleteLogin(String personId) {
    	Login login = this.findLoginByPersonId(personId);
    	if (login != null)
    		em.remove(login);
    }
    
    public Login findLoginByPersonId(String personId) {
    	return em.find(Login.class, personId);
    }
}
