package pt.gois.dtServices.report;

import pt.gois.dtServices.entity.ProcessoView;

public class VisaoGeralReport {
	
	ProcessoView processoView;
	
	public String getCodExterno() {
		return processoView.getCodExterno();
	}
	
	public String getNomeEstado() {
		return processoView.getNomeEstado(); 
	}
}
