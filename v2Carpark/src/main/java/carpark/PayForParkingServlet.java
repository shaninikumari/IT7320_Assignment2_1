package carpark;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import bean.ParkingSlotBean;

import util.CostCalculator;
import util.DBManager;

/**
 * Servlet implementation class PayForParkingServlet
 */
@WebServlet("/PayForParkingServlet")
public class PayForParkingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PayForParkingServlet() {
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
		System.out.println("PayForParkingServlet " + request.getParameter("bookingId"));
		if("Extend".equalsIgnoreCase(request.getParameter("payforslot"))){
			if(!request.getParameter("extendTime").isEmpty()){
				DBManager dbm= new DBManager();
				if(!dbm.extendBooking( request.getParameter("extendTime"),  request.getParameter("bookingId"))){
					System.out.println("Error");
					request.setAttribute("Error", "Your parking time is not extended. Please try again");
					getServletContext().getRequestDispatcher("/parkingslotbooking.jsp").forward(request, response);
				}else{
					System.out.println("Success");
					request.setAttribute("Success", "Your parking time is extended successfully");
					getServletContext().getRequestDispatcher("/parkingslotbooking.jsp").forward(request, response);
				}
			}else{
				request.setAttribute("Error", "Extendtime should not be blank");
				getServletContext().getRequestDispatcher("/parkingslotbooking.jsp").forward(request, response);
			}
			
		}else if("Pay".equalsIgnoreCase(request.getParameter("payforslot"))){
			CostCalculator costCalculator = new CostCalculator();
			double totalCost = costCalculator.calculateParkingCharge(request.getParameter("bookingId").trim());
			if(totalCost>0){
				request.setAttribute("totalCost", totalCost);
				getServletContext().getRequestDispatcher("/payment.jsp").forward(request, response);
			}else{
				getServletContext().getRequestDispatcher("/nocharge.jsp").forward(request, response);
			}
		}
	}

}
