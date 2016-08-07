package pt.gois.dtServices.controller;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import pt.gois.dtServices.business.ProcessoExternoSBLocal;
import pt.gois.dtServices.business.SolicitanteSBLocal;
import pt.gois.dtServices.entity.ProcessoExterno;
import pt.gois.dtServices.entity.Solicitante;
import pt.gois.dtServices.entity.TipoDeEstado;

@ManagedBean
@ViewScoped
public class ProcessoExternoEditMB extends GeneralMB implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	private ProcessoExternoSBLocal sb;
	
	@EJB
	private SolicitanteSBLocal sbSolicitante;
	
	ProcessoExterno processoExterno;
	
	public List<Solicitante> getSolicitantes() {
		return sbSolicitante.findAll();
	}
	
	public void validateName(FacesContext context, UIComponent toValidate, Object value) throws Exception {
	}
	
	public String create() {
		processoExterno = new ProcessoExterno();
		sb.create(processoExterno);
		return "processoExternoEdit";
	}
	
	public String save(){
//		TipoServicoSolicitante tipoServicoSolicitante = getTipoServicoSolicitante();
//		if( tipoServicoSolicitante.getId() != null ){
//			sb.save( tipoServicoSolicitante );
//		}else{
//			sb.create( tipoServicoSolicitante );
//		}
		return "processoExternoList";
		
	}
	
	public void delete( ProcessoExterno processoExterno ){
		sb.delete(processoExterno);
	}
	
	public ProcessoExterno getProcessoExterno() {
		if( processoExterno == null ){
			Integer id = getId();
			if( id != null ){
				processoExterno = sb.findById( getId() );
			}else{
				processoExterno = new ProcessoExterno();
				processoExterno.setSolicitante(new Solicitante());
				processoExterno.setTipoDeEstado(new TipoDeEstado());
			}
		}
		return processoExterno;
	}
	
	public void setSb(ProcessoExternoSBLocal sb) {
		this.sb = sb;
	}
	
	public ProcessoExternoSBLocal getSb() {
		return this.sb;
	}
}
