$(document).ready(function () {
	$('#sorgula').click(function(){
	var data_form = {
			tarih: $('#tarih').val(),
			kullanicikodu:$('#kullanicikod').val()
	};
	$("#temizle").remove();
	$("#mesaj").fadeOut(1000);
	  $.ajax({
          type: "GET",
          url: "../RezervasyonSilmeGuncelleme",
          data: data_form,
          success: function(msg) {
         	 $("#up_del_div").html(msg);
          },
          error: function(errStr) {
        	  $("#up_del_div").append('hata var');
          }
      });
	});
});
function Update(){
	var kahvaltiDurum=$('.Kahvalti').is(':checked');
	var ogleDurum=$('.Ogle').is(':checked');
	var aksamDurum=$('.Aksam').is(':checked');
	var sorguTarih=$('#tarih').val();
	var kullaniciKod=$('#kullanicikod').val();
	$.ajax({
        type: "GET",
        url:   "../UpDellRezervavtion",
        data: {
            'kahvaltiDurum':kahvaltiDurum,
            'ogleDurum': ogleDurum,
            'aksamDurum':aksamDurum,
            'sorguTarih':sorguTarih,
            'kullaniciKod':kullaniciKod,
        },
        success: function(html) {
        	$("#mesaj").html(html).fadeIn(1000);
        },
        error: function(errStr) {
            alert("Bir Sorun Olustu");
        }
    });
}