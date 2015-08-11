var Aylar=["Ocak","Subat","Mart","Nisan","Mayis","Haziran","Temmuz","Agustos","Eylul","Ekim","Kasim","Aralik"];
var cal = new Date();
var realYear= cal.getFullYear();
var realMonth = cal.getMonth();
var realDay = cal.getDate();
var clickYear = realYear;
var clickMonth = realMonth;
$(document).ready(function(){
	
	  $.ajax({
          type: "GET",
          url: "../rezerve",
          data: {
              'realMonth': realMonth,
              'realYear':  realYear,
                },
          success: function(html) {        	 
         	 $("#takvim").html(html);
         	 $("#yilay").text(Aylar[realMonth] + " " +realYear);
          },
          error: function(errStr) {
        	  alert("hata");
          }
      });

$("#next").click(function(){
	if (clickMonth == 11){
		clickMonth = 0;
		clickYear = clickYear+1;
	}
	else{
		clickMonth =clickMonth+1;
	}
	$(".temizle").remove();
	$.ajax({
        type: "GET",
        url:   "../rezerve",
        data: {
            'realMonth': clickMonth,
            'realYear': clickYear,
        },
        success: function(html) {
            $("#takvim").html(html);
            $("#yilay").text(Aylar[clickMonth] + " " +clickYear);
        },
        error: function(errStr) {
            alert("hata");
        }
    });
});
//function takvim_geri(){
$("#back").click(function(){
	if (clickMonth == 0){
		clickMonth = 11;
		clickYear = clickYear-1;
	}
	else{
		clickMonth =clickMonth-1;
	}
	$(".temizle").remove();
	$.ajax({
        type: "GET",
        url:   "../rezerve",
        data: {
            'realMonth': clickMonth,
            'realYear': clickYear,
        },
        success: function(html) {
            $("#takvim").html(html);
            $("#yilay").text(Aylar[clickMonth] + " " +clickYear);
        },
        error: function(errStr) {
            alert("hata");
        }
    });
});
$("#Kayit").click(function(){
	var checkDate=$("input[name=vehicle]:checked").parent().parent().map(function(){
		if($(this).attr('text')<10){
			if(clickMonth<10)
				return clickYear+"0"+clickMonth+"0"+$(this).attr('text');
			else
				return clickYear+""+clickMonth+""+"0"+$(this).attr('text');
		}
		else{
			if(clickMonth<10)
				return clickYear+"0"+clickMonth+$(this).attr('text');
			else
				return clickYear+""+clickMonth+""+$(this).attr('text');
		}
		}).get();
	
	var checkID=$("input[name=vehicle]:checked").parent().parent().map(function(){
		return $(this).attr('text');
		}).get();
	var checkStatus=[];
	for(var i=0;i<checkDate.length;i++){
	$("#" + checkID[i] + " input:checkbox").map(function(){
		checkStatus.push($(this).is(':checked'));
	});
	}
	if(checkDate=="")
		$("#mesaj").append("Secim Yapilmadi").fadeOut(3000);
	else{
		$.ajax({
	        type: "GET",
	        url:   "../MakeRezerveKayit",
	        data: {
	            'checkDate':JSON.stringify(checkDate),
	            'checkStatus': JSON.stringify(checkStatus),
	        },	       
	        success: function(html) {
	        	$("#mesaj").append(html).fadeOut(3000);
	        	$.ajax({
	                type: "GET",
	                url:   "../rezerve",
	                data: {
	                    'realMonth': clickMonth,
	                    'realYear': clickYear,
	                },
	                success: function(html) {
	                    $("#takvim").html(html);
	                    $("#yilay").text(Aylar[clickMonth] + " " +clickYear);
	                },
	                error: function(errStr) {
	                    alert("hata");
	                }
	            });
	        },
	        
	        error: function(errStr) {
	            alert("Bir Sorun Olustu");
	        }
	    });
	}
});
});
function pop(event){	
	var rezerveTarih=event.target.id;
	var tip=event.target.title;
	$.ajax({
        type: "GET",
        url:   "../Popup",
        data: {
            'rezerveTarih': rezerveTarih,
            'tip':tip,
        },
        success: function(html) {
         //  myWindow = window.open("", "myWindow","toolbar=yes, scrollbars=yes,top=500, left=700,width=350, height=400");
         //  myWindow.document.write(html);
        	$("#toPopup").html(html);
        },
        error: function(errStr) {
            alert("hata");
        }
    });
	$(document).ready(function(){
        $("div.loader").show();
		setTimeout(function(){ // then show popup, deley in .5 second
		$("#toPopup").fadeIn(0500); // fadein popup div
		$("#backgroundPopup").css("opacity", "0.7"); // css opacity, supports IE7, IE8
		$("#backgroundPopup").fadeIn(0001);
		}, 500); // .5 second
        return false;
    });
}
function closePopup() {
    $(document).ready(function(){
            $("div.loader").fadeOut('normal');
            $("#toPopup").html(" ");
			$("#toPopup").fadeOut("normal");
			$("#backgroundPopup").fadeOut("normal");
			popupStatus = 0;  // and set value to 0
    });
};
$("#TumSec").click(function(){
	$("input[name=vehicle]:enabled").attr('checked', true);
	
});
