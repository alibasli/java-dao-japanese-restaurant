$(document).ready(function () {
	
		$( "#tarih,#tarih1,#tarih2").datepicker({
			dateFormat: "yy-mm-dd",//tarih formatý yy=yýl mm=ay dd=gün
			appendText: "(yil-ay-gun)",//inputun sonuna bu yazýyý yazar.
			autoSize: true,//inputu otomatik boyutlandýrýr
			changeMonth: true,//ayý elle seçmeyi aktif eder
			changeYear: true,//yýlý elee seçime izin verir
			dayNames:[ "pazar", "pazartesi", "salý", "çarþamba", "perþembe", "cuma", "cumartesi" ],//günlerin adý
			dayNamesMin: [ "pa", "pzt", "sa", "car", "per", "cum", "cmt" ],//kýsaltmalar
			defaultDate: +5,//takvim açýlýnca seçili olaný bu günden 10 gün sonra olsun dedik
			/*isRTL: true//takvimi ters çevirir garip bi özellik :D*/
			maxDate: "+2y+1m +2w",//ileri göre bilme zamanýný 2 yýl 1 ay 2 hafta yaptýk
			minDate: "-1y-1m -2w",//geriyi göre bilme alanýný 1 yýl 1 ay 2 hafta yaptýk.bunu istediðiniz gibi ayarlaya bilirsiniz
			monthNamesShort: [ "Ocak", "Subat", "Mart", "Nisan", "Mayis", "Haziran", "Temmuz", "Agustos", "Eylul", "Ekim", "Kasim", "Aralik" ],//ay seçim alanýn düzenledik
			nextText: "ileri",//ileri butonun türkçeleþtirdik
			prevText: "geri",//buda geri butonu için
			showAnim: "bounce",//takvim açýlým animasyonu alta tüm animasyon isimleri yazdým 
			/*fold-blind-bounce-clip-drop-explode-fade-highlight-puff-pulsate-scale-shake-slide-size-transfer*/
			showOn: "both",//inputun yanýna ... butonu koyuyor
		});
		
	});