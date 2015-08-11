$(document).ready(function () {	
	var dil = document.getElementById("dil");
	var dildeger=dil.options[dil.selectedIndex].value;
	setTimeout(function(){
		if(dildeger=="turkce")	
		turkce();
	else
		english();
	 },500);
	$("#dil").change(function(){
		var dildeger1=dil.options[dil.selectedIndex].value;
		if(dildeger1=="turkce"){
			turkce();
		}else{
			english();
		}
	});
	$("#back").click(function(){
		setTimeout(function(){
		var dildeger2=dil.options[dil.selectedIndex].value;
		if(dildeger2=="turkce"){
			turkce();
		}else{
			english();
		}
		},500);
	});
	$("#next").click(function(){
		setTimeout(function(){
		var dildeger2=dil.options[dil.selectedIndex].value;
		if(dildeger2=="turkce"){
			turkce();
		}else{
			english();
		}
		 },500);
	});
	$("#sorgu").click(function(){
		setTimeout(function(){
		var dildeger2=dil.options[dil.selectedIndex].value;
		if(dildeger2=="turkce"){
			turkce();
		}else{
			english();
		}
		 },500);
	});
	$("#sorgula").click(function(){
		setTimeout(function(){
		var dildeger2=dil.options[dil.selectedIndex].value;
		if(dildeger2=="turkce"){
			turkce();
		}else{
			english();
		}
		 },500);
	});
	$("#Kayit").click(function(){
		setTimeout(function(){
			var dildeger2=dil.options[dil.selectedIndex].value;
			if(dildeger2=="turkce"){
				turkce();
			}else{
				english();
			}
			 },1000);
		});
	function turkce(){
		$("#restName").html("Japon Resturant");
		$("#Logout").val("Cikis");
		$("#HelpMenu").val("Yardim Menu");
		$("#MountlyMenu").val("Aylik Menu");
		$("#UserName").html("Kullanici Adi :");
		$("#Role").html("Rol :");
		$(".div_rzv :eq("+0+")").html("Rezervazyon Yap");
		$(".div_rzv :eq("+1+")").html("Rezervazyon sorgula");
		$(".div_rzv :eq("+2+")").html("Rezervazyon Sil / Guncelle");
		$(".div_rzv :eq("+3+")").html("Rezervazyon Durumu Guncelle");
		$(".div_rzv :eq("+4+")").html("Aylik Menu veya Yardim Menusu Ekle");
		$("#sunday").html("Pazar");
		$("#monday").html("Pazartesi");
		$("#tuesday").html("Sali");
		$("#wednesday").html("Carsamba");
		$("#thursday").html("Persembe");
		$("#friday").html("Cuma");
		$("#saturday").html("Cumartesi");
		$("#back").val("Geri");
		$("#next").val("Ileri");
		$("#AddtheFile").val("Yukle");
		$("#AddFile").html("Aylik Menu veya Yardim Dosyasi ekleme");
		$("#selectAfile").html("Yuklenecek Dosya Aylik menu /Yardim menu ");
		$("#SelectFileType").html("Yuklenecek Dosya Turu ");
		$("#SelectFile").html("Yuklenecek Dosyayi Seciniz");
		$("#UpDelReserv").html("REZERVAZYON SIL / GUNCELLE");
		$("#SelectDate").html("Tarih Seciniz :");
		$("#UserCode").html("Kullanici Kodu :");
		$("#sorgula").val("Sorgula");
		$("#QueryReserv").html("REZERVAZYON SORGULA");
		$("#StartDate").html("Baslangic Tarihi :");
		$("#FinishDate").html("Bitis Tarihi :");
		$("#yetki").html("(* Butun Kullanicilar )");
		$("#sorgu").val("Sogula");
		$(".breakfast").html("Kahvalti");
		$(".lunch").html("Oglen");
		$(".dinner").html("Aksam");
		$(".durum").html("Restoran Kapali");
		$(".queryDate").html("Tarih");
		$("#Kul_Bilgi").html("Kullanici Adi");
		$("#rezerve").html("Rezervasyon");
	}	
	function english(){
		$("#restName").html("JAPANESE RESTORANT");
		$("#Logout").val("Log Out");
		$("#HelpMenu").val("Help Menu");
		$("#MountlyMenu").val("Mountly Menu");
		$("#UserName").html("User Name :");
		$("#Role").html("Role :");
		$(".div_rzv :eq("+0+")").html("Make Reservation");
		$(".div_rzv :eq("+1+")").html("Query Reservation");
		$(".div_rzv :eq("+2+")").html("Update / Delete Reservation");
		$(".div_rzv :eq("+3+")").html("Update Restaurant Status");
		$(".div_rzv :eq("+4+")").html("Add the Monthly Menu or Help File");
		$("#sunday").html("Sunday");
		$("#monday").html("Monday");
		$("#tuesday").html("Tuesday");
		$("#wednesday").html("Wednesday");
		$("#thursday").html("Thursday");
		$("#friday").html("Friday");
		$("#saturday").html("Saturday");
		$("#back").val("Back");
		$("#next").val("Next");
		$("#AddtheFile").val("Add File");
		$("#AddFile").html("Add Mountly Menu or Help Menu");
		$("#selectAfile").html("select a file Mountly Menu /Help Menu ");
		$("#SelectFileType").html("Select the Type Of File ");
		$("#SelectFile").html("Select the File");
		$("#UpDelReserv").html("UP / DEL RESERVATION");
		$("#SelectDate").html("Select Date :");
		$("#UserCode").html("User Code :");
		$("#sorgula").val("Query");
		$("#QueryReserv").html("QUERY RESERVASION");
		$("#StartDate").html("Start Date :");
		$("#FinishDate").html("Finish Date :");
		$("#yetki").html("(* All Users)");
		$("#sorgu").val("Query");
		$(".breakfast").html("Breakfast");
		$(".lunch").html("Lunch");
		$(".dinner").html("Dinner");
		$(".durum").html("Restaurant Closed");
		$(".queryDate").html("Date");
		$("#Kul_Bilgi").html("User Name");
		$("#rezerve").html("Reservation");
	}
});