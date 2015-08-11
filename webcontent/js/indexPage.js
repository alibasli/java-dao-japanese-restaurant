$(document).ready(function () {	
	var dil = document.getElementById("dil");	
	$("#dil").change(function(){		
		var dildeger=dil.options[dil.selectedIndex].value;
		if(dildeger=="turkce"){			
			$("#welcome").html("Hos Geldiniz");
			$("#UserName").html("Kullanici Adi ");
			$("#Password").html("Sifre ");
			$("#login").val("Giris");
		}else{
			$("#welcome").html("Welcome");
			$("#UserName").html("User Name ");
			$("#Password").html("Password ");
			$("#login").val("Login");
		}
	});
	$('#mesaj1').hide();
	$('#mesaj2').hide();
	$("#login").click(function()
	{
		$('#mesaj1').hide();
		$('#mesaj2').hide();

		var ad = $('#ad').val();
		var parola = $('#parola').val();
		if (!ad || !parola)
		{
			if ($("#mesaj1").is(":hidden"))
			{
				$("#mesaj1").slideDown("1500");
				if (!ad)
					$("#ad").focus();
				else
					$("#parola").focus();
			}
			else
			{
				$("#mesaj1").slideUp("1500");

			}
			return false;
		}
		var form_data =
		{
			ad : $('#ad').val(),
            parola : $('#parola').val(),
			dil : $('#dil').val()
		};

		$.ajax(
		{
			url : "GirisKontrol",
			type : 'POST',
			data : form_data,
			success : function(data)
			{	
				if(data=="1"){
					window.location = 'secure/MainPage.jsp';
				}else{
					if ($("#mesaj2").is(":hidden"))
					{
						$("#mesaj2").html(data).slideDown("1500");
					}
					else
					{
						$("#mesaj2").slideUp("1500");
					}

					return false;
				}

			},
			error : function()
			{
				alert("Hata meydana geldi. Lütfen tekrar deneyinizzzzz !!!");
			}
		});

		return false;
	});
});