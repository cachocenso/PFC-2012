<!DOCTYPE html>
<head>
  <title>${form.titulo}</title>
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
		<div class="titulo">${form.descripcion}</div>
		<#list form.apartados as ap>
			${ap.render(null)}
		</#list>
        
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
 
 