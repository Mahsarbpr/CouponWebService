# CouponWebService
Developing a RESTful web service which simplifies coupon management easier was 
the main purpose of this project. Web services allows to expose the functionality
of existing code over the network So that other applications can use the
functionality of program. Web services are hardware, programming language, and
operating system independent.

The RESTful API developed in this project will demonstrate a Complete
Create,_read,_update_and_delete (CRUD), validation check, price computation,
searching Coupon resources stored in a MySQL database. We will start by
explaining the packages we created. Four main packages are created for the web
service.

com.couponWS - which contains Myresource.java class, this class is the main part
of web service because all the http methods, endpoints, web service functionalities
are defined in this class.

com.couponWS.client – For testing the web service we need to create a client
which calls the URI’s of coupon web service, this package have a CouponClient
class which contains all the clients for each URI.

Coupon – this package contains coupon class. Coupon class gives the web service
information about each coupon.

db – DB class in this package, makes the connection with coupon database.

A coupon will have only the following properties in coupon.java class
CouponID, Discount, CouponType, Itemname, ItemID, Starttime, Endtime
Coupon.
And JSON representation, which is actually the de facto media type used with REST
nowadays:

{"CouponID":13,
"Discount":0.3,
"CouponType":1,
"ItemID":0,
"Itemname":”item1”,
”ValidTime1":”2014-09-09T00:00:00”,
”ValidTime2:” 2014-09-09T00:00:00”}
## Functionalities of Coupon Web Service
Below are the description for each coupon web service functionalities:

**Add single coupon**, It gets the information of the coupon from a form, and insert
them to database (FR-2).

**Delete coupon**, input for this funciton is coupon id, this URI deletes the coupon
with the given coupon id (FR-1).

**Read coupon information**, receiving the coupon id as input, this function returns
important for user attributes of coupon (FR-8).

**Get coupon discount**, this function receives item ID as input, finds the coupon for
the item and returns the coupon type and discount amount (FR-3).

**Get coupon time**, this functionality is used for validation check, in a way that by
accepting coupon id as input, it returns start and end time of the coupon (FR-4).

**Search coupons by time**, this is a function to seach for available coupons for a
specific day, date is the input for this function and array of available coupons will
be returned (FR-5).

**Search coupons by type**, this is a function to seach for available coupons of certain
type, type is the input for this function and array of available coupons will be
returned (FR-7).

**Search coupons by item name**, this is a function to seach for available coupons for a
specific item, item name is the input for this function and array of available
coupons will be returned (FR-6).

## Database Architecture
MySQL is a popular choice of database for use in web applications. The coupon
data, user’s data are stored in a MySQL tables. Coupon table has columns for the
Coupon ID (ID), coupon discount (discount), coupon type (type), Item name
(Iname), Item ID (IID), coupon starting time (time1) and coupon ending time
(time2). User table has columns for username (username), password (password),
an attribute to show if the user is User or Client or Admin (UorAorC), which store
the user belongs to (storeID), and email (email).

**Primary key**
CouponID was set as Primary key, because we want coupon id to be unique and we
used this column in most important queries. And based on the primary key
definition it has an associated index, for fast query performance and fast look ups.
We set up the primary key to be incremental, so that each time we add a coupon, the ID will be set to coupon in incremental form.

**Indexing**
To speed up searching for coupons based on the type, time and item name, these
attributes are set as indexes. Finding rows with specific column values, are quickly
using indexes. 

Since in this project we have searching coupons functionality, which we compare
coupon attributes with values, as a result hash index is better for project.
For our scenario, we need three indexes, one on ID (since this is the primary key,
then it already has an implicit index), one on item name and one on coupon type
Here is the code for creating coupon table, primary key and indexes:

CREATE TABLE `coupon` (
`ID` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
`discount` DOUBLE NOT NULL,
`type` INT(11) NOT NULL,
`Iname` CHAR(50) NOT NULL,
`IID` INT(11) UNSIGNED NOT NULL,
`time1` DATE NOT NULL,
`time2` DATE NOT NULL,
PRIMARY KEY (`ID`) USING HASH,
INDEX `IndexType` (`type`) USING HASH,
INDEX `IndexIname` (`Iname`) USING HASH,
INDEX `IndexTime1` (`time1`) USING HASH
)

COLLATE='latin1_swedish_ci'
ENGINE=InnoDB
AUTO_INCREMENT=45
;

And following are some example queries used in the project:

SELECT discount , type from coupon where id="+IC

SELECT time1 , time2 from coupon where id="+IC

SELECT DISTINCT discount , Iname , time1 , time2 from coupon
where id="+var1

SELECT ID , discount , type , Iname , time1 , time2 from coupon
where coupon.time1 <= ? and coupon.time2 >=?

SELECT ID , discount , type , Iname , time1 , time2 from coupon
where coupon.time1 <= ? and coupon.time2 >=? and coupon.Iname=?

SELECT ID , discount , type , Iname , time1 , time2 from coupon
where coupon.time1 <= ? and coupon.time2 >=? and coupon.type=?


