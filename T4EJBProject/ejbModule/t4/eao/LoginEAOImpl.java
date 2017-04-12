package t4.eao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import t4.entities.Login;

@Stateless
public class LoginEAOImpl implements LoginEAOLocal {

	@PersistenceContext(unitName="MSSQLT4")
	private EntityManager em;
	
    public LoginEAOImpl() {
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
    	Login login = this.findByPersonId(personId);
    	if (login != null)
    		em.remove(login);
    }
    
    public Login findByPersonId(String personId) {
    	return em.find(Login.class, personId);
    }
}
