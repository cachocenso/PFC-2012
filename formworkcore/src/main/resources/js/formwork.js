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


// Establezco un manejador para el evento onReady del documento
// mediante jQuery
$(document).ready(function() {

	// Inicializo los campos fecha con jQuery UI datepicker y pongo formateos.
	$(function() {
		$("[fecha]").datepicker({changeMonth: true, 
								 changeYear: true,
								 showWeek: true,
								 showOn: "button",
								 buttonImage: "au/~/img/calendar.gif",
								 buttonImageOnly: true,
								 buttonText: "Pulse para mostrar el calendario",
								 appendText: "(dd/mm/aaaa)"});
		
		$("[dinero]").format({type:'decimal',precision: {parteEntera:15,parteDecimal:2},autofix:false});
		$("[cadnum]").format({type:'cadnum'});
	});

	// Esteblezco manejador para el evento onClick del botón de enviar
	$("button#firmarEnviar, button#enviar").click(function() {
		$.ajax({
			url : "au/submit",
			type : "post",
			dataType : "json",
			data : {
				"id" : this.id				 
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
						var reg = result.responseObjects[0];
						
						if ($("#firmarEnviar") != null) {
							alert("A firmar:\n" + reg);
						}
						
						$.get("dummypres", function(result) {
							if (result == "ERROR") {
								alert("ERROR: No se pudo completar la presentación");
							}
							else {
								alert("Presentación realizada con éxito:\nNúmero de justificante: " + result);
							}
						});
						
				}
			}
		});
	});
	
	
	// Establezco un manejador para los eventos 
	// onChange de los elementos input
	$("input,select").change(function() {
		// Cada vez que un input cambie su valor
		// se hace una llamada AJAX al servidor.
		$.ajax({
			url : "au/update",
			type : "post",
			dataType : "json",
			data : {
				"id" : this.id,
				"value" : this.type == "checkbox" ? this.checked : this.value
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
						$("#" + partida.id).val(partida.formattedValue);
						try {
							$("#" + partida.id).tooltip("destroy");
						} catch (e) {
							// Si el elemento $("#" + partida.id)
							// no tenia un tooltip asignado por un erro
							// un ejecución anterior se produce una excepción
							// que puedo ignorar.
						}
					});
				}
			}
		});
	});
});
