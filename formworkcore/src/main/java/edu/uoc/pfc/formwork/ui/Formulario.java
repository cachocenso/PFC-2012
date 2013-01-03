package edu.uoc.pfc.formwork.ui;

import java.util.ArrayList;
import java.util.List;

import edu.uoc.pfc.formwork.xml.TipoPresentacion;

/**
 * Componente que representa el formulario completo
 * de la aplicación. Es el objeto raíz del árbol de componntes
 * y contiene a todos los demás.
 * 
 * @author Alberto Díaz en 16/12/2012
 */
public class Formulario extends Componente {

	/**
	 * Aparatados del formulario.
	 */
	private List<Apartado> apartados = new ArrayList<Apartado>();
	/**
	 * Titulo del formulario.
	 */
	private String titulo;
	/**
	 * Desripción.
	 */
	private String descripcion;
	/**
	 * Nombre de la clase controladora de la aplicación
	 */
	private String nombreControlador;
	private TipoPresentacion presentacion;
	
	/* (non-Javadoc)
	 * @see edu.uoc.pfc.formwork.ui.Componente#render()
	 */
	@Override
	public String render() {
		return null;
	}

	/**
	 * Añadir un objeto Apartado al formulario.
	 * @param apartado
	 */
	public void addApartado(Apartado apartado) {
		if (!apartados.contains(apartado)) {
			apartados.add(apartado);
		}
	}

	/**
	 * Devuelve una partida determinada por el id recibido
	 * como parámtro.
	 * 
	 * @param id Partida que se busca
	 * @return La partida solicitada o null si la partida no existe
	 */
	public Partida<?> getPartida(String id) {
		
		for (Apartado ap : getApartados()) {
			
			for (Componente com : ap.getContenido()) {
				if (com instanceof Partida<?> && com.getId().equals(id)) {
					return (Partida<?>) com;
				}
			}
		}
		
		return null;
	}
	
	/*
	 * Getters y Setters.
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

	/**
	 * Establece el tipo de presentación del formulario
	 * @param presentacion
	 */
	public void setPresentacion(TipoPresentacion presentacion) {
		this.presentacion = presentacion;	
	}

	public TipoPresentacion getPresentacion() {
		return presentacion;
	}
	
}
