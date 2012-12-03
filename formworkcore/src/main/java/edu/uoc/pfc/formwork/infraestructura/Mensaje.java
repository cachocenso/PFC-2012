package edu.uoc.pfc.formwork.infraestructura;


/**
 * Clase que representa los errores producidos en la validación de una 
 * partida.
 * 
 * @author cachocenso
 * 03/12/2012
 */
public class Mensaje {
	/**
	 * Tipo de mensaje: Error o aviso.
	 * @author cachocenso
	 * 03/12/2012
	 */
	public enum TipoMensaje {
		ERROR, AVISO;
	}
	
	private TipoMensaje tipoMensaje;
	private String mensaje;
	private String idPartida;
	
	public Mensaje() {
		
	}
	
	public Mensaje(TipoMensaje tipoMensaje, String mensaje, String idPartida) {
		this.tipoMensaje = tipoMensaje;
		this.mensaje = mensaje;
		this.idPartida = idPartida;
	}

	public TipoMensaje getTipoMensaje() {
		return tipoMensaje;
	}
	public void setTipoMensaje(TipoMensaje tipoMensaje) {
		this.tipoMensaje = tipoMensaje;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getIdPartida() {
		return idPartida;
	}

	public void setIdPartida(String idPartida) {
		this.idPartida = idPartida;
	}

}
