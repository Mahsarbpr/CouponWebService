package com.couponWS.client;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import Coupon.Coupon;

public class CouponClientTest {

	@Test
	public void testReadCoupon() {
		CouponClient client=new CouponClient();
		Coupon c=client.ReadCoupon("13");
		System.out.println(c);
		assertNotNull(c);
	}
	@Test
	public void testReadCoupon2() {
		CouponClient client=new CouponClient();
		Coupon c=client.ReadCoupon2("13");
		System.out.println(c);
		assertNotNull(c);
	}

	@Test
	public void testFindCouponByTime(){
		CouponClient client=new CouponClient();
		List<Coupon> c=client.FindCouponByTimeC("2015-11-18");
		System.out.println(c);
		assertNotNull(c);
		//fail("Not yet implemented");
	}
	@Test
	public void testFindCouponForItem(){
		CouponClient client=new CouponClient();
		List<Coupon> c=client.FindCouponForItemC("item5");
		System.out.println(c);
		assertNotNull(c);
		//fail("Not yet implemented");
	}
	@Test
	public void testFindCouponByType(){
		CouponClient client=new CouponClient();
		List<Coupon> c=client.FindCouponByTypeC("1");
		System.out.println(c);
		assertNotNull(c);
		//fail("Not yet implemented");
	}
	@Test
	public void testDeleteCoupon(){
		CouponClient client=new CouponClient();
		client.Delete("51");
	}
	@Test
	public void testUpdateCoupon(){
		Coupon c= new Coupon();
		c.setCouponID(13);
		c.setDiscount(0.2);
		c.setCouponType(1);
		c.setItemname("item31");
		c.setItemID(3111);

		Date datevar=null;
		Date datevarp=null;
/*//		java.sql.Date datevar1=null;
	//	java.sql.Date datevar2=null;
		
		//try {
//			System.out.println(datevar+"this this this");
			//datevar = new SimpleDateFormat("yyyy-MM-dd").parse();
			//datevarp=new SimpleDateFormat("yyyy-MM-dd").parse(ValidTime2);
			datevar1 = new java.sql.Date(datevar.getTime());
			datevar2 = new java.sql.Date(datevarp.getTime());
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
		//	System.out.println("date date date error");
			e1.printStackTrace();
		}*/

		c.setValidTime1(datevar);
		c.setValidTime2(datevarp);
		CouponClient client=new CouponClient();
		c=client.Update(c);
		assertNotNull(c);
	}
}
