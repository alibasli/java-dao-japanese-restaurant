$(document).ready(function () {
	
		$( "#tarih,#tarih1,#tarih2").datepicker({
			dateFormat: "yy-mm-dd",//tarih format� yy=y�l mm=ay dd=g�n
			appendText: "(yil-ay-gun)",//inputun sonuna bu yaz�y� yazar.
			autoSize: true,//inputu otomatik boyutland�r�r
			changeMonth: true,//ay� elle se�meyi aktif eder
			changeYear: true,//y�l� elee se�ime izin verir
			dayNames:[ "pazar", "pazartesi", "sal�", "�ar�amba", "per�embe", "cuma", "cumartesi" ],//g�nlerin ad�
			dayNamesMin: [ "pa", "pzt", "sa", "car", "per", "cum", "cmt" ],//k�saltmalar
			defaultDate: +5,//takvim a��l�nca se�ili olan� bu g�nden 10 g�n sonra olsun dedik
			/*isRTL: true//takvimi ters �evirir garip bi �zellik :D*/
			maxDate: "+2y+1m +2w",//ileri g�re bilme zaman�n� 2 y�l 1 ay 2 hafta yapt�k
			minDate: "-1y-1m -2w",//geriyi g�re bilme alan�n� 1 y�l 1 ay 2 hafta yapt�k.bunu istedi�iniz gibi ayarlaya bilirsiniz
			monthNamesShort: [ "Ocak", "Subat", "Mart", "Nisan", "Mayis", "Haziran", "Temmuz", "Agustos", "Eylul", "Ekim", "Kasim", "Aralik" ],//ay se�im alan�n d�zenledik
			nextText: "ileri",//ileri butonun t�rk�ele�tirdik
			prevText: "geri",//buda geri butonu i�in
			showAnim: "bounce",//takvim a��l�m animasyonu alta t�m animasyon isimleri yazd�m 
			/*fold-blind-bounce-clip-drop-explode-fade-highlight-puff-pulsate-scale-shake-slide-size-transfer*/
			showOn: "both",//inputun yan�na ... butonu koyuyor
		});
		
	});