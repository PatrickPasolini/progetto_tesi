package it.unibs.controller.accesso;

import it.unibs.model.ModelAccesso;
import it.unibs.view.ViewAccesso;

public interface StrategyAccesso {
	void eseguiAccesso(ModelAccesso modelAccesso, ViewAccesso viewAccesso);
}
