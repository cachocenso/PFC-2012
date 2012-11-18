<!DOCTYPE html>
<head>
  <title>Modelo 250</title>
 <!-- <link rel="stylesheet" type="text/css" href="formwork.css"/>  -->
 <style>
 .contenido {
	font-family: Arial, Helvetica, sans-serif;
	width: 80%;
	margin: 0 auto;
	font-size: 1em;
}

.titulo {
	color: #0066CC;
	font-size: 14px;	
	font-weight: bold;
	margin-bottom: 5px
}

.apartado {
  border: 1px solid black;
}

.caption {
  background: #2ab0ed none repeat scroll 0% 50%;
  border: 1px solid;
  color: #0000ae;
  font-variant: small-caps;
}

table  {
		width: 100%;
		border: 0;
		padding: 0;
}

td.etiqueta {
	width: 60%;
}

td.partida {
	width: 40%
}
 </style>
 </head>
 
    <body>
      <div class="contenido">
		<div class="titulo">
			Gravamen Especial sobre Dividendos y Rentas de Fuente Extranjera Derivados de la Transmisión de los Valores representativos 
			de los Fondos Propios de Entidades no Residentes en Territorio Español. Modelo 250
		</div>
        <div id="identificacion" class="apartado">
          <div class="caption">Identificaci&#243;n</div>
            <div style="float: left; width: 25%;">
              <div>NIF</div>
              <div>
                <input id="nif" type="text" maxlength="9" size="9"
                value="00000101D" />
              </div>
            </div>
            <div style="float: left; width: 25%">
              <div>NIF Representante</div>
              <div>
                <input id="nif" type="text" maxlength="9" size="9"
                value="00000102X" />
              </div>
            </div>
            <div style="float: left; width: 50%">
              <div>Apellidos y nombre o raz&#243;n social</div>
              <div>
                <input id="nif" type="text" maxlength="40"
                size="40" value="PACO EL PULPO" />
              </div>
            </div>
            <div style="float: none;">&#160;</div>
        </div>
        <div style="float: none;">&#160;</div>
		<div id="devengo" class="apartado">
          <div class="caption">Devengo</div>
            <div style="float: left; width: 33%;">
              <div>Ejercicio</div>
              <div>
                <input id="ejercicio" type="text" maxlength="4" size="4"
                value="2012" />
              </div>
            </div>
            <div style="float: left; width: 33%">
              <div>Periodo</div>
              <div>
                <input id="periodo" type="text" maxlength="2" size="2"
                value="0A" />
              </div>
            </div>
            <div style="float: left; width: 34%">
              <div>Fecha devengo</div>
              <div>
                <input id="fecha" type="text" maxlength="10"
                size="10" value="10/10/2012" />
              </div>
            </div>
            <div style="float: none;">&#160;</div>
        </div>
        <div style="float: none;">&#160;</div>
		<div id="autoliquidacion" class="apartado">
          <div class="caption">Autoliquidaci&#243;n</div>
		  <table>
				<tr>
					<td colspan="2">
						A) Dividendos o participaciones en beneficios de entidades no residentes en territorio español.
					</td>
				</tr>
				<tr>
					<td class="etiqueta">
						Base imponible (importe &iacute;ntegro).
					</td>
					<td class="partida">
						<input type="text" maxlength="18" size="18"/>
					</td>
				</tr>
				<tr>
					<td class="etiqueta">
						Tipo gravamen (8% o 10%)
					</td>
					<td class="partida">
						<input type="text" maxlength="5" size="5S"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						B) Transmisión de valores o participaciones de entidades no residentes en territorio español.
					</td>
				</tr>
				<tr>
					<td class="etiqueta">
						B.1) Renta obtenida en la transmisión (con exclusión de la cuantía que deba declararse en la casilla [05]).
					</td>
					<td class="partida">
						<input type="text" maxlength="18" size="18"/>
					</td>
				</tr>
				<tr>
					<td class="etiqueta">
						Tipo gravamen (8% o 10%)
					</td>
					<td class="partida">
						<input type="text" maxlength="5" size="5"/>
					</td>
				</tr>
				<tr>
					<td class="etiqueta">
						B.2) Reversión de cualquier corrección de valor sobre la participación transmitida que hubiera sido fiscalmente deducible durante el tiempo de tenencia de la participación.
					</td>
					<td class="partida">
						<input type="text" maxlength="18" size="18"/>
					</td>
				</tr>
				<tr>
					<td class="etiqueta">
						Tipo gravamen (8% o 10%)
					</td>
					<td class="partida">
						<input type="text" maxlength="5" size="5"/>
					</td>
				</tr>
				<tr>
					<td class="etiqueta">
						CUOTA A INGRESAR ( [01] X [02] + [03] X [04] + [05] X [06]) / 100.
					</td>
					<td class="partida">
						<input type="text" maxlength="18" size="18"/>
					</td>
				</tr>
		  </table>
          <div style="float: none;">&#160;</div>
        </div>
		<p/>
		<div id="complementaria" class="apartado">
            <div class="caption">Declaraci&#243;n complementaria.</div>
			<table>
				<tr>
					<td class="etiqueta">
						Si esta autoliquidación es complementaria de otra autoliquidación anterior correspondiente al mismo concepto, ejercicio y fecha de devengo señálelo marcando esta casilla.
					</td>
					<td class="partida">
						<input type="checkbox"/>
					</td>
				</tr>
				<tr>
					<td class="etiqueta">
						En este caso, indique a continuación el número de justificante de la declaración anterior
					</td>
					<td class="partida">
						<input type="text" maxlength="13" size="13"/>
					</td>
				</tr>
			</table>
		</div>
		<p/>
		<div id="ingreso" class="apartado">
            <div class="caption">Ingreso</div>
			<table>
				<tr>
					<td class="etiqueta">
						Importe (casilla [09]).
					</td>
					<td class="partida">
						<input type="text" maxlength="18" size="18"/>
					</td>
				</tr>
				<tr>
					<td class="etiqueta">
						Número de referencia completo (NRC).
					</td>
					<td class="partida">
						<input type="text" maxlength="13" size="13"/>
					</td>
				</tr>
			</table>
		</div>
		<p/>
		<div id="envio" class="apartado">
			<div style ="margin: 5px">
				<center><button>Firmar y enviar</button></center>
			</div>
		</div>
      </div>
    </body>
 