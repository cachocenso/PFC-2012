<#assign s = ap.contenido?size>
<div id="devengo" class="apartado">
	<div class="caption">Devengo</div>
	<#list ap.contenido as comp >
			<#if comp.id == "ejercicio">
			    <div style="float: left; width: 33%;">
				      <div style="margin-left: 2px">${comp.etiqueta}</div>
				      <div style="margin-left: 2px">
				        <input id="${comp.id}" type="text" maxlength="4" size="4"/>
				      </div>
			    </div>
			<#elseif comp.id == "periodo">
 			      <div style="float: left; width: 33%">
				      <div style="margin-left: 2px">${comp.etiqueta}</div>
				      <div style="margin-left: 2px">
				        <select id="${comp.id}">
				        	<#list comp.periodos as per>
				        		<option value="${per}">${per}</option>
				        	</#list>
				        </select>
				      </div>
			    </div>
			<#else>
			    <div style="float: left; width: 33%">
				      <div style="margin-left: 2px">${comp.etiqueta}</div>
				      <div style="margin-left: 2px">
				        <input id="${comp.id}" type="text" maxlength="10" size="10"/>
				      </div>
			    </div>
	    	</#if>
	</#list>	
    <div style="float: none;">&#160;</div>
    <div style="float: none;">&#160;</div>
    <div style="float: none;">&#160;</div>
</div>
<div style="float: none;">&#160;</div>

  