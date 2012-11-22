package edu.uoc.pfc.formwork.ui;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import edu.uoc.pfc.formwork.infraestructura.FormworkContext;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class Apartado extends Componente {
	public static List<String> CONTENIDOS_VALIDOS_IDENTIFICACION = Arrays
			.asList(new String[] { "nif", "representante", "nombre" });
	public static List<String> CONTENIDOS_VALIDOS_DEVENGO = Arrays
			.asList(new String[] { "ejercicio", "fecha", "periodo{.+}" });
	public static List<String> CONTENIDOS_VALIDOS_PERIODO = Arrays
			.asList(new String[] { "0A", "1P", "2P", "3P", "1T", "2T", "3T",
					"4T", "01", "02", "03", "04", "05", "06", "07", "08", "09",
					"10", "11", "12" });

	private Logger logger = Logger.getLogger(Apartado.class);

	private TipoApartado tipo;
	private List<Componente> contenido = new ArrayList<Componente>();

	public List<Componente> getContenido() {
		return contenido;
	}

	public void setContenido(List<Componente> contenido) {
		this.contenido = contenido;
	}

	public TipoApartado getTipo() {
		return tipo;
	}

	public void setTipo(TipoApartado tp) {
		this.tipo = tp;
	}

	public void addComponente(Componente componente) {
		if (!contenido.contains(componente)) {
			contenido.add(componente);
		}
	}

	@Override
	public String render(IRenderer renderer) {
		StringWriter stringWriter = new StringWriter();

		Configuration configuration = FormworkContext.getTemplateConfig();

		try {
			Template template = null;
			HashMap<Object, Object> dataModel = new HashMap<Object, Object>();
			
			switch (tipo) {
			case identificacion:
				template = configuration.getTemplate("templates/identificacion.ftl");
				break;
			case devengo:
				template = configuration.getTemplate("templates/devengo.ftl");
				break;
			case partidas:
				break;
			default:
				return "Soy el apartado " + getId();
			}
			
			if (template != null) {
				template.process(dataModel, stringWriter);
			}
		} catch (IOException e) {
			logger.error("Plantilla no encontrada.", e);
		} catch (TemplateException e) {
			logger.error("Error al aplicar plantilla.", e);
		}

		return stringWriter.toString();
	}

}
