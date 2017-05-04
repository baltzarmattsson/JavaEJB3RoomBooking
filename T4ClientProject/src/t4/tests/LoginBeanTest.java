package t4.tests;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.junit.Test;

import t4.eao.LoginEAOLocal;
import t4.eao.PersonEAOLocal;
import t4.entities.Person;
import t4.entities.Login;
import junit.framework.TestCase;

public class LoginBeanTest extends TestCase {
	LoginEAOLocal loginEAO;
	PersonEAOLocal personEAO;

	public LoginBeanTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		Context context = new InitialContext();
		loginEAO = (LoginEAOLocal) context.lookup("java:app/T4EJBProject/LoginEAOImpl!t4.eao.LoginEAOLocal");
		personEAO = (PersonEAOLocal) context.lookup("java:app/T4EJBProject/PersonEAOImpl!t4.eao.PersonEAOLocal");
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		loginEAO = null;
	}
	
	@Test
	public void test() throws Exception {
		assertEquals("javaSucksDick", "javaSucksDick");
	}

	@Test
	public void testLoginMethodPerson() throws Exception {
		
		Person p = new Person();
		p.setId("SYSA16aa");
		
		Login login = new Login();
		login.setPerson(p);
		
		assertNotNull(login.getPerson());
		assertEquals(login.getPerson().getId(), "SYSA16aa");
	}
	
	@Test
	public void testLoginMethodPassword() throws Exception {
		Login login = new Login();
		login.setPassword("Lund123");
		assertEquals(login.getPassword(), "Lund123");
		
	}

	@Test
	public void testCreateLogin() throws Exception {
		
		Person p = new Person();
		p.setId("testSubject");
		if (this.personEAO.findPersonByPersonId("testSubject") == null)
			this.personEAO.createPerson(p);
		assertNotNull(this.personEAO.findPersonByPersonId(p.getId()));
		
		Login login = new Login();
		login.setPerson(p);
		login.setPassword("testSubjectPassword");
		
		this.loginEAO.createLogin(login);
	}

	@Test
	public void testFindLogin() throws Exception {
		Login login = this.loginEAO.findLoginByPersonId("testSubject");
		assertNotNull(login);
	}

	@Test
	public void testDeleteAnimal() {
		this.loginEAO.deleteLogin("testSubject");
		Login login = this.loginEAO.findLoginByPersonId("testSubject");
		assertNull(login);
	}
}
