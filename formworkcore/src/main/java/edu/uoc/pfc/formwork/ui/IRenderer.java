package edu.uoc.pfc.formwork.ui;

public interface IRenderer {
	/**
	 * Dibuja el contenido del árbol de componentes al lenguaje
	 * especificado en la clase que implementa la interfaz
	 * @param root
	 */
	void render(Componente root);
}
