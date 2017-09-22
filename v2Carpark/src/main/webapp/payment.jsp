<%@page import="java.awt.*"%>
<%@page import="java.util.Scanner"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
  <%
String contextPath = request.getContextPath();
System.out.println("contextPath: "+ contextPath);
%>
<h3>  Please Pay for Parking Slot </h3>
<form action="PaymentServlet" method="post" >
	<table  bgcolor = "LightSeaGreen">
		<tr>
			<td>Vehicle No.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="ti"/></td>
		</tr>
		<tr>
			<td>Time In&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="ti"/></td>
		</tr>
		<tr>
			<td>Time Out&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="to"></td>
		</tr>
		<tr>
			<td>     Zone&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="radio" name="Zone" value="P30" checked>P30
					<input type="radio"name="Zone" value="P60">P60&nbsp;
					<input type="radio"name="Zone" value="P120">P120
			</td>
		</tr>
		<tr>
			<td>Total Time&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="tt"></td>
			
		</tr>
		<tr>
			<td>Exceed Time&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="exceedTime"></td>
		</tr>
		<tr>
			<td>Amount Due&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="amountDue"></td>
		</tr>
		<tr>
			<td>Card Number&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="cardnumber"></td>
		</tr>
		<tr>
			<td>Name on Card&nbsp;&nbsp;&nbsp;<input type="text" name="nameoncard"></td>
		</tr>
		<tr>
			<td>Expiry Date&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="expiryDate"/></td>
		</tr>
		<tr>
			<td>CVV&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="password" name="cvv"/></td>
		</tr>
			<td><input type="submit" value="&nbsp;&nbsp;&nbsp;Go Back"/></td>
			<td><input type="submit" value="&nbsp;Make Payment"/></td>
		    		 
		     
		</table>
</form>
</html>
