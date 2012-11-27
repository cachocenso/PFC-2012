<#assign s = ap.contenido?size>
<div id="identificacion" class="apartado">
          <div class="caption">Identificaci&#243;n</div>
            <#list ap.contenido as comp>
            	<#if comp.id == "nif" || comp.id == "nifr"> 
			            <div style="float: left; width: 33%">
			              <div style="margin-left: 2px">${comp.etiqueta}</div>
			              <div style="margin-left: 2px">
			                <input id="${comp.id}" type="text" maxlength="9" size="9"/>
			              </div>
			            </div>
			     <#else>
			            <div style="float: left; width: 34%;">
			              <div style="margin-left: 2px">Apellidos y nombre o raz&#243;n social</div>
			              <div style="margin-left: 2px">
			                <input id="nomrasoc" type="text" maxlength="40" size="40"/>
			              </div>
			            </div>
            	</#if>
            </#list>
            <div style="float: none;">&#160;</div>
           	<#if s < 3> 
	            <div style="float: none;">&#160;</div>
	            <div style="float: none;">&#160;</div>
            </#if>
        </div>
		<div style="float: none;">&#160;</div>	