/*
 *

 * Copyright (c) 2010 C. F., Wong (<a href="http://cloudgen.w0ng.hk">Cloudgen Examplet Store</a>)
 * Licensed under the MIT License:
 * http://www.opensource.org/licenses/mit-license.php
 *
 */
/*
 *
 * Copyright (c) 2011 Cloudgen Wong (<a href="http://www.cloudgen.w0ng.hk">Cloudgen Wong</a>)
 * Licensed under the MIT License:
 * http://www.opensource.org/licenses/mit-license.php
 *
 */
// version 1.05 
// fix the problem of jQuery 1.5 when using .val() 
// fix the problem when precision has been set and the input start with decimal dot or comma ,e.g. precision set to 3 and input with ".1234"
//____________________________________________AEAT_____________________________
// Version AEAT:
//  se a�ade dependencia con ZKOSS
//  funcion log para depurar en firebug
/// he cambiado esta '$(this).val()' expresi�n en muchos sitios por 'input.val' porque en ie no toma el valor esperado dentro de 'filtrarcadenas'

var versionScript="1.05";
//  1.05, 19-nov-2012: Cuando se pone 0 decimales evitamos que salga la coma al final
var idCajaNRC;

function setIdCajaNRC(idCaja){
	idCajaNRC = idCaja;
}


function setNRC(NRC_devuelto){
	zk.Widget.$(jq('$T1_NRC')[0]).setText(NRC_devuelto);
	zAu.send(new zk.Event(zk.Widget.$(jq('$T1_NRC')[0]),'onChange',{'value':NRC_devuelto,'position':0}));
	//var mensajeAviso = "El N�mero de Referencia Completa se ha incorporado a la presentaci�n";
	//avisoNRC(mensajeAviso);
}

jQuery.fn.log = function (msg) {
    console.log("%s: %o", msg, this);
    return this;
};

function log(msg){
	  if (window.console && console.log) {
	    console.log(msg); //for firebug
	  }
//	  document.write(msg); //write to screen
	  $("#logBox").append(msg); //log to container
	}

var email = {
	tldn : new RegExp(
			"^[^\@]+\@[^\@]+\.(A[C-GL-OQ-UWXZ]|B[ABD-JM-OR-TVWYZ]|C[ACDF-IK-ORUVX-Z]|D[EJKMOZ]|E[CEGR-U]|F[I-KMOR]|G[ABD-IL-NP-UWY]|H[KMNRTU]|I[DEL-OQ-T]|J[EMOP]|K[EG-IMNPRWYZ]|L[A-CIKR-VY]|M[AC-EGHK-Z]|N[ACE-GILOPRUZ]|OM|P[AE-HKL-NR-TWY]|QA|R[EOSUW]|S[A-EG-ORT-VYZ]|T[CDF-HJ-PRTVWZ]|U[AGKMSYZ]|V[ACEGINU]|W[FS]|XN|Y[ETU]|Z[AMW]|AERO|ARPA|ASIA|BIZ|CAT|COM|COOP|EDU|GOV|INFO|INT|JOBS|MIL|MOBI|MUSEUM|NAME|NET|ORG|PRO|TEL|TRAVEL)$",
			"i")
};


(function($) {

	$.extend($.expr[":"], {
		regex : function(d, a, c) {
			var e = new RegExp(c[3], "g");
			var b = ("text" === d.type) ? d.value : d.innerHTML;
			return (b == "") ? true : (e.exec(b));
		}
	});

	$.fn.output = function(d) {
		if (typeof d == "undefined")
			return (this.is(":text")) ? this.val() : this.html();
		else
			return (this.is(":text")) ? this.val(d) : this.html(d);
	};

	formatter = {
		getRegex : function(settings) {
			var settings = $.extend($.fn.format.defaults, settings);
			var result = "";
			if (settings.type == "decimal") {
				var e = (settings.permiteNegativos) ? "-?" : "";
				if (settings.precision.parteDecimal > 0) {
										
					var decimalRegexp = settings.separadorDecimal;
					if (decimalRegexp == ",")
						decimalRegexp = "\\,";
					// result="^"+e+"\\d+$|^"+e+"\\d{0,"+settings.parteEntera+"}\\"+settings.separadorDecimal+"\\d{1,"+settings.precision.parteDecimal+"}$";
					result = "^" + e + "\\d{0,"
					+ settings.precision.parteEntera + "}\\,?$|^" + e
					+ "\\d{0," + settings.precision.parteEntera + "}\\"
					+ settings.separadorDecimal + "\\d{1,"
					+ settings.precision.parteDecimal + "}$";
				
				} else
					result = "^" + e + "\\d+$";
			} else if (settings.type == "phone-number") {
				result = "^\\d[\\d\\-]*\\d$";
			} else if (settings.type == "alfabetico") {
				result = "^[A-Za-z]+$";
			}
			return result;
		},
		
		isEmail : function(d) {
			var a = $(d).output();
			var c = false;
			var e = true;
			var e = new RegExp(
					"[\s\~\!\#\$\%\^\&\*\+\=\(\)\[\]\{\}\<\>\\\/\;\:\,\?\|]+");
			if (a.match(e) != null) {
				return c;
			}
			if (a
					.match(/((\.\.)|(\.\-)|(\.\@)|(\-\.)|(\-\-)|(\-\@)|(\@\.)|(\@\-)|(\@\@))+/) != null) {
				return c;
			}
			if (a.indexOf("\'") != -1) {
				return c;
			}
			if (a.indexOf("\"") != -1) {
				return c;
			}
			if (email.tldn && a.match(email.tldn) == null) {
				return c;
			}
			return e;
		},
		
		formatString : function(target, settings) {
			var settings = $.extend($.fn.format.defaults, settings);
			var oldText = $(target).output();
			var newText = oldText;
			if (settings.type == "decimal") {
				if (newText != "") {
					var g;
					var h = (settings.permiteNegativos) ? "\\-" : "";
					var i = "\\" + settings.separadorDecimal;
					g = new RegExp("[^\\d" + h + i + "]+", "g");
					newText = newText.replace(g, "");
					var h = (settings.permiteNegativos) ? "\\-?" : "";
					if (settings.precision.parteDecimal > 0) {
						// g=new
						// RegExp("^("+h+"\\d*"+i+"\\d{1,"+settings.precision.parteDecimal+"}).*");
						var regExp = "^(" + h + "\\d{0,"
								+ settings.precision.parteEntera + "}" + i
								+ "\\d{1," + settings.precision.parteDecimal
								+ "})*";
						g = new RegExp(regExp);
					} else
						g = new RegExp("^(" + h + "\\d+).*");
					newText = newText.replace(g, "$1");
				}
			} else if (settings.type == "phone-number") {
				newText = newText.replace(/[^\-\d]+/g, "").replace(/^\-+/, "")
						.replace(/\-+/, "-");
			} else if (settings.type == "alfabetico") {
				newText = newText.replace(/[^A-Za-z]+/g, "");
			}
			if (newText != oldText)
				$(target).output(newText);
		},
		
		formatNumber : function(target, settings) {
			var settings = $.extend($.fn.format.defaults, settings);
			var text = $(target).output();
			if (text == "")
				return;
			
			if (text.indexOf(","))
				text = text.replace(",", ".");
			if(text==0){
				$(target).output("");
				return;
			}
			if(text=="-"){
				$(target).output("");
				return;
			}
			if(text=="-."){
				$(target).output("");
				return;
			}
		
			var number = text;
			var decimals = settings.precision.parteDecimal;
			var dec_point = settings.separadorDecimal;
			var thousands_sep = settings.separadorMiles;

			var exponent = "";
			var numberstr = number.toString();
			var eindex = numberstr.indexOf("e");
			if (eindex > -1) {
				exponent = numberstr.substring(eindex);
				number = parseFloat(numberstr.substring(0, eindex));
			}

			var sign = number < 0 ? "-" : "";
			var l1;
			if(number.indexOf('.')==-1)
				l1=number.length;
			else 
				l1=number.indexOf('.');
			
//			var integer = number.toString().substring(0, l1);
			var integer="";
			if(sign=="")
				integer = number.toString().substring(0, l1);
			else
				integer = number.toString().substring(1, l1);

			var fractional = number.toString().substring(l1	);
			dec_point = dec_point != null ? dec_point : ".";
			dec_point = decimals ==0 ? "" : dec_point;
				
			
			fractional = ( fractional.length >= 0) ? (dec_point + fractional
					.substring(1))
					: "";
			if (decimals != null && decimals > 0) {
				for (var i = fractional.length - 1, z = decimals; i < z; ++i)
					fractional += "0";
			}

			thousands_sep = (thousands_sep != dec_point || fractional.length == 0) ? thousands_sep
					: null;
			var c1=0;
			for(var j=0;j<integer.length;j++){
				if(integer.charAt(j)=='0'){
					
				}else{
					c1=j;
					break;
				}
			}
			integer=integer.substring(c1);
			if (thousands_sep != null && thousands_sep != "") {
				for ( var i = integer.length - 3; i > 0; i -= 3)
					integer = integer.substring(0, i) + thousands_sep
							+ integer.substring(i);
			}

			if(integer=="")
				integer="0";
			
			var newText = sign + integer + fractional + exponent;
//			zk.Widget.$(target).setText(newText);
			//antes lo poniamos con el output y no funcionaba porque solo
			//se actualizaba la vista pero el widget zk interiormente guradaba
			//el valor antiguo
			$(target).output(newText);
		},
		
		unFormatNumber : function(target, settings) {
			var settings = $.extend($.fn.format.defaults, settings);
			var text = $(target).output();
			while (text.indexOf(".") != -1) {
				text = text.replace(".", "");
			}
//			zk.Widget.$(target).setText(text);
			//antes lo poniamos con el output y no funcionaba porque solo
			//se actualizaba la vista pero el widget zk interiormente guradaba
			//el valor antiguo
			$(target).output(text);
		}
	};

	$.fn.format = function(options) {
		
		

		var opts = $.extend({}, $.fn.format.defaults, options);
		var decimal = opts.separadorDecimal;
		// para cada componente que puede contener el objeto jQuery que invoca a
		// esta funci�n
		this
				.each(function() {
					var input = $(this);
					// asignamos a la asignaci�n del foco la invocaci�n a una
					// funci�n
					$(this).focus(function() {
						
						$(this).data("old-value",quitarPuntos($(this).val()));
						
						
						if (opts.type == "decimal") {
							formatter.unFormatNumber(this, opts);
						}
						
						if($.browser.msie){
							this.onpropertychange = function() {
								if (window.event.propertyName == "value") {
									filtrarCadenas();
								}
							};
						}
					});

					// asignamos la p�rdida de foco la invocaci�n a una funci�n
					$(this).blur(function() {
						
						if($.browser.msie){
							this.onpropertychange = function() {	};
							
						}
						if (opts.type == "decimal") {
							formatter.formatNumber(this, opts);
						}
					});
					
					function filtrarCadenas(event){
//						setTimeout(function() {
//						dentro de esta funci�n no funciona el jquery en ie7
						if (opts.type == "decimal") {
								var reg = formatter
								.getRegex(opts);
								var regExp = new RegExp(reg);
								
								if (regExp.test(input.val())){
	
								}else{
									if(!$.browser.msie){
										input.val(input.data("old-value"));
										formatter.unFormatNumber(input,opts);
									}else{
										// en ie el valor antiguo es el valor formateado sin foco
										//y lo necesitamos con foco
										var t1=input.data("old-value");
										while(t1.indexOf('.')!=-1){
											t1=t1.replace('.','');
										}
//										input.log(t1);
										input.val(t1);
									}
								}
							}else if(opts.type == "alfabetico") {
								var reg = "^[ a-zA-Z\-,.'\u00E7\u00C7\u00F1\u00D1]*$";
								
								var regExp = new RegExp(reg);
								
								if (!regExp.test(input.val())){
									input.val(input.data("old-value"));
								}
							}else if(opts.type == "cadnum") {
								var reg = "^[0123456789]*$";
								
								var regExp = new RegExp(reg);
							
								if (!regExp.test(input.val())){
									input.val(input.data("old-value"));
								}
							}else if(opts.type == "alfanumerico") {
								var reg = "^[ a-zA-Z\-,.'\u00E7\u00C7\u00F1\u00D10123456789&\:;/_]*$";
								
								var regExp = new RegExp(reg);
							
								if (!regExp.test(input.val())){
									input.val(input.data("old-value"));
								}
							}else if(opts.type == "tipoNif") {
								var reg = "^[ a-zA-Z0123456789]*$";
								
								var regExp = new RegExp(reg);
								
								if (!regExp.test(input.val())){
									input.val(input.data("old-value"));
								}
							}
							else if(opts.type == "tipoNGrupo") {
								var reg = "^[0123456789/]*$";
								
								var regExp = new RegExp(reg);
								
								if (!regExp.test(input.val())){
									input.val(input.data("old-value"));
								}
							}
						
						input.data("old-value",input.val());
//						}, 0);
					}//fin de filtrar cadenas
					
					if($.browser.msie){
						//no maneja el evento input
						//se sustituye por onpropertychange que se activa y desactiva
						//con el onFocus y onBlur
						
					}else{
						$(this).bind('input', filtrarCadenas);
					}
						
					// asignamos la pulsaci�n de un tecla la invocaci�n a una
					// funci�n
					$(this).keypress(
							
									function(d) {
										// caracteres permitidos
										var valiEmail = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-._@\u00D1\u00C7\u00B4";
										var valiAlfanum = "0123456789&\:;/_";
										var valiAlfa = " ',-.ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz\u00C7\u00E7\u00D1\u00F1";
										var valiLetrasNif = "ABCDEFGHIJKLMNPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
										var numNif = "0123456789";
										var barra = "/";
									
//										var selRango = zk(this).getSelectionRange();
//										log(selRango);
//										selStart = selRango[0] ;
//										selEnd = selRango[1] ;
										var selRango = $(this).selection();
										selStart = selRango.start;
										selEnd = selRango.end;

										var a=d.which;
									    /*IE and Chrome set ctrlKey to true and altKey to true when you press the ALTGR modifier.
									    Firefox sets ctrlKey to false and altKey to true when you press the ALTGR modifier, as only ALT has been pressed.
									    Chrome has the altGraphKey property, but it is always undefined.
									     */
										//con ie7 dejar pasar altgr permite introducir caracteres no permitidos
										if (!(d.ctrlKey && d.altKey ) &&(d.ctrlKey || d.altKey || d.metaKey || a<32)) {//Ignore
											
											return true;
										} 
										$(this).data("old-value",$(this).val());


										if (a < 48 || a > 57) {//si no es un n�mero
											
											if (opts.type == "decimal") {
												if (opts.permiteNegativos
														&& a == 45
														&& this.value.length == 0)
													return true;
												
												if (a == decimal.charCodeAt(0)) {//decimal es el separador de decimales
													if (opts.precision.parteDecimal > 0
															&& this.value.indexOf(decimal) == -1
															&& this.value.length > 0 
															&& input.val()!="-")// para no permitir -,
														return true;
													else
														return false;
												}
												
												if (a != 0 && a != 8 && a != 9 && a != 13
														&& a != 35 && a != 36
														&& a != 37 && a != 39) {
													return false;
												}
												return true;
												
											} else if (opts.type == "email") {
												if (a == 8 || a == 9 || a == 13
														|| (a > 34 && a < 38)
														|| a == 39 || a == 45
														|| a == 46
														|| (a > 64 && a < 91)
														|| (a > 96 && a < 123)
														|| a == 95) {
													return true;
												}
												if (a == 64
														&& this.value
																.indexOf("@") == -1)
													return true;
												return false;
											} else if (opts.type == "phone-number") {
												if (a == 45
														&& this.value.length == 0)
													return false;
												if (a == 8 || a == 9 || a == 13
														|| (a > 34 && a < 38)
														|| a == 39 || a == 45) {
													return true;
												}
												return false;
											} else if (opts.type == "cadnum") {
												
												 return false;

											} else if (opts.type == "alfabetico") {
											
												var x=valiAlfa.indexOf(String.fromCharCode(a));
												if(a==0 || a == 8 || a == 9 || a == 13
														|| x!=-1 )
													return true;
												else return false;

											} else if(opts.type == "alfanumerico"){
												var x=valiAlfa.indexOf(String.fromCharCode(a)) ;
												var y=valiAlfanum.indexOf(String.fromCharCode(a));
												if(a==0 ||a == 8 || a == 9 || a == 13
														|| x!=-1 || y!=-1)
													return true;
												else return false;
											} else if(opts.type == "tipoNif"){
												var x=valiLetrasNif.indexOf(String.fromCharCode(a)) ;
												
												if(a == 8 || a == 9 || a == 13
														|| x!=-1 )
													return true;
												else return false;
											} 
											else if(opts.type == "tipoNGrupo"){												
												var y=input.val().length;
												if (a == barra.charCodeAt(0)&& y==4){
													return true;
												}																								
												else 
													return false;
											}											
											else
												return false;
										} else {//es un numero
											if (opts.type == "alfabetico") {
												return false;
											} else if (opts.type == "decimal") {
												
												var reg = formatter
														.getRegex(opts);
												var regExp = new RegExp(reg);
												
												var val = input.val()
														.substring(0, selStart)
														+ String
																.fromCharCode(a)
														+ input.val()
																.substring(
																		selEnd,
																		input.val().length);
												
												if (regExp.exec(val)!=null)
													return true;
												else{
													return false;
												}
											}else if (opts.type == "cadnum") {
											
												var x="0123456789".indexOf(String.fromCharCode(a));
												if(a==0 || a == 8 || a == 9 || a == 13
														|| x!=-1 )
													return true;
												else return false;

											}  
											else if (opts.type == "tipoNGrupo") {
												
												var x="0123456789".indexOf(String.fromCharCode(a));
												var y=input.val().length;
												if(x!=-1 && (y>=5 || y<4))
													return true;
												else return false;

											}  
											
											
											else
												return true;
										}
									});
				});
	};

	$.fn.format.defaults = {
		type : "decimal",
		precision : {
			parteEntera : 3,
			parteDecimal : 2
		},
		separadorDecimal : ",",
		separadorMiles : ".",
		permiteNegativos : false,
		autofix : true
	};
 function quitarPuntos(cad){
	 while (cad.indexOf(".") != -1) {
		 cad = cad.replace(".", "");
		}
	 return cad;
 }
})(jQuery);
