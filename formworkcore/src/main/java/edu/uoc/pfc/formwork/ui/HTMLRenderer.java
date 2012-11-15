package edu.uoc.pfc.formwork.ui;

public class HTMLRenderer implements IRenderer {

	/* (non-Javadoc)
	 * @see edu.uoc.pfc.formwork.ui.IRenderer#render(edu.uoc.pfc.formwork.ui.Componente)
	 */
	public void render(Componente root) {
		
		if (!(root instanceof Formulario)) {
			throw new IllegalArgumentException("Se esperaba un objeto Formulario");
		}
	}

}
