<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-8">
<link href="../style/Popup.css" rel="stylesheet" type="text/css" />
<script src="../js/jquery-1.9.1.js" type="text/javascript"> </script>
<script src="../js/MakeRezerve.js" type="text/javascript"></script>
<script src="../js/Language.js" type="text/javascript"></script>
<style>
#gunler table tr{
    height:50px;
}
#gunler tr th {    
    width:14%;
    border-style: solid;
    border-width:1px;
    }
#takvim tr td{
    border-style: solid;
    border-width:1px;
}
</style>
</head>
<body>
<div id="div_days">
<div style="background: #B8C0C4;">
<table width="100%">
  <tr >
      <td style=" width: 15%">
          <button id="Kayit" style="margin-left:30px; float:left; margin-top:30px; height:40px; width:auto;" onclick="restoranKayit(event)">Rezervasyon Kaydet</button>
      </td>
      <td style=" width: 150px;"><h3 id="mesaj"></h3></td>
      <td>
          <input type="button" id="back"  value="Back" class="submit" style="width:100px" />
      </td>
	  <td>
         <center> <h2 id="yilay" style="  white-space:nowrap;"></h2></center>
      </td>
      <td>
          <input  type="button" id="next"  value="Next" class="submit" style="width:100px; float:left" />
      </td>
      <td><button id="TumSec" style="margin-right:30px; float:right; height:40px; width:auto; margin-top: 30px;">Hepsini Sec</button></td>
   </tr>
</table>
</div>
<br/>
<table id="gunler" style="width:100%;">
<tr>
<th ><h3  id="sunday"></h3></th>
<th ><h3  id="monday"></h3></th>
<th ><h3  id="tuesday"></h3></th>
<th ><h3  id="wednesday"></h3></th>
<th ><h3  id="thursday"></h3></th>
<th ><h3  id="friday"></h3></th>
<th ><h3  id="saturday"></h3></th>
</tr>
</table>
</div>
<table id="takvim" style="margin-left:auto; margin-right:auto; margin-top:5px; width:100%"></table>

        <div id="toPopup">	ali </div> <!--toPopup end--> 
	    <div class="loader">   </div>
	    <div id="backgroundPopup" onclick="closePopup()"> </div>
</body>
</html>