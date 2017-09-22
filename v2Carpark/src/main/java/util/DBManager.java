package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bean.ParkingSlotBean;
import bean.Payment;
import bean.UserRegistrationBean;

public class DBManager {
	private Connection conn = null;
	public DBManager() {
		

        try {
            // registers Oracle JDBC driver - though this is no longer required
            // since JDBC 4.0, but added here for backward compatibility
            Class.forName("oracle.jdbc.OracleDriver");

            // METHOD #1
            String dbURL1 = "jdbc:oracle:thin:inv/inv@localhost:1521:XE";
            conn = DriverManager.getConnection(dbURL1);
            if (conn != null) {
                System.out.println("Connected with connection #1");
            }
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
	}
	
	public boolean registerUser(UserRegistrationBean userRegistrationBean) {
		boolean userCreated = false;
		
		Statement stmt = null;
		try {
			stmt = conn.createStatement();

			String sql = "INSERT INTO \"INV\".\"Caruser\"   " +
	                   "VALUES ("+ "'"+userRegistrationBean.getFirstName() +"'"+ ","+ "'"+userRegistrationBean.getLastName() +"'"+ ","+"'"+userRegistrationBean.getEmailId() +"'"+ ","+"'"+userRegistrationBean.getContactNo() +"'"+ ","+"'"+userRegistrationBean.getAddress() +"'"+ ","+"'"+userRegistrationBean.getLoginId() +"'"+ ","+"'"+userRegistrationBean.getPassword() +"'" + ")";
			System.out.println(sql.toString());
		    stmt.executeUpdate(sql);
		    userCreated=true;
		   }catch(SQLException se){
		      
		      se.printStackTrace();
		   }catch(Exception e){
		      
		      e.printStackTrace();
		   }finally{
		      //finally block used to close resources
		      try{
		         if(stmt!=null)
		        	 stmt.close();
		      }catch(SQLException se){
		      }
		      
		}
		
		return userCreated;
	}
	
	public boolean bookParking(ParkingSlotBean parkingSlotBean) {
		boolean bookingCreated = false;
		
		Statement stmt = null;
		try {
			stmt = conn.createStatement();

			String sql = "INSERT INTO \"INV\".\"BookSlot\"   " +
	                   "VALUES ("+ "'"+parkingSlotBean.getUserId() +"'"+ ","+ "'"+parkingSlotBean.getLocation() +"'"+ ","+"'"+parkingSlotBean.getZone() +"'"+ ","+"'"+parkingSlotBean.getVehiclenumber() +"'"+ ","+"'"+parkingSlotBean.getDate() +"'"+ ","+"'"+parkingSlotBean.getStarttime() +"'"+ ","+"'"+parkingSlotBean.getEndtime() +"'" +","+"'"+parkingSlotBean.getActualEndTime() +"'"+","+"'"+parkingSlotBean.getBookingId() +"'"+ ")";
			System.out.println(sql.toString());
		    stmt.executeUpdate(sql);
		    bookingCreated=true;
		   }catch(SQLException se){
		      
		      se.printStackTrace();
		   }catch(Exception e){
		      
		      e.printStackTrace();
		   }finally{
		      //finally block used to close resources
		      try{
		         if(stmt!=null)
		        	 stmt.close();
		      }catch(SQLException se){
		      }
		      
		}
		
		return bookingCreated;
	}
	
	public ArrayList <ParkingSlotBean> getMyParkingSlot(String userId) {
		
		Statement stmt = null;
		ArrayList <ParkingSlotBean> carparkSlotList = new ArrayList<ParkingSlotBean>();
		try {
			stmt = conn.createStatement();

		      String sql = "SELECT * FROM \"INV\".\"BookSlot\"  bookslot"+ " where bookslot.\"loginId\"=" + "\'"+userId +"\'" ;
		      System.out.println(sql.toString());
		      ResultSet rs = stmt.executeQuery(sql);
		      //STEP 5: Extract data from result set
		      
		      while(rs.next()){
		    	  ParkingSlotBean parkingSlotBean = new ParkingSlotBean();
		    	  parkingSlotBean.setUserId(userId);
		    	  parkingSlotBean.setVehiclenumber(rs.getString(4));
		    	  parkingSlotBean.setZone(rs.getString(3));
		    	  parkingSlotBean.setLocation(rs.getString(2));
		    	  parkingSlotBean.setStarttime(rs.getString(6));
		    	  parkingSlotBean.setEndtime(rs.getString(7));
		    	  parkingSlotBean.setDate(rs.getString(5));
		    	  parkingSlotBean.setBookingId(rs.getString(9));
		    	  carparkSlotList.add(parkingSlotBean);
		         
		      }
		      rs.close();
		   }catch(SQLException se){
		      
		      se.printStackTrace();
		   }catch(Exception e){
		      
		      e.printStackTrace();
		   }finally{
		      //finally block used to close resources
		      try{
		         if(stmt!=null)
		        	 stmt.close();
		      }catch(SQLException se){
		      }
		      
		}
		
		return carparkSlotList;
		
	}
	
	
public ParkingSlotBean getBookedParkingSlot(String bookingId) {
		
		Statement stmt = null;
		ParkingSlotBean parkingSlotBean = new ParkingSlotBean();
		try {
			stmt = conn.createStatement();

		      String sql = "SELECT * FROM \"INV\".\"BookSlot\"  bookslot"+ " where bookslot.\"bookingId\"=" + "\'"+bookingId +"\'" ;
		      System.out.println(sql.toString());
		      ResultSet rs = stmt.executeQuery(sql);
		      //STEP 5: Extract data from result set
		      
		      while(rs.next()){
		    	  
		    	  parkingSlotBean.setUserId(rs.getString(1));
		    	  parkingSlotBean.setVehiclenumber(rs.getString(4));
		    	  parkingSlotBean.setZone(rs.getString(3));
		    	  parkingSlotBean.setLocation(rs.getString(2));
		    	  parkingSlotBean.setStarttime(rs.getString(6));
		    	  parkingSlotBean.setEndtime(rs.getString(7));
		    	  parkingSlotBean.setDate(rs.getString(5));
		    	  parkingSlotBean.setBookingId(rs.getString(9));
		    	  
		         
		      }
		      rs.close();
		   }catch(SQLException se){
		      
		      se.printStackTrace();
		   }catch(Exception e){
		      
		      e.printStackTrace();
		   }finally{
		      //finally block used to close resources
		      try{
		         if(stmt!=null)
		        	 stmt.close();
		      }catch(SQLException se){
		      }
		      
		}
		
		return parkingSlotBean;
		
	}
	
	public boolean extendBooking(String endTime, String bookingId) {
		//update "ProductStock" set "quantity"=20 where "productID"='2';
		boolean updateSuccess=false;
		Statement stmt = null;
		try {
				stmt = conn.createStatement();
				String sql = "update \"INV\".\"BookSlot\" set \"endTime\"="+"'"+endTime+"'" +" where \"bookingId\"="+"'" +bookingId.trim() +"'" ;
				System.out.println(sql.toString());
				stmt.executeUpdate(sql);
				updateSuccess=true;
			}catch(SQLException se){
			      
			      se.printStackTrace();
			   }catch(Exception e){
			      
			      e.printStackTrace();
			   }finally{
			      //finally block used to close resources
			      try{
			         if(stmt!=null)
			        	 stmt.close();
			      }catch(SQLException se){
			      }
			   }
		return updateSuccess;
	}
	
	public boolean makePayment(Payment payment) {
		boolean bookingCreated = false;
		
		Statement stmt = null;
		try {
			stmt = conn.createStatement();

			String sql = "INSERT INTO \"INV\".\"Payment\"   " +
	                   "VALUES ("+ "'"+payment.getPaymentId() +"'"+ ","+ "'"+payment.getBookingId() +"'"+ ","+"'"+payment.getAmountDue() +"'"+ ","+"'"+payment.getPaymentDate() +"'"+ ")";
			System.out.println(sql.toString());
		    stmt.executeUpdate(sql);
		    bookingCreated=true;
		   }catch(SQLException se){
		      
		      se.printStackTrace();
		   }catch(Exception e){
		      
		      e.printStackTrace();
		   }finally{
		      //finally block used to close resources
		      try{
		         if(stmt!=null)
		        	 stmt.close();
		      }catch(SQLException se){
		      }
		      
		}
		
		return bookingCreated;
	}
	
	public boolean authenticateUser(String userId, String password) {
		boolean userAuthentic = false;
		
		Statement stmt = null;
		try {
			stmt = conn.createStatement();

		      String sql = "SELECT * FROM \"INV\".\"Caruser\"  usr"+ " where usr.\"loginId\"=" + "\'"+userId +"\'" + " AND "+ "usr.\"password\"=" + "\'"+password +"\'";
		      System.out.println(sql.toString());
		      ResultSet rs = stmt.executeQuery(sql);
		      //STEP 5: Extract data from result set
		      while(rs.next()){
		    	  userAuthentic=true;
		         
		      }
		      rs.close();
		   }catch(SQLException se){
		      
		      se.printStackTrace();
		   }catch(Exception e){
		      
		      e.printStackTrace();
		   }finally{
		      //finally block used to close resources
		      try{
		         if(stmt!=null)
		        	 stmt.close();
		      }catch(SQLException se){
		      }
		      
		}
		
		return userAuthentic;
	}

	public boolean isPaymentDone(String bookingId) {
		boolean paymentDone = false;
		
		Statement stmt = null;
		try {
			stmt = conn.createStatement();

		      String sql = "SELECT * FROM \"INV\".\"Payment\"  payment"+ " where payment.\"bookingId\"=" + "\'"+bookingId +"\'" ;
		      System.out.println(sql.toString());
		      ResultSet rs = stmt.executeQuery(sql);
		      //STEP 5: Extract data from result set
		      while(rs.next()){
		    	  paymentDone=true;
		         
		      }
		      rs.close();
		   }catch(SQLException se){
		      
		      se.printStackTrace();
		   }catch(Exception e){
		      
		      e.printStackTrace();
		   }finally{
		      //finally block used to close resources
		      try{
		         if(stmt!=null)
		        	 stmt.close();
		      }catch(SQLException se){
		      }
		      
		}
		
		return paymentDone;
	}
}
