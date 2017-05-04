package t4.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.EJBTransactionRolledbackException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

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
		String operation = request.getParameter("operation");
		String url = null;
		
		switch (operation) {
		case "goToAboutPage":
			url = "/About";
			break;
		
		case "goToTestPage":
			url = "/TestPage.jsp";
			break;
			
		case "goToEditorSelectorPage":
			url = "/EditorSelector.jsp";
			request.setAttribute("allPersons", facade.findAllPersons());
			request.setAttribute("allRoles", facade.findAllRoles());
			break;
		}
		
		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);
	}
		
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String url = null;
		String operation = request.getParameter("operation");
		
		try {
			// UPDATE/DELETE/CREATE Person
			if (operation.equals("personModification")) {

				Mode mode = null;
				if (request.getParameter("deletePerson") != null) {
					mode = Mode.DELETE;
				} else if (request.getParameter("updatePerson") != null) {
					mode = Mode.UPDATE;
				} else if (request.getParameter("createPerson") != null) {
					mode = Mode.CREATE;
				}
				try {
					Person person = this.handlePersonModification(request, mode);
					url = this.fillRequestWithPersonInfoAndReturnUrl(person, request);
					request.setAttribute("successMessage", "Person " + mode.toString().toLowerCase() + "d!");
				} catch (Exception e) {
					String errorMessage = this.handleConstraintViolationException(e, EditType.PERSON);
					request.setAttribute("errorMessage", errorMessage);
					url = "/PersonEditor.jsp";
				}

				// DELETE/CREATE ROLE
			} else if (operation.equals("roleModification")) {

				Mode mode = null;
				if (request.getParameter("deleteRole") != null) {
					mode = Mode.DELETE;
				} else if (request.getParameter("createRole") != null) {
					mode = Mode.CREATE;
				}
				try {
					Role role = this.handleRoleModification(request, mode);
					url = this.fillRequestWithRoleInfoAndReturnUrl(role, request);
					request.setAttribute("successMessage", "Role " + mode.toString().toLowerCase() + "d!");
				} catch (Exception e) {
					String errorMessage = this.handleConstraintViolationException(e, EditType.ROLE);
					request.setAttribute("errorMessage", errorMessage);
					url = "/RoleEditor.jsp";
				}
				// DELETE/CREATE LOGIN
			} else if (operation.equals("loginModification")) {

				Mode mode = null;
				if (request.getParameter("deleteLogin") != null) {
					mode = Mode.DELETE;
				} else if (request.getParameter("createLogin") != null) {
					mode = Mode.CREATE;
				} else if (request.getParameter("updateLogin") != null) {
					mode = Mode.UPDATE;
				}
				this.handleLoginModification(request, mode);
				Person person = this.facade.findPersonByPersonId(request.getParameter("personId"));
				url = this.fillRequestWithPersonInfoAndReturnUrl(person, request);
				// request.setAttribute("errorMessage", "Login " +
				// mode.toString().toLowerCase() + "d!");

			} else if (operation.equals("navbarClick")) {
				if (request.getParameter("goToHomePage") != null) {
					url = "/EditorSelector.jsp";

					request.setAttribute("allPersons", facade.findAllPersons());
					request.setAttribute("allRoles", facade.findAllRoles());
				} else if (request.getParameter("goToAboutPage") != null) {
					url = "/About.jsp";
				} else if (request.getParameter("goToTestPage") != null) {
					url = "/TestPage.jsp";
				} else if (request.getParameter("logoutUser") != null) {
					url = "/IndexLogin.jsp";
				}
			}
			
			else {
				switch (operation) {
				
				case "goToAboutPage":
					url = "/About.jsp";
					break;
				
				case "goToTestPage":
					url = "/TestPage.jsp";
					break;

				case "goToPersonEditPage":

					boolean editing = request.getParameter("editing") != null;
					request.setAttribute("editing", editing);

					Person personSubject = null;
					if (editing) {
						String personId = request.getParameter("selectedPerson");
						if (personId != null) {
							personSubject = facade.findPersonByPersonId(personId);
						}
					}
					url = this.fillRequestWithPersonInfoAndReturnUrl(personSubject, request);
					break;

				case "goToRoleEditPage":

					editing = request.getParameter("editing") != null;
					request.setAttribute("editing", editing);

					Role roleSubject = null;
					if (editing) {
						String roleName = request.getParameter("selectedRole");
						if (roleName != null) {
							roleSubject = facade.findRoleByRoleName(roleName);
						}
					}
					url = this.fillRequestWithRoleInfoAndReturnUrl(roleSubject, request);
					break;

				case "goToEditorSelectorPage":
					url = "/EditorSelector.jsp";

					request.setAttribute("allPersons", facade.findAllPersons());
					request.setAttribute("allRoles", facade.findAllRoles());
					break;

				case "loginUser":
					String username = request.getParameter("username");
					String password = request.getParameter("password");
					Login login = facade.findLoginByPersonId(username);
					if (login != null && login.getPassword().equals(password)) {
						url = "/EditorSelector.jsp";
						request.setAttribute("loggedInUser", login.getPerson());
						request.setAttribute("allPersons", facade.findAllPersons());
						request.setAttribute("allRoles", facade.findAllRoles());
					} else {
						url = "/IndexLogin.jsp";
						request.setAttribute("responseLabel",
								"ERROR: Login failed, either the credentials are wrong or the user does not have a login");
					}
					break;
				}
			}

			RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
			rd.forward(request, response);

		} catch (Exception e) {
			request.setAttribute("allPersons", facade.findAllPersons());
			request.setAttribute("allRoles", facade.findAllRoles());
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/EditorSelector.jsp");
			rd.forward(request, response);
		}
	}
	
	private enum Mode {
		UPDATE, CREATE, DELETE
	}
	
	private enum EditType {
		PERSON, ROLE
	}
	
	private String handleConstraintViolationException(Exception e, EditType editType) {		
		String subject = editType == EditType.PERSON ? "person" : "role";
		String identifyingAttribute = editType == EditType.PERSON ? "id" : "name";
		String message = null;
		
		if (e.getMessage().contains("ConstraintViolationException"))
			message = "There's already a " + subject + " with that " + identifyingAttribute + ", please choose another value.";
		else
			message = "An unknown error occured, please try again";
		
        return message;
	}
	
	private static final int PrimaryKey = 2627;
	private static final int ForeignKey = 547;
	private static final int DataWouldBeTruncated = 8152;
	private static final int SomethingIsNull = 515;

	private Person handlePersonModification(HttpServletRequest request, Mode mode) throws SQLException {

		String personId = request.getParameter("personId");
		if (personId != null) {
			if (mode == Mode.DELETE) {
				if (!personId.equals("1")) {
					this.facade.deletePerson(personId);
				} else {
					request.setAttribute("errorMessage", "Cannot delete Admin \"1\"");
				}
			} else {
				Person person = new Person();
				person.setId(personId);
				person.setName(request.getParameter("personName"));
				person.setEmail(request.getParameter("personEmail"));
				person.setPhoneNbr(request.getParameter("personPhoneNbr"));

				String roleName = request.getParameter("roleName");
				if (roleName != null) {
					Role role = facade.findRoleByRoleName(roleName);
					person.setRole(role);
				}

				if (mode == Mode.UPDATE) {
					return this.facade.updatePerson(person);
				} else if (mode == Mode.CREATE) {
					return this.facade.createPerson(person);
				}
			}
		}
		return null;
	}

	private String fillRequestWithPersonInfoAndReturnUrl(Person person, HttpServletRequest request) {
		
		if (person != null) {
			request.setAttribute("personSubject", person);
			request.setAttribute("editing", true);
			
			Login login = facade.findLoginByPersonId(person.getId());
			if (login != null) {
				request.setAttribute("loginSubject", login);
				request.setAttribute("loginExists", true);
			} else {
				request.setAttribute("loginExists", false);
			}
			
		} else {
			request.setAttribute("editing", false);
		}
		request.setAttribute("allRoles", facade.findAllRoles());
		String url = "/PersonEditor.jsp";
		return url;
	}
	
	private Role handleRoleModification(HttpServletRequest request, Mode mode) throws SQLException {

		String roleName = request.getParameter("roleName");
		if (roleName != null) {
			if (mode == Mode.DELETE) {
				this.facade.deleteRole(roleName);
			} else {
				Role role = new Role();
				role.setName(roleName);

				if (mode == Mode.UPDATE) {
					return this.facade.updateRole(role);
				} else if (mode == Mode.CREATE) {
					return this.facade.createRole(role);
				}
			}
		}
		return null;
	}


	private String fillRequestWithRoleInfoAndReturnUrl(Role role, HttpServletRequest request) {

		if (role != null) {
			request.setAttribute("roleSubject", role);
			request.setAttribute("editing", true);
		} else {
			request.setAttribute("editing", false);
		}
		String url = "/RoleEditor.jsp";
		return url;
	}

	private Login handleLoginModification(HttpServletRequest request, Mode mode) {

		String personId = request.getParameter("personId");
		Person loginOwner = facade.findPersonByPersonId(personId);
		if (loginOwner != null) {
			if (mode == Mode.DELETE) {
				this.facade.deleteLogin(personId);
				request.setAttribute("personId", personId);
			} else {
				String password, confirmPassword;
				password = request.getParameter("password");
				confirmPassword = request.getParameter("confirmPassword");
				if (password.equals(confirmPassword)) {
					Login login = new Login();
					login.setPerson(loginOwner);
					login.setPassword(request.getParameter("password"));

					if (mode == Mode.UPDATE) {
						request.setAttribute("successMessage", "Login updated!");
						return this.facade.updateLogin(login);
					} else if (mode == Mode.CREATE) {
						request.setAttribute("successMessage", "Login created!");
						return this.facade.createLogin(login);
					}
				} else {
					request.setAttribute("errorMessage", "Passwords doesnt match");
				}
			}
		} else {
			request.setAttribute("errorMessage", "Could not find owner (person)");
		}
		return null;
	}
}
