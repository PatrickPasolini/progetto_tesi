package it.unibs.controller.accesso;

import it.unibs.controller.ControllerConfiguratore;
import it.unibs.model.Model;
import it.unibs.model.ModelAccesso;
import it.unibs.view.accesso.ViewAccesso;
import it.unibs.view.atomicElements.BaseView;
import it.unibs.view.configuratore.ViewMenuConfiguratore;

public interface StrategyAccesso {
	void eseguiAccesso(ModelAccesso modelAccesso, BaseView viewAccesso);
	
}
