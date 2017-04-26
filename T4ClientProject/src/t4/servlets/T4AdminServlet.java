package t4.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javafx.scene.chart.PieChart.Data;
import t4.entities.Login;
import t4.entities.Person;
import t4.entities.Role;
import t4.facade.FacadeLocal;


@WebServlet("/T4AdminServlet")
public class T4AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private FacadeLocal facade;

    public T4AdminServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = null;
		try {
			String operation = request.getParameter("operation");
			
			switch (operation) {
			case "getAllLogins": 
				ArrayList<Login> allLogins = this.facade.findAllLogins();
				request.setAttribute("allLogins", allLogins);
				break;
			case "getLogin":
				String personAndLoginId = request.getParameter("personAndLoginId");
				Login login = this.facade.findLoginByPersonId(personAndLoginId);
				
				if (login != null) {
					request.setAttribute("login", login);
					
				} else {
					request.setAttribute("responseLabel", "INFO: Login not found");
				}
				break;
			
			case "getAllRoles":
				ArrayList<Role> allRoles = this.facade.findAllRoles();
				request.setAttribute("allRoles", allRoles);
				break;
			case "getRole":
				String roleId = request.getParameter("roleId");
				Role role = this.facade.findRoleByRoleName(roleId);
						
				if (role != null) {
					request.setAttribute("role", role);
					
				} else {
					request.setAttribute("responseLabel", "INFO: Role not found");
				}
				break;
			case "getAllPersons":
				ArrayList<Person> allPersons = this.facade.findAllPersons();
				request.setAttribute("allPersons", allPersons);
				break;
			case "getPerson":
				String personId = request.getParameter("personId");
				Person person = this.facade.findPersonByPersonId(personId);
				
				if (person != null) {
					request.setAttribute("person", person);
					
				} else {
					request.setAttribute("responseLabel", "INFO: Person not found");
				}
				break;
			case "createNewPerson":
				System.out.println("ASDASDASDSDASD");
				request.setAttribute("allRoles", this.facade.findAllRoles());
				request.setAttribute("mode", "Create");
				request.setAttribute("test", "test");
				url = "/PersonEditor.jsp";
				break;
				
			case "editPerson":
				personId = request.getParameter("personId");
				Person p = this.facade.findPersonByPersonId(personId);

				request.setAttribute("test", "test");
				request.setAttribute("allRoles", this.facade.findAllRoles());
				request.setAttribute("personToEdit", p);
				request.setAttribute("mode", "Edit");
				url = "/PersonEditor.jsp";
				break;
			}
			
		} catch (Exception e) {
			
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = null;
		
		try {
			String operation = request.getParameter("operation");
			
			switch (operation) {
			
			// LOGIN
			case "createNewPerson":
				System.out.println("ASDASDASDSDASD");
				request.setAttribute("allRoles", this.facade.findAllRoles());
				request.setAttribute("mode", "Create");
				request.setAttribute("test", "test");
				url = "/PersonEditor.jsp";
				break;
				
			case "editPerson":
				String personId = request.getParameter("selectedPerson");
				Person p = this.facade.findPersonByPersonId(personId);

				request.setAttribute("test", "test");
				request.setAttribute("allRoles", this.facade.findAllRoles());
				request.setAttribute("personToEdit", p);
				request.setAttribute("mode", "Edit");
				url = "/PersonEditor.jsp";
				break;
			case "loginUser":
				String username = request.getParameter("username");
				String password = request.getParameter("password");
				Login login = facade.findLoginByPersonId(username);
				if (login != null && login.getPassword().equals(password)) {
					url = "/EditorSelector.jsp";
					request.setAttribute("loggedInUser", login.getPerson());
					request.setAttribute("allPersons", facade.findAllPersons());
				} else {
					url = "/IndexLogin.jsp";
					request.setAttribute("responseLabel", "ERROR: Login failed, either the credentials are wrong or the user does not have a login");
				}
				System.out.println("breaking");
				break;
				
			case "addLogin":
				personId = request.getParameter("personId");
				password = request.getParameter("password");
				Login loginForPerson = facade.findLoginByPersonId(personId);
				
				if (loginForPerson == null) {
					Person person = facade.findPersonByPersonId(personId);
					loginForPerson = new Login();
					loginForPerson.setPerson(person);
					loginForPerson.setPassword(password);
					facade.createLogin(loginForPerson);
					request.setAttribute("responseMessage", "SUCCESS: Login created");
				} else {
					loginForPerson.setPassword(password);
					facade.updateLogin(loginForPerson);
					request.setAttribute("responseMessage", "SUCCESS: Login updated");
				}
				break;
				
			// PERSON
			case "addPerson":
				Person person = new Person();
				person.setId(request.getParameter("personId"));
				person.setName(request.getParameter("personName"));
				person.setEmail(request.getParameter("personEmail"));
				person.setPhoneNbr(request.getParameter("personPhoneNbr"));
				
				Role personRole = facade.findRoleByRoleName(request.getParameter("roleName"));
				person.setRole(personRole);
				facade.createPerson(person);
				request.setAttribute("responseMessage", "SUCCESS: Person created");
				break;
				
			// ROLE
			case "addRole":
				Role role = new Role();
				role.setName(request.getParameter("roleName"));
				facade.createRole(role);
				request.setAttribute("responseMessage", "SUCCESS: Role created");
				break;
			}

			RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
			rd.forward(request, response);
		} catch (Exception e) {
			throw e;
		}
	}

}
