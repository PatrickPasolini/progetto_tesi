package it.unibs.controller.accesso;

import it.unibs.model.ModelAccesso;
import it.unibs.view.accesso.BaseView;
import it.unibs.view.accesso.ViewAccesso;

public interface StrategyAccesso {
	void eseguiAccesso(ModelAccesso modelAccesso, BaseView viewAccesso);
}
