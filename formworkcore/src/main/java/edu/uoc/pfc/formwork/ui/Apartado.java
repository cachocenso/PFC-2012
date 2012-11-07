package edu.uoc.pfc.formwork.ui;

import java.util.List;

public class Apartado extends Componente {
	private String tipo;
	private List<String> contenido;
	
	public List<String> getContenido() {
		return contenido;
	}
	
	public void setContenido(List<String> contenido) {
		this.contenido = contenido;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public void render(IRenderer renderer) {
		// TODO Auto-generated method stub
		
	}
}
