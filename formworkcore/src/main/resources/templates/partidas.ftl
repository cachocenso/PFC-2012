		<div id="${ap.id}" class="apartado">
          <div class="caption">${ap.titulo}</div>
		  <table>
		  		<#list ap.contenido as comp>
		  			<#if comp.class.simpleName == "Etiqueta">
							<tr>  
								<td colspan="2" class="subtitulo">
									${comp.valor}
								</td>
							</tr>
					<#else>
						<tr>
							<td class="etiqueta">
								${comp.etiqueta}
							</td>
							<td class="partida">
								<#if comp.calculado>
									<input id="${comp.id}" class="numberAlign" type="text" maxlength="18" size="18" disabled dinero />
								<#else>
									<input id="${comp.id}" class="numberAlign" type="text" maxlength="18" size="18" dinero />
								</#if>
							</td>
						</tr>
		  			</#if>
		  		</#list>
		  </table>
          <div style="float: none;">&#160;</div>
		</div>
		<div style="float: none;">&#160;</div>	
