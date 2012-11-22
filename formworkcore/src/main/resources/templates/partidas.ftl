		<div id="${ap.id}" class="apartado">
          <div class="caption">${ap.titulo}</div>
		  <table>
		  		<#list ap.contenido as comp>
		  			<#if comp.class.simpleName == "Etiqueta">
							<tr>
								<td colspan="2">
									${comp.valor}
								</td>
							</tr>
					<#else>
						<tr>
							<td class="etiqueta">
								${comp.etiqueta}
							</td>
							<td class="partida">
								<input id="${comp.id}" type="text" maxlength="18" size="18"/>
							</td>
						</tr>
		  			</#if>
		  		</#list>
		  </table>
          <div style="float: none;">&#160;</div>
		</div>
		  		
		  		<#-- COMMENTARIO
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
						<input type="text" maxlength="5" size="5"/>
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
	-->