package carpark;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.DBManager;

import bean.Payment;

/**
 * Servlet implementation class PaymentServlet
 */
@WebServlet("/PaymentServlet")
public class PaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaymentServlet() {
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
		
		System.out.println("PaymentServlet called");
		
		System.out.println("PaymentServlet called: " + request.getParameter("paymentaction"));
		if("Make Payment".equalsIgnoreCase(request.getParameter("paymentaction"))){
			System.out.println("RegisterUserServlet Servlet Register button clicked");
			if(request.getParameter("amountDue").isEmpty() || request.getParameter("cardnumber").isEmpty()|| request.getParameter("nameoncard").isEmpty()
					|| request.getParameter("expiryDate").isEmpty()|| request.getParameter("cvv").isEmpty()){
				System.out.println("All fields are mandetory");
				request.setAttribute("Error", "All fields are mandetory");
				getServletContext().getRequestDispatcher("/payment.jsp").forward(request, response);
				
			}else{
		
				Payment payment = new Payment();
				payment.setAmountDue(request.getParameter("amountDue"));
				payment.setCardNumber(request.getParameter("cardnumber"));
				payment.setNameOnCard(request.getParameter("nameoncard"));
				payment.setExpiryDate(request.getParameter("expiryDate"));
				payment.setCvv(request.getParameter("cvv"));
				payment.setPaymentId(UUID.randomUUID().toString());
				payment.setBookingId(UUID.randomUUID().toString());
				payment.setUserId(request.getParameter("loginId"));
				payment.setPaymentDate((new Date()).toString());
				
				DBManager dbm= new DBManager();
				if(dbm.makePayment(payment)){
					if(request.getAttribute("Error")!=null){
						request.removeAttribute("Error");
					}
					request.setAttribute("Success", "Payment successfully completed");
					
					getServletContext().getRequestDispatcher("/parkingslotbooking.jsp").forward(request, response);
				}else{
					request.setAttribute("Error", "Payment failed, please try again");
					getServletContext().getRequestDispatcher("/payment.jsp").forward(request, response);
				}
			
		}
		}else if("Back".equalsIgnoreCase(request.getParameter("paymentaction"))){
			System.out.println("PaymentServlet back button clicked");
			getServletContext().getRequestDispatcher("/parkingslotbooking.jsp").forward(request, response); 
		}

	}
}
