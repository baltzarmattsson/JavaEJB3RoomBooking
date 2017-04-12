package t4.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import t4.entities.Login;
import t4.entities.Person;
import t4.entities.Role;
import t4.facade.FacadeLocal;


@WebServlet("/TestClientServlet")
public class TestClientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private FacadeLocal facade;

    public TestClientServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		out.println("<!DOCTYPE html><html><head>");
		out.println("<title>T4 Testing</title>");
		out.println("<meta charset=\"ISO-8859-1\">");
		out.println("</head><body>");
		
		out.println("<h3>Person, Login, Role testing</h3><br>");
		
//		out.println("Skapar person");
//		Role r = new Role("Nyroll");
//		facade.createRole(r);
//		Person p = new Person("2", "Namn", "em@ail.com", "0709-123", r);
//		facade.createPerson(p);
//		Login l = new Login(p, "passwurd");
//		facade.createLogin(l);
		
//		facade.deletePerson("2");
		
//		out.println("Skapad?");
		
		Person foundPerson = facade.findPersonByPersonId("1");
		out.println("Persson: " + foundPerson.getId() + "'s role = " + foundPerson.getRoleName().getName());
		
		out.println("</body></html>");
	
	}

}
