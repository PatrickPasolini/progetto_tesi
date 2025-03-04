package it.unibs.controller.accesso;

import it.unibs.model.ModelAccesso;
import it.unibs.view.console.ViewAccesso;

public interface StrategyAccesso {
	void eseguiAccesso(ModelAccesso modelAccesso, ViewAccesso viewAccesso);
}
