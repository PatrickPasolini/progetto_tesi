package it.unibs.controller.accesso;

import it.unibs.model.ModelAccesso;
import it.unibs.view.atomicElements.BaseView;

public interface StrategyAccesso {
	void eseguiAccesso(ModelAccesso modelAccesso, BaseView viewAccesso);
	
}
