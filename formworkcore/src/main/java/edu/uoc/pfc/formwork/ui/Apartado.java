package edu.uoc.pfc.formwork.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Apartado extends Componente {
	public static List<String> CONTENIDOS_VALIDOS = Arrays.asList(new String[] {"nif", "representante", "nombre"});
	
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
