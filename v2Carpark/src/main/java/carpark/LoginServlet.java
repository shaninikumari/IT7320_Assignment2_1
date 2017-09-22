package carpark;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.DBManager;

import bean.LoginBean;


/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		System.out.println("LoginServlet called");
		String action = request.getParameter("action");
		System.out.println("LoginServlet called: " + request.getParameter("loginAction"));
		if("Login".equalsIgnoreCase(request.getParameter("loginAction"))){
			if(request.getParameter("loginid").isEmpty() || request.getParameter("pass").isEmpty()){
				request.setAttribute("Error", "Either User ID or Password is left blank");
				getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
				
			}else{
				LoginBean loginBean = new LoginBean();
				loginBean.setUserid(request.getParameter("loginid").trim());
				loginBean.setUserid(request.getParameter("pass").trim());
				DBManager dbm= new DBManager();
				if(!dbm.authenticateUser(request.getParameter("loginid").trim(), request.getParameter("pass").trim())){
					request.setAttribute("Error", "User ID and Password combination is wrong");
					getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
				}else{
					
					getServletContext().getRequestDispatcher("/parkingslotbooking.jsp").forward(request, response);
				}
				
			}
			
			
		}else if("Exit".equalsIgnoreCase(request.getParameter("loginAction"))){
			getServletContext().getRequestDispatcher("/exitcarpark.jsp").forward(request, response);
		}
		
	}

}
