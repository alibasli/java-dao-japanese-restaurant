<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="../js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="../js/tarih/jquery-ui-1.10.custom.js"></script>
<script type="text/javascript" src="../js/tarih/tarih.js"></script>
<link rel="stylesheet" href="../style/tarih/jquery-ui-1.10.0.custom.min.css" />
<script src="../js/Language.js" type="text/javascript"></script>
<script src="../js/UpDelReservation.js" type="text/javascript"></script>
</head>
<body><br />
<h3 style="margin-left:300px;margin-right:auto;" id="UpDelReserv">UP / DEL RESERVATION</h3>
<div id="ali" >
&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;<span id="SelectDate">Select Date :</span> <input type="text" id="tarih" style="margin-left:100px;margin-top:10px;"/>
&nbsp;&nbsp;&nbsp; &nbsp;&nbsp; <span id="UserCode">User Code :</span> <input type="text" id="kullanicikod" style="margin-left:20px;"/>
<input type="button" id="sorgula" value="Query"/>
</div> <br />
<div id="up_del_div" style="float:left"></div><div style="float:left;"><label id="mesaj" style="font-size: 20px;"></label></div>
</body>
</html>