package edu.uoc.pfc.formwork.infraestructura;

import org.drools.KnowledgeBase;

public class FormworkContext {

	private KnowledgeBase knowledgeBase;

	public KnowledgeBase getKnowledgeBase() {
		return knowledgeBase;
	}

	public void setKnowledgeBase(KnowledgeBase knowledgeBase) {
		this.knowledgeBase = knowledgeBase;
	}

}
