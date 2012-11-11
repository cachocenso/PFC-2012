package edu.uoc.pfc.formwork.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Apartado extends Componente {
	public static List<String> CONTENIDOS_VALIDOS_IDENTIFICACION = Arrays.asList(new String[] {"nif", "representante", "nombre"});
	public static List<String> CONTENIDOS_VALIDOS_DEVENGO = Arrays.asList(new String [] {"ejercicio", "fecha", "periodo{.+}"});
	public static List<String> CONTENIDOS_VALIDOS_PERIODO = Arrays.asList(new String [] {
												"0A", 
												"1P", "2P","3P", 
												"1T", "2T", "3T", "4T", 
												"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"});
	
	private TipoApartado tipo;
	private List<Partida<?>> contenido = new ArrayList<Partida<?>>();
	
	public List<Partida<?>> getContenido() {
		return contenido;
	}
	
	public void setContenido(List<Partida<?>> contenido) {
		this.contenido = contenido;
	}
	
	public TipoApartado getTipo() {
		return tipo;
	}
	
	public void setTipo(TipoApartado tp) {
		this.tipo = tp;
	}

	
	public void addPartida(Partida<?> partida) {
		if (!contenido.contains(partida)) {
			contenido.add(partida);
		}
	}
	
	@Override
	public void render(IRenderer renderer) {
		// TODO Auto-generated method stub
		
	}
	
}
