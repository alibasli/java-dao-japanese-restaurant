

 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<title> Login </title>
<link href="style/index_style.css" rel="stylesheet" type="text-css" />
<script src="js/jquery-1.9.1.js" type="text/javascript"> </script>
<script src="js/indexPage.js" type="text/javascript"></script>
<meta http-equiv="Content-Type" content="text/jsp; charset=ISO-8859-9">
</head>

<body>
<div id="div_body">

  <div id="div_logo">  
      <img alt="toyota" src="images/Toyota.png" style="width:60px; float:left">
      
      <select id="dil" style="margin-left:500px; background:white">                 
                 <option value="ingilizce">English</option>
                 <option value="turkce">Turkce</option>
      </select>
  </div>
  
  <div id="div_login">  
	
	<center><h2 id="welcome"> WELCOME  </h2>	
	<div id="divLoginPanel" style="background:#e3e3e3;width:300px; margin:auto; "><br>
	    <!-- kullanici bilgileri-->
	    <table class="div_kullanici_adi" ><tr>
	    <td><span id="UserName" style="white-space:nowrap; float: left">User Name </span></td><td><input type="text" id="ad" style=" margin-right:10px;"></td>
	    </tr>
	    <tr>
	    <td><span id="Password" style="white-space:nowrap; float: left;">Password </span></td><td><input type="password" id="parola" style=" margin-right:10px;"></td>
	    </tr>
	    </table>
		<input type="submit" value="Login" id="login" style="margin-top:5px; height:35px; width:70px;">
		<div class="div_kullanici_adi">
		<br /><br />
		   <label id="mesaj1">Kullanici Adi ve Sifre Bos Gecilemez</label>
		   <label id="mesaj2"></label>
		</div>				
		
	</div>	
	</center>	
  </div>  
</div>
</body>

</html>