/* Inicialización en español para la extensión 'UI date picker' para jQuery. */
/* Traducido por Vester (xvester@gmail.com). */
jQuery(function($){
$.datepicker.regional['es'] = {		closeText : 'Cerrar',
		prevText : '&#x3C;Ant',
		nextText : 'Sig&#x3E;',
		currentText : 'Hoy',
		monthNames : [ 'enero', 'febrero', 'marzo', 'abril', 'mayo', 'junio',
				'julio', 'agosto', 'septiembre', 'octubre', 'noviembre',
				'diciembre' ],
		monthNamesShort : [ 'ene', 'feb', 'mar', 'abr', 'may', 'jun', 'jul',
				'ago', 'sep', 'oct', 'nov', 'dic' ],
		dayNames : [ 'domingo', 'lunes', 'martes', 'miércoles', 'jueves',
				'viernes', 'sábado' ],
		dayNamesShort : [ 'dom', 'lun', 'mar', 'mie', 'jue', 'vie', 'sab' ],
		dayNamesMin : [ 'D', 'L', 'M', 'X', 'J', 'V', 'S' ],
		weekHeader : 'Sm',
		dateFormat : 'dd/mm/yy',
		firstDay : 1,
		isRTL : false,
		showMonthAfterYear : false,
		yearSuffix : ''
	};
	$.datepicker.setDefaults($.datepicker.regional['es']);
});

$(document).ready(function() {

	$(function() {
		$("[fecha]").datepicker();
	});

	$("input").change(function() {
		$.ajax({
			url : "/au/update",
			type : "post",
			dataType : "json",
			data : {
				"id" : this.id,
				"value" : this.value
			},
			success : function(result) {
				$.each(result, function(i, object) {
					if (object.tipoMensaje != null) {
						var partida = "#" + object.idPartida;

						$(partida).tooltip({
							content : object.mensaje,
							items : "input",
							show : true
						});

						$(partida).tooltip("open");
					} else {
						$("#" + object.id).val(object.value);
					}
				})

			}
		});
	});
});
