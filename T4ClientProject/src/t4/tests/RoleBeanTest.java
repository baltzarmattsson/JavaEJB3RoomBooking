package t4.tests;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.junit.Test;

import junit.framework.TestCase;
import t4.eao.RoleEAOLocal;
import t4.entities.Role;

public class RoleBeanTest extends TestCase {

	RoleEAOLocal role;
	
	public RoleBeanTest(String name) {
		super(name);
	}
	
	protected void setUp() throws Exception {
		super.setUp();
		Context context = new InitialContext();

		role = (RoleEAOLocal) context.lookup("java:app/T4EJBProject/RoleEAOImpl!t4.eao.RoleEAOLocal");
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		role = null;
	}

	public void testRoleMethods() throws Exception {
		Role role = new Role();
		role.setName("Admin");
		assertEquals(role.getName(), "Admin");
	}

	public void testRoleMethods2() throws Exception {
		Role role = new Role();
		role.setName("Student");
		assertEquals(role.getName(), "Student");
	}
}
