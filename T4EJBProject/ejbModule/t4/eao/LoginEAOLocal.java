package t4.eao;

import javax.ejb.Local;

import t4.entities.Login;

@Local
public interface LoginEAOLocal {
	public Login createLogin(Login login);
	public Login updateLogin(Login login);
	public void deleteLogin(String personId);
	public Login findByPersonId(String personId);
}
