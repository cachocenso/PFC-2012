<#assign pr = form.presentacion>
<!DOCTYPE html>
<head>
  <meta http-equiv="content-type" content="text/html; charset=UTF-8">
  <meta http-equiv="cache-control" content="no-cache">  
  <meta http-equiv="pragma" content="no-cache">
  <meta http-equiv="expires" content="-1">
    
  <title>${form.titulo}</title>
  <link rel="stylesheet" type="text/css" href="au/~/css/formwork.css"/>
  <link rel="stylesheet" type="text/css" 
  		href="http://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css"/>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.js"></script>
  <script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
  <script src="au/~/js/formateador.1.00.src.js"></script>
  <script src="au/~/js/formwork.js"></script>
  
 </head>
 
<body>
  <div class="contenido">
	<div class="titulo">${form.descripcion}</div>
	<#list form.apartados as ap>
		${ap.render()}
	</#list>
    <#if pr??>
	<p/>
	<div id="envio" class="apartado">
		<div style ="margin: 5px">
			<#if pr.signed>
			<center><button id="firmarEnviar">Firmar y enviar</button></center>
			<#else>
			<center><button id="enviar">Enviar</button></center>
			</#if>
		</div>
	</div>
  </div>
  </#if>
</body>
 
 