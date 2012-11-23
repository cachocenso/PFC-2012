package edu.uoc.pfc.formwork.ui;

import java.util.ArrayList;
import java.util.List;

public class Formulario extends Componente {

	private List<Apartado> apartados = new ArrayList<Apartado>();
	private String titulo;
	private String descripcion;
	private String nombreControlador;
	
	@Override
	public String render(IRenderer renderer) {
		return "Soy el formulario";
	}

	public void addApartado(Apartado apartado) {
		if (!apartados.contains(apartado)) {
			apartados.add(apartado);
		}
	}

	/**
	 * @param titulo
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
		
	}

	public List<Apartado> getApartados() {
		return apartados;
	}

	public void setApartados(List<Apartado> apartados) {
		this.apartados = apartados;
	}

	public String getTitulo() {
		return titulo;
	}

	/**
	 * @param descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
		
	}

	public String getDescripcion() {
		return descripcion;
	}

	public String getNombreControlador() {
		return nombreControlador;
	}

	public void setNombreControlador(String nombreControlador) {
		this.nombreControlador = nombreControlador;
	}
	
}
