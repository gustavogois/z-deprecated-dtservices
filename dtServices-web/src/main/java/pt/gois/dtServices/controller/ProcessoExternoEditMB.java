package pt.gois.dtServices.controller;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import pt.gois.dtServices.business.ProcessoExternoSBLocal;
import pt.gois.dtServices.business.SolicitanteSBLocal;
import pt.gois.dtServices.business.TipoDeEstadoSBLocal;
import pt.gois.dtServices.entity.Concelho;
import pt.gois.dtServices.entity.Distrito;
import pt.gois.dtServices.entity.EnderecoVW;
import pt.gois.dtServices.entity.Imovel;
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
	
	@EJB
	private pt.gois.dtServices.business.EnderecoSBLocal sbEndereco;
	
	ProcessoExterno processoExterno;
	
	EnderecoVW endereco;
	
	public EnderecoVW getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoVW endereco) {
		this.endereco = endereco;
	}

	public void onEnderecoSelect(SelectEvent event) {
        Imovel imovel = getProcessoExterno().getImovel();
        EnderecoVW end = (EnderecoVW)event.getObject();
        end = sbEndereco.findById(new Integer(end.getId()));
        this.setEndereco(end);
        
        imovel.setRuaPorta(end.getRuaPorta());
        imovel.setComplemento(end.getComplemento());
        imovel.setLocalidade(end.getLocalidade());
        imovel.setDistrito(end.getDistrito());
        imovel.setConcelho(end.getConcelho());
        imovel.setCodigoPostal(end.getCodigoPostal());
    }

	
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
		ProcessoExterno processoExterno = getProcessoExterno();
		
		TipoDeEstado criado = new TipoDeEstado();
		criado.setId(TipoDeEstadoSBLocal.CRIADO);
		
		processoExterno.setTipoDeEstado(criado);
		
		if( processoExterno.getId() != null ){
			sb.save( processoExterno );
		}else{
			sb.create( processoExterno );
		}
		return "processoExternoList";
		
	}
	
	public void delete( ProcessoExterno processoExterno ){
		sb.delete(processoExterno);
	}
	
	public void validateEndereco(FacesContext context, UIComponent toValidate, Object value) throws Exception {
		
	}
	
	public void validateCrp(FacesContext context, UIComponent toValidate, Object value) throws Exception {
		
	}
	
	public void validateInquilino(FacesContext context, UIComponent toValidate, Object value) throws Exception {
		
	}
	
	public ProcessoExterno getProcessoExterno() {
		if( processoExterno == null ){
			Integer id = getId();
			if( id != null ){
				processoExterno = sb.findById( getId() );
				endereco = new EnderecoVW();
				endereco.setCodigoPostal(processoExterno.getImovel().getCodigoPostal());
			}else{
				processoExterno = new ProcessoExterno();
				processoExterno.setSolicitante(new Solicitante());
				processoExterno.setTipoDeEstado(new TipoDeEstado());
				Imovel imovel = new Imovel();
				imovel.setConcelho(new Concelho());
				imovel.setDistrito(new Distrito());
				processoExterno.setImovel(imovel);
				imovel.setProcessoExterno(processoExterno);
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
