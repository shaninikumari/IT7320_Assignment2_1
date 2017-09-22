package carpark;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.DBManager;

import bean.UserRegistrationBean;

/**
 * Servlet implementation class RegisterUserServlet
 */
@WebServlet("/RegisterUserServlet")
public class RegisterUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("RegisterUserServlet Servlet called");
		String action = request.getParameter("action");
		System.out.println("RegisterUserServlet Servlet called: " + request.getParameter("registerAction"));
		if("Register".equalsIgnoreCase(request.getParameter("registerAction"))){
			System.out.println("RegisterUserServlet Servlet Register button clicked");
			if(request.getParameter("address").isEmpty() || request.getParameter("contactNo").isEmpty()|| request.getParameter("emailId").isEmpty()
					|| request.getParameter("firstName").isEmpty()|| request.getParameter("lastName").isEmpty()){
				System.out.println("All fields are mandetory");
				request.setAttribute("Error", "All fields are mandetory");
				getServletContext().getRequestDispatcher("/register.jsp").forward(request, response);
				
			}else{
				UserRegistrationBean userRegistration = new UserRegistrationBean();
				userRegistration.setLoginId(request.getParameter("loginId"));
				userRegistration.setPassword(request.getParameter("password"));
				userRegistration.setContactNo(request.getParameter("contactNo"));
				userRegistration.setAddress(request.getParameter("address"));
				userRegistration.setContactNo(request.getParameter("contactNo"));
				userRegistration.setEmailId(request.getParameter("emailId"));
				userRegistration.setFirstName(request.getParameter("firstName"));
				userRegistration.setLastName(request.getParameter("lastName"));
				
				DBManager dbm= new DBManager();
				if(dbm.registerUser(userRegistration)){
					if(request.getAttribute("Error")!=null){
						request.removeAttribute("Error");
					}
					request.setAttribute("RegistrationSuccess", "Thank you for registering your detail. Enter your User Id and Password for logging in");
					getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
				}else{
					request.setAttribute("Error", "Your registration failed, please try again");
					getServletContext().getRequestDispatcher("/register.jsp").forward(request, response);
				}
				
				
			}
		}else if("Cancel".equalsIgnoreCase(request.getParameter("registerAction"))){
			System.out.println("RegisterUserServlet Servlet Cancel button clicked");
			getServletContext().getRequestDispatcher("/goodby.jsp").forward(request, response); 
		}
	}

}
