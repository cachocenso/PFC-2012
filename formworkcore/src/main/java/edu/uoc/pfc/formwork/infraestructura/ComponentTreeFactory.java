package edu.uoc.pfc.formwork.infraestructura;

import java.util.Arrays;
import java.util.List;

import javax.xml.bind.JAXBException;

import edu.uoc.pfc.formwork.ui.Apartado;
import edu.uoc.pfc.formwork.ui.Etiqueta;
import edu.uoc.pfc.formwork.ui.Formulario;
import edu.uoc.pfc.formwork.ui.PartidaBoolean;
import edu.uoc.pfc.formwork.ui.PartidaCadena;
import edu.uoc.pfc.formwork.ui.PartidaCantidad;
import edu.uoc.pfc.formwork.ui.PartidaPeriodo;
import edu.uoc.pfc.formwork.ui.TipoApartado;
import edu.uoc.pfc.formwork.xml.TipoCalculado;
import edu.uoc.pfc.formwork.xml.TipoComponente;
import edu.uoc.pfc.formwork.xml.TipoEtiqueta;
import edu.uoc.pfc.formwork.xml.TipoFormulario;
import edu.uoc.pfc.formwork.xml.TipoPartida;
import edu.uoc.pfc.formwork.xml.TipoTipoPartida;

/**
 * Factoría de objetos Component. Se emplea para generar el 
 * árbol de componentes de servidor a partir de los objetos
 * JAXB extraídos en el procesado de la página fwp.
 * 
 * @author Alberto Díaz en 11/11/2012
 */
public class ComponentTreeFactory {

	private ComponentTreeFactory() {

	}

	/**
	 * Contruye un objeto @see Formulario a partir del objeto TipoFormulario obtenido del 
	 * proceso de la página fwp.
	 * @param jaxbFormulario
	 * @return el objeto Formulario
	 * @throws JAXBException
	 */
	public static Formulario createComponentsTree(TipoFormulario jaxbFormulario) throws JAXBException {
		Formulario theForm = new Formulario();

		theForm.setId(jaxbFormulario.getId());
		theForm.setTitulo(jaxbFormulario.getTitulo());
		theForm.setDescripcion(jaxbFormulario.getDescripcion());
		theForm.setNombreControlador(jaxbFormulario.getControlador());
		
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
	 * Crear las partidas de un apartado.
	 * 
	 * @param ap
	 * @param apartado
	 * @throws JAXBException 
	 */
	private static void createPartidas(Apartado apartado, 
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
		case complementaria:
			createPartidasComplementarias(apartado, jaxbApartado);
			break;
		case pago:
			createPartidasPago(apartado, jaxbApartado);
			break;
		default:
			break;
		}
	}

	/**
	 * Crear las partidas de un apartado de tipo @see pago
	 * 
	 * @param apartado
	 * @param jaxbApartado
	 */
	private static void createPartidasPago(Apartado apartado,
			edu.uoc.pfc.formwork.xml.TipoApartado jaxbApartado) {
		if (apartado.getTipo() != TipoApartado.pago) {
			throw new IllegalArgumentException(apartado.getTipo().name());
		}
		
		apartado.setId(jaxbApartado.getId());
		apartado.setTitulo(jaxbApartado.getTitulo());
		
		PartidaCantidad partidaCantidad = new PartidaCantidad();
		partidaCantidad.setId("imp");
		partidaCantidad.setEtiqueta("Importe.");
		apartado.addComponente(partidaCantidad);
		
		PartidaCadena nrc = new PartidaCadena();
		nrc.setId("nrc");
		nrc.setEtiqueta("Número de referencia completo (NRC).");
		apartado.addComponente(nrc);
		
		
	}

	/**
	 * Crear partidas de un apartado de tipo complementaria.
	 * 
	 * @param apartado
	 * @param jaxbApartado
	 */
	private static void createPartidasComplementarias(Apartado apartado,
			edu.uoc.pfc.formwork.xml.TipoApartado jaxbApartado) {
		
		if (apartado.getTipo() != TipoApartado.complementaria) {
			throw new IllegalArgumentException(apartado.getTipo().name());
		}
		
		apartado.setId(jaxbApartado.getId());
		apartado.setTitulo(jaxbApartado.getTitulo());
		
		PartidaBoolean partidaBoolean = new PartidaBoolean();
		partidaBoolean.setId("comple");
		partidaBoolean.setEtiqueta("Si esta autoliquidación es complementaria de otra " +
									"autoliquidación anterior señálelo marcando esta casilla.");
		apartado.addComponente(partidaBoolean);
		
		PartidaCadena justificante = new PartidaCadena();
		justificante.setId("njust");
		justificante.setEtiqueta("Indique a continuación el número de justificante " +
								"de la declaración anterior");
		apartado.addComponente(justificante);
		
	}

	/**
	 * Crear partidas de un apartado de tipo partidas.
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
				cantidad.setCalculado(tipoPartida.getCalculado() == TipoCalculado.SI);
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
				ej.setEtiqueta("Ejercicio");
				apartado.addComponente(ej);
			}
			else if ("fecha".equals(c.trim())) {
				PartidaCadena fech = new PartidaCadena();
				fech.setId(c.trim());
				fech.setEtiqueta("Fecha");
				apartado.addComponente(fech);
			}
			else if (c.trim().matches("periodo(.+)")) {
				String periodo = c.substring(c.indexOf('(') + 1, c.indexOf(')'));
				
				PartidaPeriodo per = new PartidaPeriodo();
				per.setId("periodo");
				per.setEtiqueta("Periodo");
				per.setPeriodos(Arrays.asList(periodo.split(" ")));
				apartado.addComponente(per);
			}
			else {
				throw new JAXBException("Contenido incorrecto: " + c);
			}
		}
	}

	/**
	 * Crear las partidas de un apartado de tipo @see identificacion
	 * 
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
		partida.setId("nif");
		partida.setEtiqueta("NIF");
		apartado.addComponente(partida);
		
		if (nifRep) {
			partida = new PartidaCadena();
			partida.setEtiqueta("NIF Representante");
			partida.setId("nifr");
			apartado.addComponente(partida);
			
		}
		
		if (nombre) {
			partida = new PartidaCadena();
			partida.setId("nomrasoc");
			partida.setEtiqueta("Apellidos y nombre o razón social");
			apartado.addComponente(partida);
		}
	}

}
