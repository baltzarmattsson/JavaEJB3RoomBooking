package t4.eao;

import java.util.ArrayList;

import javax.ejb.Local;

import t4.entities.Login;

@Local
public interface LoginEAOLocal {
	public ArrayList<Login> findAllLogins();
	public Login createLogin(Login login);
	public Login updateLogin(Login login);
	public void deleteLogin(String personId);
	public Login findLoginByPersonId(String personId);
}
