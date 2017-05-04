package t4.tests;

import javax.naming.Context;
import javax.naming.InitialContext;

import junit.framework.TestCase;
import t4.eao.PersonEAOLocal;
import t4.entities.Person;

public class PersonBeanTest extends TestCase {
	
	PersonEAOLocal person;

	public PersonBeanTest(String name) {
		super(name);
	}
	
	protected void setUp() throws Exception {
		super.setUp();
		Context context = new InitialContext();
		person=(PersonEAOLocal)context.lookup("java:app/T4EJBProject/PersonEAOImpl!t4.eao.PersonEAOLocal");
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		person = null;
	}
	public void testPersonMethods() throws Exception {
		Person person = new Person();
		person.setId("SYSA15BM");
		person.setName("Baltzar Mattsson");
		person.setEmail("SYSA15BM@lu.se");
		person.setPhoneNbr("7890");
		assertEquals(person.getId(),"SYSA15BM");
		assertEquals(person.getName(),"Baltzar Mattsson");
		assertEquals(person.getEmail(),"SYSA15BM@lu.se");
		assertEquals(person.getPhoneNbr(),"7890");
		}
		public void testPersonMethods2() throws Exception {
		Person person = new Person();
		person.setId("SYSA15EN");
		person.setName("Eric Nilsson");
		person.setEmail("SYSA15EN@lu.se");
		person.setPhoneNbr("8900");
		assertEquals(person.getId(),"SYSA15EN");
		assertEquals(person.getName(),"Eric Nilsson");
		assertEquals(person.getEmail(),"SYSA15EN@lu.se");
		assertEquals(person.getPhoneNbr(),"8900");
		}
}
