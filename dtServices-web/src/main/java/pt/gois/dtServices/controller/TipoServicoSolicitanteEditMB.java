package pt.gois.dtServices.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import org.primefaces.model.DualListModel;

import pt.gois.dtServices.business.SolicitanteSBLocal;
import pt.gois.dtServices.business.TipoServicoSBLocal;
import pt.gois.dtServices.business.TipoServicoSolicitanteSBLocal;
import pt.gois.dtServices.entity.Servico;
import pt.gois.dtServices.entity.Solicitante;
import pt.gois.dtServices.entity.TipoServico;
import pt.gois.dtServices.entity.TipoServicoSolicitante;

@ManagedBean
@ViewScoped
public class TipoServicoSolicitanteEditMB extends GeneralMB implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	private TipoServicoSolicitanteSBLocal sb;
	
	@EJB
	private SolicitanteSBLocal sbSolicitante;
	
	@EJB
	private TipoServicoSBLocal sbTipoServico;
	
	TipoServicoSolicitante tipoServicoSolicitante;
	
	public List<Solicitante> getSolicitantes() {
		return sbSolicitante.findAll();
	}
	
	public DualListModel<String> getTiposServico() {
		
		List<TipoServico> tiposServico = sbTipoServico.findAll();
		
        List<String> tiposServicoSource = new ArrayList<String>();
        List<String> tiposServicoTarget = new ArrayList<String>();
         
        for (TipoServico tipo : tiposServico) {
			tiposServicoSource.add(tipo.getNome());
		}
        return new DualListModel<String>(tiposServicoSource, tiposServicoTarget);
	}
	
	public void validateName(FacesContext context, UIComponent toValidate, Object value) throws Exception {
	}
	
	public String create() {
		tipoServicoSolicitante = new TipoServicoSolicitante();
		sb.create(tipoServicoSolicitante);
		return "tipoServicoSolicitanteEdit";
	}
	
	public String save(){
		TipoServicoSolicitante tipoServicoSolicitante = getTipoServicoSolicitante();
		if( tipoServicoSolicitante.getId() != null ){
			sb.save( tipoServicoSolicitante );
		}else{
			sb.create( tipoServicoSolicitante );
		}
		return "tipoServicoSolicitanteList";
	}
	
	public void delete( TipoServicoSolicitante tipoServicoSolicitante ){
		sb.delete(tipoServicoSolicitante);
	}
	
	public TipoServicoSolicitante getTipoServicoSolicitante() {
		if( tipoServicoSolicitante == null ){
			Integer id = getId();
			if( id != null ){
				tipoServicoSolicitante = sb.findById( getId() );
			}else{
				tipoServicoSolicitante = new TipoServicoSolicitante();
				tipoServicoSolicitante.setServicos(new ArrayList<Servico>());
				tipoServicoSolicitante.setSolicitante(new Solicitante());
				tipoServicoSolicitante.setTipoServico(new TipoServico());
			}
		}
		return tipoServicoSolicitante;
	}
	
	public void setTipoServicoSolicitante(TipoServicoSolicitante tipoServicoSolicitante) {
		this.tipoServicoSolicitante = tipoServicoSolicitante;
	}

	public TipoServicoSolicitanteSBLocal getSb() {
		return sb;
	}

	public void setSb(TipoServicoSolicitanteSBLocal sb) {
		this.sb = sb;
	}
}
