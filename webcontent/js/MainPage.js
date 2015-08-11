$(document).ready(function () {	
	$("#div_content").load("MakeRezerve.jsp");
	$(".div_rzv:first").css("background","#e3e3e3");
	$(".div_rzv").click(function(){
		var index=$(this).index(".div_rzv");
		if(index==0){
			$(".div_rzv").css("background","white");
			$(this).css("background","#e3e3e3");
	        $("#div_content").load("MakeRezerve.jsp");
		}
		else if(index==1){
			$(".div_rzv").css("background","white");
			$(this).css("background","#e3e3e3");
			$("#div_content").load("queryReservation.jsp");
		}
		else if(index==2){
			$(".div_rzv").css("background","white");
			$(this).css("background","#e3e3e3");
			$("#div_content").load("yonetim/UpDelRezervation.jsp");
		}
		else if(index==3){
			$(".div_rzv").css("background","white");
			$(this).css("background","#e3e3e3");
			$("#div_content").load("yonetim/RestoranDurumGuncelle.jsp");
		}
		else if(index==4){
			$(".div_rzv").css("background","white");
			$(this).css("background","#e3e3e3");
			$("#div_content").load("yonetim/AddMonthlyMenu.jsp");
		}
	});
	 xmlhttp = new XMLHttpRequest();
     xmlhttp.open("POST","dosyaisim.xml",false);
     xmlhttp.send();
     xmlDoc=xmlhttp.responseXML;

     AylikMenu=xmlDoc.getElementsByTagName("TITLE")[0].childNodes[0].nodeValue;
     $("#AylikMenu").attr('href' , '../dosyalar/'+AylikMenu );
     YardimMenu=xmlDoc.getElementsByTagName("TITLE")[1].childNodes[0].nodeValue;
     $("#YardimMenu").attr('href' , '../dosyalar/'+YardimMenu );    	
	
	
});