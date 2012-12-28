/**
 * 
 */
package edu.uoc.pfc.formwork.ui;

/**
 * @author Alberto Díaz en 29/11/2012
 */
public class PartidaBoolean extends Partida<Boolean> {

	/* (non-Javadoc)
	 * @see edu.uoc.pfc.formwork.ui.Componente#render(edu.uoc.pfc.formwork.ui.IRenderer)
	 */
	@Override
	public String render() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getFormattedValue() {
		return getValue().toString();
	}

	@Override
	protected void formatValue() {
		setFormattedValue(getValue().toString());
	}

	
}
