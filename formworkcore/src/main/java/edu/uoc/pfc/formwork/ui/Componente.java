package edu.uoc.pfc.formwork.ui;

public abstract class Componente {
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public abstract void render(IRenderer renderer);
}
