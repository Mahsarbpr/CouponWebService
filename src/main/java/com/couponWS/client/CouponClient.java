package com.couponWS.client;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;

import Coupon.Coupon;
//import couponModel.GenericType;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class CouponClient {
private Client client;
public CouponClient(){
	client = ClientBuilder.newClient();
}
public Coupon ReadCoupon(String SID){
	int IID=Integer.parseInt(SID);
	Coupon cc=client.target("http://localhost:8080/coupon-webservice/webapi/myresource/getcoupons").queryParam("var1",IID).request().get(Coupon.class);
	return cc;
}
public Coupon ReadCoupon2(String SID){
	Coupon cc=client.target("http://localhost:8080/coupon-webservice/webapi/myresource/").path("readcoupon/"+SID).request().get(Coupon.class);
	return cc;
}
public Coupon GetTime(String SID){
	int IID=Integer.parseInt(SID);
	Coupon cc=client.target("http://localhost:8080/coupon-webservice/webapi/myresource/gettime").queryParam("var",IID).request().get(Coupon.class);
	return cc;
}
public List<Coupon> FindCouponByTimeC(String SID){
	//int IID=Integer.parseInt(SID);
	List<Coupon> cc=client.target("http://localhost:8080/coupon-webservice/webapi/myresource/FindCouponByTime").queryParam("var1",SID).request().get(new GenericType<List<Coupon>>(){});
	return cc;
}
public List<Coupon> FindCouponForItemC(String SID){
	//int IID=Integer.parseInt(SID);
	List<Coupon> cc=client.target("http://localhost:8080/coupon-webservice/webapi/myresource/FindCouponForItem").queryParam("var",SID).request().get(new GenericType<List<Coupon>>(){});
	return cc;
}
public List<Coupon> FindCouponByTypeC(String SID){
	//int IID=Integer.parseInt(SID);
	List<Coupon> cc=client.target("http://localhost:8080/coupon-webservice/webapi/myresource/FindCouponsByType").queryParam("var2",SID).request().get(new GenericType<List<Coupon>>(){});
	return cc;
}
public void Delete(String SID){
	//int IID=Integer.parseInt(SID);
	Response response= client.target("http://localhost:8080/coupon-webservice/webapi/myresource/DeleteCoupon").path(SID).request(MediaType.APPLICATION_JSON).delete();
	if(response.getStatus()!=200){
		throw new RuntimeException(response.getStatus()+": there was an error on the server.");
	}
}
public Coupon Update(Coupon c){
	Response response=  client.target("http://localhost:8080/coupon-webservice/webapi/myresource/").path("c.getCouponID()").request(MediaType.APPLICATION_JSON).put(Entity.entity(c, MediaType.APPLICATION_JSON));
	if(response.getStatus()!=200){
		throw new RuntimeException(response.getStatus()+": there was an error on the server.");
	}
	return response.readEntity(Coupon.class);
}
}
