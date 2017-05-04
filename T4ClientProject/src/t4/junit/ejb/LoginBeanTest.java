package t4.junit.ejb;

import junit.framework.TestCase;
import javax.naming.Context;
import javax.naming.InitialContext;

import t4.eao.LoginEAOLocal;
import t4.eao.PersonEAOLocal;
import t4.entities.Person;
import t4.entities.Login;
import junit.framework.TestCase;

public class LoginBeanTest extends TestCase {

	LoginEAOLocal loginEAO;
		
	public LoginBeanTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		Context context = new InitialContext();
		
		loginEAO = (LoginEAOLocal)context.lookup("java:app/T4EJBProject/LoginEAOImpl!t4.eao.LoginEAOLocal");
		
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		loginEAO = null;
	}
	
	public void testLoginMethodPerson() throws Exception {
		
		Person p = new Person();
		p.setId("SYSA16aa");
		
		Login login = new Login();
		login.setPerson(p);
		
		assertNotNull(login.getPerson());
		assertEquals(login.getPerson().getId(), "SYSA16aa");
	}
	
	public void testLoginMethodPassword() throws Exception {
		Login login = new Login();
		login.setPassword("Lund123");
		assertEquals(login.getPassword(), "Lund123");
		
	}
}
