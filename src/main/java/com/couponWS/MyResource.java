package com.couponWS;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlRootElement;

import com.mysql.jdbc.PreparedStatement;
//import com.sql.jdbc.Driver;
import Coupon.Coupon;
import db.DB;
@XmlRootElement
/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {
    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
	 @GET
	    @Produces(MediaType.TEXT_PLAIN)
	    public String getIt() {
	        return "Got it!";
	    }
//Deletes a coupon
	 @DELETE
	 @Path("DeleteCoupon/{ID}")
	 @Consumes(MediaType.APPLICATION_JSON)
	 @Produces(MediaType.APPLICATION_JSON)
	 public Response DeleteCoupon(@PathParam("ID") String id){//(@FormParam("DelVar") String CouponID){	
		 int ci=Integer.parseInt(id);
		//System.out.println("here is the id for deleting: "+id);		
		 PreparedStatement stmt = null;
		    	DB database = new DB();
		    	try {
		    		Connection c= database.connect();
					String query = "DELETE FROM coupon WHERE ID=?";
					stmt = (PreparedStatement)c.prepareStatement(query);
					stmt.setInt(1, ci);
					stmt.executeUpdate();
		    	}
		    	catch(SQLException e)
		    	{
		    		e.printStackTrace();
		    		System.out.println("sql erroooooor");
		    	} 
	 return Response.ok().build();
	 }
//Adds a single coupon	
	@POST
	@Path("CreateCoupon")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	 public void CreateCoupon(@FormParam("vardiscount") String Discount,
			@FormParam("vartype") String CouponType,@FormParam("varItmnm") String Itemname,@FormParam("varItmid") String ItemID, @FormParam("vartime1") String ValidTime1, @FormParam("vartime2") String ValidTime2) 
	{//@FormParam("varID") String CouponID
		//CouponID != null && CouponID !="" &&
		if( Discount != null && Discount !="" && CouponType != null && CouponType !="" && Itemname != null && Itemname !="" && ItemID != null && ItemID !="" && ValidTime1 != null && ValidTime1 !="" && ValidTime2 != null && ValidTime2 !="" )
		{			
//		int is1=Integer.parseInt(CouponID);
		Double ds2=Double.parseDouble(Discount);
		int is3=Integer.parseInt(CouponType);
		int is5=Integer.parseInt(ItemID);

		Date datevar=null;
		Date datevarp=null;
		java.sql.Date datevar1=null;
		java.sql.Date datevar2=null;
		
		try {
			datevar = new SimpleDateFormat("yyyy-MM-dd").parse(ValidTime1);
			datevarp=new SimpleDateFormat("yyyy-MM-dd").parse(ValidTime2);
			datevar1 = new java.sql.Date(datevar.getTime());
			datevar2 = new java.sql.Date(datevarp.getTime());
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Coupon C= new Coupon();
		PreparedStatement stmt = null;
    	DB database = new DB();
    	try {
    		Connection c= database.connect();
			String query = "INSERT INTO coupon (discount, type, Iname, IID, time1, time2) Values (?,?,?,?,?,?)";
			stmt = (PreparedStatement)c.prepareStatement(query);
	//		stmt.setInt(1, is1);
			stmt.setDouble(1, ds2);
			stmt.setInt(2, is3);
			stmt.setString(3, Itemname);
			stmt.setInt(4, is5);
			stmt.setDate(5, datevar1);
			stmt.setDate(6,datevar2);
			stmt.executeUpdate();
			//stmt.close();
    	}
    	catch(SQLException e)
    	{
    		e.printStackTrace();
    	}
    	} 
	}
//Read coupon information, type and discount
    @GET
	@Path("readcoupon/{ID}")
    @Produces(MediaType.APPLICATION_JSON)   
  //  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Coupon ReadCoupon(@PathParam ("ID") String id){
    	Coupon co=new Coupon();	
    	Statement stmt = null;
		ResultSet rs = null;
		Date d= new Date();
    	DB database = new DB();
    	int IC=Integer.parseInt(id);
    	co.CouponID=IC;
    	try {
    		Connection c= database.connect();
			stmt = (Statement) c.createStatement();
//			String query = "SELECT Promotion FROM coupon where id="+IC;
			rs = (ResultSet) stmt.executeQuery("SELECT discount , type from coupon where id="+IC);
			/*while (rs.next()) {
				co.Discount = rs.getDouble("discount");
				co.CouponType = rs.getInt("type");
				co.Itemname ="";
				co.ItemID=0;
				co.ValidTime1=d;
				co.ValidTime2=d;
						}
			*/
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return co;
    	}
//First simple rest service I developed 
/*@GET
@Produces({MediaType.TEXT_XML, MediaType.APPLICATION_JSON})
@Path("get")
	public Coupon Check2(){
	Statement stmt = null;
	ResultSet rs = null;
	DB database = new DB();
	Coupon C2 = new Coupon();
	C2.CouponID = 11;
	int IC=11;
	try {
		
		Connection c= database.connect();
		stmt = (Statement) c.createStatement();
		String query = "SELECT discount FROM coupon where id="+IC;
		rs = (ResultSet) stmt.executeQuery("SELECT discount , type from coupon where id="+IC);
		while (rs.next()) {
			C2.Discount = Double.parseDouble(rs.getString("Discount"));
			C2.CouponType =Integer.parseInt(rs.getString("Type"));
			return C2;
		}
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return C2;
} */
/////////////////////////////////////////////////

//Reads Coupon discount amount for price computation
@GET
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.APPLICATION_JSON)//{MediaType.TEXT_XML,
@Path("getco")
	public Coupon GetDiscount(@QueryParam("var1") int var1 ){ //Check3
	PreparedStatement stmt = null;
	//	Statement stmt = null;
	ResultSet rs = null;
	DB database = new DB();
	Coupon C2 = new Coupon();
	
	Date datevar=new Date();
	java.sql.Date datevar1=null;
	try {
		datevar1 = new java.sql.Date(datevar.getTime());
	} catch (Exception e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}

	try {
		Connection c= database.connect();
		//stmt = (Statement) c.createStatement();
		//rs = (ResultSet) stmt.executeQuery("SELECT DISTINCT discount , type from coupon where coupon.time1 <= ? and coupon.time2 >=? and IID=?");//+var1);
		String sql="SELECT DISTINCT discount , type from coupon where coupon.time1 <= ? and coupon.time2 >=? and IID=?";
		stmt = (PreparedStatement) c.prepareStatement(sql);
		stmt.setDate(1, datevar1);
		stmt.setDate(2, datevar1);
		stmt.setInt(3, var1);
		if(stmt.executeQuery()!=null)
		{
			rs=stmt.executeQuery();
			if (rs.next()) {
			C2.CouponType=rs.getInt("type");
			C2.Discount=rs.getDouble("discount");
			}
			else
				return null;
		
	}
		else
			return null;
		} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return C2;
}
//Reads Coupon information discount, start and end time, for validation check
@GET
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.APPLICATION_JSON)
@Path("gettime")
public Coupon Validation(@QueryParam("var") int var ){ //Checktime
	Statement stmt = null;
	ResultSet rs = null;
	DB database = new DB();
	Coupon C2 = new Coupon();
	//C2.CouponID = var;
	int IC=var;
	try {
		Connection c= database.connect();
		stmt = (Statement) c.createStatement();
		//String query = "SELECT Promotion FROM coupon where id="+IC;
		rs = (ResultSet) stmt.executeQuery("SELECT time1 , time2 from coupon where id="+IC);
		while (rs.next()) {
			C2.setValidTime1(rs.getDate("time1"));
			C2.setValidTime2(rs.getDate("time2"));
			//C2.Discount = Double.parseDouble(rs.getString("discount"));
			//C2.CouponType =Integer.parseInt(rs.getString("type"));
		//	return C2;
		}
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return C2;
}
//for checking
@GET
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.APPLICATION_JSON)
@Path("getcoupons")
public Coupon GetCoupon(@QueryParam("var1") int var1){//Checkcoupons
	Statement stmt = null;
	ResultSet rs = null;
	DB database = new DB();
	Coupon C2 = new Coupon();
	try {
		Connection c= database.connect();
		stmt = (Statement) c.createStatement();
		//String query = "SELECT Promotion FROM coupon where id="+IC;
		rs = (ResultSet) stmt.executeQuery("SELECT DISTINCT type, discount , Iname , time1 , time2 from coupon where id="+var1);
		if(rs.next()){
	//	while (rs.next()){
			C2.CouponType=rs.getInt("type");
			C2.Discount=rs.getDouble("discount");
			C2.Itemname=rs.getString("Iname");
			C2.setValidTime1(rs.getDate("time1"));
			C2.setValidTime2(rs.getDate("time2"));
			//C2.Discount = Double.parseDouble(rs.getString("discount"));
			//C2.CouponType =Integer.parseInt(rs.getString("type"));
			//return C2;
		}
		else
			return null;
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return C2;
}
//finds available coupons for a specific time
@GET
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.APPLICATION_JSON)
@Path("FindCouponByTime") //previous name getcoupons2
public List<Coupon> FindCouponByDate(@QueryParam("var1") String var1){//Checkcoupons2
	PreparedStatement stmt = null;
	ResultSet rs = null;
	DB database = new DB();
	Connection c=null;
	List<Coupon> C2 = new ArrayList<Coupon>();
	//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	Date datevar=null;
	java.sql.Date datevar1=null;
	try {
		datevar = new SimpleDateFormat("yyyy-MM-dd").parse(var1);
		datevar1 = new java.sql.Date(datevar.getTime());
	} catch (ParseException e1) {
		// TODO Auto-generated catch block
		System.out.println("date date date error");
		e1.printStackTrace();
	}
	try {
		c=database.connect();
		String sql="SELECT ID , discount , type , Iname , time1 , time2 from coupon where coupon.time1 <= ? and coupon.time2 >=? ";
		stmt = (PreparedStatement) c.prepareStatement(sql);
		stmt.setDate(1, datevar1);
		//stmt.setDate(1, datevar1);
		stmt.setDate(2, datevar1);
		if(stmt.executeQuery()!=null)
		{
			rs=stmt.executeQuery();
			while (rs.next()) {
			Coupon Cpn= new Coupon();
			Cpn.setCouponID(rs.getInt("ID"));
			System.out.println(Cpn.CouponID);
			Cpn.setDiscount(rs.getDouble("discount"));
			Cpn.setCouponType(rs.getInt("type"));
			Cpn.setItemname(rs.getString("Iname"));
			Cpn.setValidTime1(rs.getDate("time1"));
			Cpn.setValidTime2(rs.getDate("time2"));
			C2.add(Cpn);
		}	
		System.out.println(C2);
			return C2;
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return C2;
}
//finds available coupons for specific item
@GET
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.APPLICATION_JSON)
@Path("FindCouponForItem")//pervious name :getcoupons4 
public List<Coupon> FindCouponForItem(@QueryParam("var") String var){//Checkcoupons4
	PreparedStatement stmt = null;
	ResultSet rs = null;
	DB database = new DB();
	Connection c=null;
	List<Coupon> C2 = new ArrayList<Coupon>();
/*	Date datevar=new Date();
	java.sql.Date datevar1=null;	
	try {
		datevar1 = new java.sql.Date(datevar.getTime());
	} catch (Exception e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}*/
	try {
		c=database.connect();
		String sql="SELECT ID , discount , type , Iname , time1 , time2 from coupon where coupon.Iname=?";//coupon.time1 <= ? and coupon.time2 >=? and 
		stmt = (PreparedStatement) c.prepareStatement(sql);
	//	stmt.setDate(1, datevar1);
	//	stmt.setDate(2, datevar1);
		stmt.setString(1, var);
		if(stmt.executeQuery()!=null)
		{
			rs=stmt.executeQuery();
			while (rs.next()) {
			Coupon Cpn= new Coupon();
			Cpn.setCouponID(rs.getInt("ID"));
			Cpn.setDiscount(rs.getDouble("discount"));
			Cpn.setCouponType(rs.getInt("type"));
			Cpn.setItemname(rs.getString("Iname"));
			Cpn.setValidTime1(rs.getDate("time1"));
			Cpn.setValidTime2(rs.getDate("time2"));
			C2.add(Cpn);
		}	
		return C2;
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return C2;
}
//finds specific type of coupons
@GET
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.APPLICATION_JSON)
@Path("FindCouponsByType")//previous name getcoupons3
public List<Coupon> FindCouponsByType(@QueryParam("var2") String var2){//Checkcoupons3
	PreparedStatement stmt = null;
	ResultSet rs = null;
	DB database = new DB();
	Connection c=null;
	List<Coupon> C2 = new ArrayList<Coupon>();
	//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	int ty=Integer.parseInt(var2);
	//System.out.println(ty);
	Date datevar=new Date();
	java.sql.Date datevar1=null;
	int i=0;
	try {
		//System.out.println(datevar+"this this this");
		datevar1 = new java.sql.Date(datevar.getTime());
		System.out.println(datevar1);
	} catch (Exception e1) {
		// TODO Auto-generated catch block
	//	System.out.println("date date date error");
		e1.printStackTrace();
	}
//	System.out.print(datevar1);
	try {
		c=database.connect();
		String sql="SELECT ID , discount , type , Iname , time1 , time2 from coupon where coupon.time1 <= ? and coupon.time2 >=? and coupon.type=?";
		stmt = (PreparedStatement) c.prepareStatement(sql);
		stmt.setDate(1, datevar1);
		stmt.setDate(2, datevar1);
		stmt.setInt(3, ty);
		if(stmt.executeQuery()!=null)
		{
			rs=stmt.executeQuery();
			//System.out.println("hi");
			while (rs.next()) {
			Coupon Cpn= new Coupon();
			Cpn.setCouponID(rs.getInt("ID"));
			Cpn.setDiscount(rs.getDouble("discount"));
			Cpn.setCouponType(rs.getInt("type"));
			Cpn.setItemname(rs.getString("Iname"));
			Cpn.setValidTime1(rs.getDate("time1"));
			Cpn.setValidTime2(rs.getDate("time2"));
		//	System.out.println(Cpn);
			//System.out.println("hi");
			C2.add(Cpn);
		}	
		return C2;
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return C2;
}
}