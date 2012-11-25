<!DOCTYPE html>
<head>
  <title>${form.titulo}</title>
  <link rel="stylesheet" type="text/css" href="/au/~/css/formwork.css"/>
  <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.0/jquery.min.js"></script>
  <script src="/au/~/js/formwork.js">
  </script>
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
 
 