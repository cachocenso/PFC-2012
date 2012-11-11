package edu.uoc.pfc.formwork.ui;

import java.util.ArrayList;
import java.util.List;

public class Formulario extends Componente {

	private List<Apartado> apartados = new ArrayList<Apartado>();
	
	@Override
	public void render(IRenderer renderer) {
		// TODO Auto-generated method stub
		
	}

	public void addApartado(Apartado apartado) {
		if (!apartados.contains(apartado)) {
			apartados.add(apartado);
		}
	}
	
}
