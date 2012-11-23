package edu.uoc.pfc.formwork.infraestructura;

import java.util.Arrays;
import java.util.List;

import javax.xml.bind.JAXBException;

import edu.uoc.pfc.formwork.ui.Apartado;
import edu.uoc.pfc.formwork.ui.Etiqueta;
import edu.uoc.pfc.formwork.ui.Formulario;
import edu.uoc.pfc.formwork.ui.PartidaCadena;
import edu.uoc.pfc.formwork.ui.PartidaCantidad;
import edu.uoc.pfc.formwork.ui.PartidaPeriodo;
import edu.uoc.pfc.formwork.ui.TipoApartado;
import edu.uoc.pfc.formwork.xml.TipoComponente;
import edu.uoc.pfc.formwork.xml.TipoEtiqueta;
import edu.uoc.pfc.formwork.xml.TipoFormulario;
import edu.uoc.pfc.formwork.xml.TipoPartida;
import edu.uoc.pfc.formwork.xml.TipoTipoPartida;

/**
 * Factoría de objetos Component.
 * 
 * @author Alberto Díaz en 11/11/2012
 */
public class ComponentTreeFactory {

	private ComponentTreeFactory() {

	}

	public static Formulario createComponentsTree(TipoFormulario jaxbFormulario) throws JAXBException {
		Formulario theForm = new Formulario();

		theForm.setId(jaxbFormulario.getId());
		theForm.setTitulo(jaxbFormulario.getTitulo());
		theForm.setDescripcion(jaxbFormulario.getDescripcion());
		for (edu.uoc.pfc.formwork.xml.TipoApartado jaxbApartado : jaxbFormulario
				.getApartado()) {
			Apartado apartado = new Apartado();
			apartado.setId(jaxbApartado.getId());
			edu.uoc.pfc.formwork.ui.TipoApartado tp = edu.uoc.pfc.formwork.ui.TipoApartado
					.fromTipoTipoApartado(jaxbApartado.getTipo());
			apartado.setTipo(tp);
			createPartidas(apartado, jaxbApartado);

			theForm.addApartado(apartado);
		}

		return theForm;
	}

	/**
	 * @param ap
	 * @param apartado
	 * @throws JAXBException 
	 */
	public static void createPartidas(Apartado apartado, 
			edu.uoc.pfc.formwork.xml.TipoApartado jaxbApartado) throws JAXBException {
		
		switch (apartado.getTipo()) {
		case identificacion:
			createPartidasIdentificacion(apartado, jaxbApartado);
			break;
		case devengo:
			createPartidasDevengo(apartado, jaxbApartado);
			break;
		case partidas:
			createPartidasPartidas(apartado, jaxbApartado);
			break;
		default:
			break;
		}
	}

	/**
	 * @param apartado
	 * @param jaxbApartado
	 */
	private static void createPartidasPartidas(Apartado apartado,
			edu.uoc.pfc.formwork.xml.TipoApartado jaxbApartado) {
		if (apartado.getTipo() != TipoApartado.partidas) {
			throw new IllegalArgumentException(apartado.getTipo().name());
		}
		
		apartado.setId(jaxbApartado.getId());
		
		// El título del apartado solo se tiene en cuenta en los apartados de tipo
		// partidas
		apartado.setTitulo(jaxbApartado.getTitulo());
		
		List<TipoComponente> jaxbPartidas = jaxbApartado.getEtiquetaOrPartida();
		
		for (TipoComponente tipoComponente : jaxbPartidas) {
			
			if (tipoComponente instanceof TipoEtiqueta) {
				TipoEtiqueta tipoEtiqueta = (TipoEtiqueta) tipoComponente;
				 Etiqueta etiqueta = new Etiqueta();
				 etiqueta.setId(tipoEtiqueta.getId());
				 etiqueta.setValor(tipoEtiqueta.getValor());
				 apartado.addComponente(etiqueta);
			}
			else {
				PartidaCantidad cantidad = new PartidaCantidad();
				
				TipoPartida tipoPartida = (TipoPartida)tipoComponente;
				cantidad.setId(tipoPartida.getId());
				cantidad.setEtiqueta(tipoPartida.getEtiqueta());
				cantidad.setAdmiteNegativos(tipoPartida.getTipo() == TipoTipoPartida.CANTIDAD_NEGATIVA);
				apartado.addComponente(cantidad);
			}
		}
	}

	/**
	 * Creación de las partidas del apartado de devengo.
	 * 
	 * @param apartado
	 * @param jaxbApartado
	 * @throws JAXBException 
	 */
	private static void createPartidasDevengo(Apartado apartado,
			edu.uoc.pfc.formwork.xml.TipoApartado jaxbApartado) throws JAXBException {
		
		if (apartado.getTipo() != TipoApartado.devengo) {
			throw new IllegalArgumentException(apartado.getTipo().name());
		}
		
		String contenido = jaxbApartado.getContenido();
		
		if (contenido == null) {
			throw new IllegalArgumentException("Falta contenido: " + jaxbApartado.getTipo());
		}
		
		List<String> contenidos = Arrays.asList(contenido.split(","));
		
		for (String c : contenidos) {
			
			if ("ejercicio".equals(c.trim())) {
				PartidaCadena ej = new PartidaCadena();
				ej.setId(c);
				
				apartado.addComponente(ej);
			}
			else if ("fecha".equals(c.trim())) {
				PartidaCadena fech = new PartidaCadena();
				fech.setId(c);
				apartado.addComponente(fech);
			}
			else if (c.trim().matches("periodo(.+)")) {
				String periodo = c.substring(c.indexOf('(') + 1, c.indexOf(')'));
				
				PartidaPeriodo per = new PartidaPeriodo();
				per.setId("periodo");
				per.setPeriodos(Arrays.asList(periodo.split(" ")));
				apartado.addComponente(per);
			}
			else {
				throw new JAXBException("Contenido incorrecto: " + c);
			}
		}
	}

	/**
	 * @param apartado
	 * @param jaxbApartado 
	 * @throws JAXBException 
	 */
	private static void createPartidasIdentificacion(Apartado apartado, edu.uoc.pfc.formwork.xml.TipoApartado jaxbApartado) throws JAXBException {
		if (apartado.getTipo() != TipoApartado.identificacion) {
			throw new IllegalArgumentException(apartado.getTipo().name());
		}
		
		boolean nifRep = false, nombre = false;
		
		
		// análisis de contenido
		String contenido = jaxbApartado.getContenido();
		
		if (contenido != null) {
			List<String> contenidoSplit = Arrays.asList(contenido.split(","));
			
			for (String c: contenidoSplit) {
				if (Apartado.CONTENIDOS_VALIDOS_IDENTIFICACION.contains(c)) {
					if ("representante".equals(c)) {
						nifRep = true;
					}
					else if ("nombre".equals(c)) {
						nombre = true;
					}
				}
				else {
					throw new JAXBException("contenido ilegal: " + c);
				}
			}
		}
		else {
			// Todos los contenidos.
			nifRep = true; 
			nombre = true;
		}
		
		
		// El NIF siempre va sea cual sea el valor de contenido.
		PartidaCadena partida = new PartidaCadena();
		partida.setEtiqueta("NIF");
		apartado.addComponente(partida);
		
		if (nifRep) {
			partida = new PartidaCadena();
			partida.setEtiqueta("NIF Representante");
			apartado.addComponente(partida);
			
		}
		
		if (nombre) {
			partida = new PartidaCadena();
			partida.setEtiqueta("Apellidos y nombre o razón social");
			apartado.addComponente(partida);
		}
	}

}
