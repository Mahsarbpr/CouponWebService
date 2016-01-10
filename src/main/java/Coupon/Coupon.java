package Coupon;
import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.json.JSONException;
import org.json.JSONObject;
@XmlRootElement
public class Coupon {

public int CouponID;
public double Discount;
public int CouponType; //There should be better option for defining Coupon Type
public String Itemname;
public int ItemID;
public Date ValidTime1;// = new Date();
public Date ValidTime2;// = new Date();

//@XmlElement(name="CouponID")
public int getCouponID() {
	return CouponID;
}
//@XmlElement(name="Itemname")
public String getItemname() {
	return Itemname;
}
public void setItemname(String itemname) {
	Itemname = itemname;
}
public void setCouponID(int couponID) {
	CouponID = couponID;
}
//@XmlElement(name="Discount")
public double getDiscount() {
	return Discount;
}
public void setDiscount(double discount) {
	Discount = discount;
}
//@XmlElement(name="CouponType")
public int getCouponType() {
	return CouponType;
}
public void setCouponType(int couponType) {
	CouponType = couponType;
}
//@XmlElement(name="StartingTime")
public Date getValidTime1() {
	return ValidTime1;
}
public void setValidTime1(Date validTime1) {
	ValidTime1 = validTime1;
}
//@XmlElement(name="EndTime")
public Date getValidTime2() {
	return ValidTime2;
}
public void setValidTime2(Date validTime2) {
	ValidTime2 = validTime2;
}
//@XmlElement(name="ItemID")
public int getItemID() {
	return ItemID;
}
public void setItemID(int itemID) {
	ItemID = itemID;
}
@Override
public String toString() {
	//try {
      //   takes advantage of toString() implementation to format {"a":"b"}
      //  return new JSONObject().put("CouponID", CouponID).put("Discount", Discount).put("CouponType", CouponType).put("Itemname", Itemname).put("ItemID", ItemID).put("StartingTime", ValidTime1).put("EndTime", ValidTime2).toString();
   // } catch (JSONException e) {
     //   return null;
    //}
	  return getClass().getSimpleName() + "CouponID=" + CouponID + ", Discount="+ Discount+",Coupon Tpye="+CouponType+", Item name="+Itemname+", Starting time"+ValidTime1+", Expiration Time="+ValidTime2;
}

}
