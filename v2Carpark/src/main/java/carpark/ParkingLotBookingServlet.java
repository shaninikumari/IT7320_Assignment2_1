package carpark;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.DBManager;

import bean.ParkingSlotBean;

/**
 * Servlet implementation class ParkingLotBookingServlet
 */
@WebServlet("/ParkingLotBookingServlet")
public class ParkingLotBookingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ParkingLotBookingServlet() {
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
		System.out.println("ParkingLotBookingServlet called");
		
		System.out.println("ParkingLotBookingServlet called: " + request.getParameter("loginId"));
		if("ReserverSlot".equalsIgnoreCase(request.getParameter("bookparking"))){
			if(request.getParameter("loginId").isEmpty() || request.getParameter("location").isEmpty() || request.getParameter("zone").isEmpty()|| request.getParameter("vehiclenumber").isEmpty()
					|| request.getParameter("date").isEmpty()|| request.getParameter("starttime").isEmpty()|| request.getParameter("endtime").isEmpty()){
				System.out.println("All fields are mandetory");
				request.setAttribute("Error", "All fields are mandetory");
				getServletContext().getRequestDispatcher("/parkingslotbooking.jsp").forward(request, response);
				
			}else{
				ParkingSlotBean parkingSlotBean = new ParkingSlotBean();
				parkingSlotBean.setUserId(request.getParameter("loginId").trim());
				parkingSlotBean.setBookingId(UUID.randomUUID().toString());
				parkingSlotBean.setLocation(request.getParameter("location").trim());
				parkingSlotBean.setZone(request.getParameter("zone").trim());
				parkingSlotBean.setVehiclenumber(request.getParameter("vehiclenumber").trim());
				parkingSlotBean.setDate(request.getParameter("date").trim());
				parkingSlotBean.setStarttime(request.getParameter("starttime").trim());
				parkingSlotBean.setEndtime(request.getParameter("endtime").trim());
				parkingSlotBean.setActualEndTime("");
				
				DBManager dbm= new DBManager();
				if(dbm.bookParking(parkingSlotBean)){
					if(request.getAttribute("Error")!=null){
						request.removeAttribute("Error");
					}
					request.setAttribute("Success", "Car parking slot booked");
					getServletContext().getRequestDispatcher("/payment.jsp").forward(request, response);
				}else{
					request.setAttribute("Error", "Car parking slot booking failed, please try again");
					getServletContext().getRequestDispatcher("/parkingslotbooking.jsp").forward(request, response);
				}
				
				
			}
			
		}else if("Exit".equalsIgnoreCase(request.getParameter("bookparking"))){
			getServletContext().getRequestDispatcher("/exitcarpark.jsp").forward(request, response);
		}
	}

}
