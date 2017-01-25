package pt.gois.dtServices.controller;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import pt.gois.dtServices.business.TipoServicoSB;
import pt.gois.dtServices.business.TipoServicoSBLocal;
import pt.gois.dtServices.entity.Concelho;
import pt.gois.dtServices.entity.Distrito;
import pt.gois.dtServices.entity.EnderecoVW;
import pt.gois.dtServices.entity.Solicitante;
import pt.gois.dtServices.entity.TipoServico;

@ManagedBean
@ViewScoped
public class SolicitanteEditMB extends GeneralMB implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	private pt.gois.dtServices.business.SolicitanteSBLocal sb;

	@EJB
	private pt.gois.dtServices.business.EnderecoSBLocal sbEndereco;

	@EJB
	private pt.gois.dtServices.business.TipoServicoSBLocal sbTiposServico;

	Solicitante solicitante;
	TipoServico tipoServico;

//	public void onTipoServicoChange() {
//		if (tipoServico != null) {
//			tipoServico = sbTiposServico.findById(tipoServico.getId());
//		}
//		tipoServicoSolicitante.setTipoServico(tipoServico);
//		tipoServicoSolicitante.setValor(tipoServico.getValor());
//	}

	public String save() {
		Solicitante solicitante = getSolicitante();
		
		if (solicitante.getId() != null) {
			sb.save(solicitante);
		} else {
			sb.create(solicitante);
		}
		return "/pages/solicitante/solicitanteList?faces-redirect=true"; 
	}


	public String delete(Solicitante solicitante) {
		try {
			sb.delete(solicitante);
		} catch(EJBException e) {
			if(sb.isCauseException(TipoServicoSB.CONSTRAINT_VIOLATION_EXCEPTION, e)) {
				String mensagem = "Não é possível excluir este Solicitante. Existem Processos Internos associados.";
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, mensagem, "System Error"));
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, TipoServicoSBLocal.MSG_FATAL_ERRO, 
						"Erro desconhecido na exclusão do Solicitante"));
			}
		}
		return "solicitanteList";
	}

	public Solicitante getSolicitante() {
		if (solicitante == null) {
			Integer id = getId();
			if (id != null) {
				solicitante = sb.findById(getId());
			} else {
				solicitante = new Solicitante();
				solicitante.setConcelho(new Concelho());
				solicitante.setDistrito(new Distrito());
			}
		}
		return solicitante;
	}

	public void onEnderecoSelect(SelectEvent event) {
		Solicitante solicitante = getSolicitante();
		EnderecoVW end = (EnderecoVW) event.getObject();
		end = sbEndereco.findById(end.getId());

		solicitante.setRuaPorta(end.getRuaPorta());
		solicitante.setComplemento(end.getComplemento());
		solicitante.setLocalidade(end.getLocalidade());
//		solicitante.setDistrito(end.getDistrito());
//		solicitante.setConcelho(end.getConcelho());
		solicitante.setCodigoPostal(end.getCodigoPostal());
	}

	private boolean naoENovoSolicitante() {
		return solicitante != null ;
	}

	public void setSolicitante(Solicitante solicitante) {
		this.solicitante = solicitante;
	}

	public pt.gois.dtServices.business.SolicitanteSBLocal getSb() {
		return sb;
	}

	public void setSBLocalb(pt.gois.dtServices.business.SolicitanteSBLocal sb) {
		this.sb = sb;
	}

	public TipoServico getTipoServico() {
		if (this.tipoServico == null) {
			tipoServico = new TipoServico();
		}
		return tipoServico;
	}

	public void setTipoServico(TipoServico tipoServico) {
		this.tipoServico = tipoServico;
	}

	public pt.gois.dtServices.business.EnderecoSBLocal getSbEndereco() {
		return sbEndereco;
	}

	public void setSbEndereco(pt.gois.dtServices.business.EnderecoSBLocal sbEndereco) {
		this.sbEndereco = sbEndereco;
	}

	public pt.gois.dtServices.business.TipoServicoSBLocal getSbTiposServico() {
		return sbTiposServico;
	}

	public void setSbTiposServico(pt.gois.dtServices.business.TipoServicoSBLocal sbTiposServico) {
		this.sbTiposServico = sbTiposServico;
	}

	public void setSb(pt.gois.dtServices.business.SolicitanteSBLocal sb) {
		this.sb = sb;
	}

}
