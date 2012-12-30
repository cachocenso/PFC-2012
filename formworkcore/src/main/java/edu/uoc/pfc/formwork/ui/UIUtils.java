package edu.uoc.pfc.formwork.ui;

import java.math.BigDecimal;

public class UIUtils {
	private UIUtils() {}
	

	@SuppressWarnings({"rawtypes", "unchecked"})
	public static void setValue(Partida partida, Object valor) throws Exception {
		Object typedValue;
		
		if (partida instanceof PartidaCantidad) {
			String v = ((String)valor).replace(',', '.');
			typedValue = new BigDecimal(v);
		}
		else if (partida instanceof PartidaCadena || partida instanceof PartidaLista) {
			typedValue = valor.toString();
		}
		else if (partida instanceof PartidaBoolean) {
			typedValue = Boolean.valueOf((String) valor);
		}
		else {
			throw new IllegalArgumentException("Valor:" + valor);
		}
		
		partida.setValue(typedValue);
	}
}
