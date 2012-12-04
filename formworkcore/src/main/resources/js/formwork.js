/* Inicialización en español para la extensión 'UI date picker' para jQuery. */
/* Traducido por Vester (xvester@gmail.com). */
jQuery(function($) {
	$.datepicker.regional['es'] = {
		closeText : 'Cerrar',
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
				if (result.resultado == "ERROR") {
					$.each(result.responseObjects, function(j, error) {
						var partida = "#" + error.idPartida;

						$(partida).tooltip({
							content : error.mensaje,
							items : "input",
							tooltipClass : "ui-state-error",
							show : true
						});

						$(partida).tooltip("open");

					});
				} else if (result.resultado == "SUCCESS") {
					$.each(result.responseObjects, function(j, partida) {
						$("#" + partida.id).val(partida.value);
						$("#" + partida.id).tooltip("destroy");
					});
				}
			}
		});
	});
});
