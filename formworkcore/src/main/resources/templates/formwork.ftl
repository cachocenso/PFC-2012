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
		<div id="envio" class="apartado">
			<div style ="margin: 5px">
				<center><button>Firmar y enviar</button></center>
			</div>
		</div>
      </div>
    </body>
 
 