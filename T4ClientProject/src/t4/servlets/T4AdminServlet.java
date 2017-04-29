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
				request.setAttribute("allRoles", this.facade.findAllRoles());
				request.setAttribute("editing", false);
				request.setAttribute("test", "test");
				url = "/PersonEditor.jsp";
				break;
				
			case "editPerson":
				personId = request.getParameter("personId");
				Person p = this.facade.findPersonByPersonId(personId);

				request.setAttribute("test", "test");
				request.setAttribute("allRoles", this.facade.findAllRoles());
				request.setAttribute("personToEdit", p);
				request.setAttribute("editing", true);
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
				request.setAttribute("allRoles", this.facade.findAllRoles());
				request.setAttribute("editing", false);
				request.setAttribute("test", "test");
				url = "/PersonEditor.jsp";
				break;
				
			case "editPerson":
				String personId = request.getParameter("selectedPerson");
				Person p = this.facade.findPersonByPersonId(personId);

				request.setAttribute("test", "test");
				request.setAttribute("allRoles", this.facade.findAllRoles());
				request.setAttribute("personSubject", p);
				request.setAttribute("editing", true);
				url = "/PersonEditor.jsp";
				break;
				
			case "saveNewPerson": 
				System.out.println("**Saving newp erson**: " + request.getParameter("editing"));

				Person newPerson = new Person();
				newPerson.setId(request.getParameter("personId"));
				newPerson.setName(request.getParameter("personName"));
				newPerson.setEmail(request.getParameter("personEmail"));
				newPerson.setPhoneNbr(request.getParameter("personPhoneNbr"));
				
				String roleName = request.getParameter("roleName");
				Role role = facade.findRoleByRoleName(roleName);
				newPerson.setRole(role);
				
				facade.createPerson(newPerson);
				
				request.setAttribute("editing", true);
				request.setAttribute("personSubject", newPerson);
				url = "/PersonEditor.jsp";
				
				break;
			case "updateExistingPerson":
				System.out.println("**updating existing");
				
				Person existing = new Person();
				existing.setId(request.getParameter("personId"));
				existing.setName(request.getParameter("personName"));
				existing.setEmail(request.getParameter("personEmail"));
				existing.setPhoneNbr(request.getParameter("personPhoneNbr"));
				
				roleName = request.getParameter("roleName");
				role = facade.findRoleByRoleName(roleName);
				existing.setRole(role);
				
				facade.updatePerson(existing);
				
				request.setAttribute("editing", true);
				request.setAttribute("personSubject", existing);
				url = "/PersonEditor.jsp";
				
				break;
			case "deletePerson":
				System.out.println("**deleting person**");
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
				role = new Role();
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
