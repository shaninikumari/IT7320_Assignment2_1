package carpark;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
		System.out.println("Payment Servlet called");
		Payment payment = new Payment();
		payment.setTimeIn(request.getParameter("ti"));
		payment.setTimeOut(request.getParameter("to"));
		payment.setZone(request.getParameter("zone"));
		payment.setTotalTime(request.getParameter("tt"));
		payment.setExceedTime(request.getParameter("exceedTime"));
		payment.setAmountDue(request.getParameter("amountDue"));
		payment.setCardNumber(request.getParameter("cardnumber"));
		payment.setNameOnCard(request.getParameter("nameoncard"));
		payment.setExpiryDate(request.getParameter("expiryDate"));
		payment.setCvv(request.getParameter("cvv"));
		
		payment.makePayment();
	}

}
