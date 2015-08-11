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
          url: "../RestoranDurumGuncelle",
          data: {
              'realMonth': realMonth,
              'realYear':  realYear,
                },
          success: function(html) {
         	 $("#takvim").append(html);
         	 $("#yilay").text(Aylar[realMonth] + " " +realYear);
          },
          error: function(errStr) {
        	  alert("hata var");
          }
      });
 });
function takvim_ileri(){
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
        url:   "../RestoranDurumGuncelle",
        data: {
            'realMonth': clickMonth,
            'realYear': clickYear,
        },
        success: function(html) {
            $("#takvim").append(html);
            $("#yilay").text(Aylar[clickMonth] + " " +clickYear);
        },
        error: function(errStr) {
            alert("hata");
        }
    });
};
function takvim_geri(){
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
        url:   "../RestoranDurumGuncelle",
        data: {
            'realMonth': clickMonth,
            'realYear': clickYear,
        },
        success: function(html) {
            $("#takvim").append(html);
            $("#yilay").text(Aylar[clickMonth] + " " +clickYear);
        },
        error: function(errStr) {
            alert("hata");
        }
    });
};
$("#Kayit").click(function(){
	var checkDate=$("input[name=vehicle]").parent().parent().map(function(){
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
			return 
		}).get();
	
	var checkID=$("input[name=vehicle]").parent().parent().map(function(){
		return $(this).attr('text');
		}).get();
	var checkStatus=[];
	for(var i=0;i<checkDate.length;i++){
	$("#" + checkID[i] + " input:checkbox").map(function(){
		checkStatus.push($(this).is(':checked'));
	});
	}
	var yilay=clickYear+"0"+clickMonth;
	if(checkDate=="")
		$("#mesaj").append("Secim Yapilmadi").fadeOut(3000);
	else{
		$.ajax({
	        type: "GET",
	        url:   "../rezerveKayit",
	        data: {
	            'checkDate':JSON.stringify(checkDate),
	            'checkStatus': JSON.stringify(checkStatus),
	            'yilay':yilay,
	        },
	        success: function(html) {
	        	$("#mesaj").append(html).fadeOut(3000);
	        	$.ajax({
	                type: "GET",
	                url:   "../RestoranDurumGuncelle",
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
$("#TumSec").click(function(){
	$("input[name=vehicle]:enabled").attr('checked', true);
	
});