<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-8">
<title>Main Page</title>
<link href="../style/MainPage_style.css" rel="stylesheet" type="text/css" />
<script src="../js/jquery-1.9.1.js" type="text/javascript"> </script>
<script src="../js/MainPage.js" type="text/javascript"> </script>
<script src="../js/Language.js" type="text/javascript"></script>
<script type="text/javascript">
$(document).ready(function () {	
	var kullanici = '<%=session.getAttribute("Rol") %>';
	if(kullanici!=1)
		{		
		var i=2;
		    for(i;i<5;i++)
			{
		        $(".div_rzv:eq("+i+")").hide();
		    }
		}
	var language = '<%=session.getAttribute("dil") %> ';
	$("#dil").val(language);
});
</script>
</head>

<body >
<div  id="div_body">
 
    <div id="div_header">
	    <div id="div_logo">
            <img alt="toyota" src="../images/Toyota.png" style="width:120px; float:left">
            <select id="dil" style="margin-left:350px; background:white">                 
                 <option value="ingilizce">English</option>
                 <option value="turkce">Turkce</option>
      </select>
        </div>
		<div id="div_buton">
		   <div id="div_butonlar">
		      <a  href="logOut.jsp"><input type="submit" value="Log Out" id="Logout" class="submit"/> </a>
			 <a target="_blank" id="YardimMenu1" href="../dosyalar/YardimMenu.xlsx">  <input type="submit" value="Help Menu" id="HelpMenu" class="submit"/> </a>
			<a target="_blank"  id="AylikMenu1" href="../dosyalar/AylikMenu.xlsx">  <input type="submit" value="Mountly Menu" id="MountlyMenu" class="submit"/> </a> 
			</div>			
			 <h3 id="restName" style="float:right; margin-right:20px;">JAPANESE RESTORANT </h3>
		</div>		
	</div>
	
	<!-- header bitiş -->

	<div id="div_kullanici_rol_bilgisi">
	<hr />
	      <span id="UserName" style="float:left; margin-left:50px; height:20px;">   User Name : </span>
	      <span style="float:left; margin-left:15px;height:20px;"><%=session.getAttribute("Kullanici") %> </span>
	      <span id="Role" style="float:left; margin-left:50px;height:20px;"> Role :</span> 
	      <span style="float:left; margin-left:15px;height:20px; padding-right:20px;">
	      <%String rol="Kullanici"; if(session.getAttribute("Rol").equals("1")==true) rol="Admin";%><%=rol %></span> 
	      <br />
	</div>
<hr />
	<div id="div_top_menu">
	
	    <div class="div_rzv" style="margin-left:150px;" >Make Reservation </div> 
	    <div class="div_rzv" >Query Reservation </div>	
		<div class="div_rzv" >Update / Delete Reservation </div>
		<div class="div_rzv" >Update Restaurant Status</div>
		<div class="div_rzv" >Add the Monthly Menu or Help File</div>
<br />
	<br />
	</div>
	<hr />
	<div id="div_content"></div>
</div>
</body>
</html>