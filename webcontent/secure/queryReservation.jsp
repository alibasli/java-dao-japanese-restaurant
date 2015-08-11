<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="../style/MainPage_style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="../js/tarih/jquery-ui-1.10.custom.js"></script>
<script type="text/javascript" src="../js/tarih/tarih.js"></script>
<link rel="stylesheet" href="../style/tarih/jquery-ui-1.10.0.custom.min.css" />
<script src="../js/Language.js" type="text/javascript"></script>
<script type="text/javascript">
$(document).ready(function () {
	$('#sorgu').click(function(){
	var data_form = {
			tarih1: $('#tarih1').val(),
			tarih2: $('#tarih2').val(),
			kullanicikodu:$('#kullanicikod').val()
	};
	$("#rezerve_tablo").remove();
	  $.ajax({
          type: "POST",
          url: "../RezervasyonSorgula",
          data: data_form,
          success: function(msg) {
         	 $("#reservazyonlar").append(msg);
          },
          error: function(errStr) {
        	  $("#reservazyonlar").append('hata var');
          }
      });
	});
});
</script>
</head>
<body><br />
<h3 style="margin-left:300px;margin-right:auto;" id="QueryReserv">QUERY RESERVASION </h3>
<div id="ali">
&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;<span id="StartDate">Start Date :</span> <input type="text" id="tarih1" style="margin-left:20px;margin-top:40px;"/>

&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;<span id="FinishDate"> Finish Date :</span> <input type="text" id="tarih2" style="margin-left:20px;"/>

&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;<span id="UserCode"> User Code :</span> <input type="text" id="kullanicikod" style="margin-left:20px;"/>
&nbsp;&nbsp;&nbsp; &nbsp;&nbsp; <label id="yetki">(* All Users)</label>
<input type="button" value="sorgula" id="sorgu" />
</div>
<div id="reservazyonlar"></div>
</body>
</html>