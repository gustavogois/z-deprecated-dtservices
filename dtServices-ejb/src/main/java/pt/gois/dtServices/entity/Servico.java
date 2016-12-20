package pt.gois.dtServices.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the servico database table.
 * 
 */
@Entity
@NamedQuery(name="Servico.findAll", query="SELECT s FROM Servico s")
public class Servico extends GeneralEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Date dtCadastro;
	private Date dtFim;
	private Date dtInicio;
	private String observacoes;
	private double valor;
	private List<Imagem> imagems;
	private TipoDeEstado tipoDeEstado;
	private ProcessoInterno processoInterno;
	private TipoServicoSolicitante tipoServicoSolicitante;
	private List<EstadosServico> estadosServicos;

	public Servico() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	@Temporal(TemporalType.DATE)
	public Date getDtCadastro() {
		return this.dtCadastro;
	}

	public void setDtCadastro(Date dtCadastro) {
		this.dtCadastro = dtCadastro;
	}


	@Temporal(TemporalType.DATE)
	public Date getDtFim() {
		return this.dtFim;
	}

	public void setDtFim(Date dtFim) {
		this.dtFim = dtFim;
	}


	@Temporal(TemporalType.DATE)
	public Date getDtInicio() {
		return this.dtInicio;
	}

	public void setDtInicio(Date dtInicio) {
		this.dtInicio = dtInicio;
	}


	public String getObservacoes() {
		return this.observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}


	public double getValor() {
		return this.valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}


	//bi-directional many-to-one association to ImagemServico
	@ManyToMany(mappedBy="servicos")
	public List<Imagem> getImagems() {
		return this.imagems;
	}

	public void setImagems(List<Imagem> imagems) {
		this.imagems = imagems;
	}

	//bi-directional many-to-one association to TiposDeEstado
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="estado_atual_Id")
	public TipoDeEstado getTipoDeEstado() {
		return this.tipoDeEstado;
	}

	public void setTipoDeEstado(TipoDeEstado tipoDeEstado) {
		this.tipoDeEstado = tipoDeEstado;
	}


	//bi-directional many-to-one association to ProcessoInterno
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="processoId")
	public ProcessoInterno getProcessoInterno() {
		return this.processoInterno;
	}

	public void setProcessoInterno(ProcessoInterno processoInterno) {
		this.processoInterno = processoInterno;
	}


	//bi-directional many-to-one association to TipoServicoSolicitante
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="tipoServico_SolicitanteId")
	public TipoServicoSolicitante getTipoServicoSolicitante() {
		return this.tipoServicoSolicitante;
	}

	public void setTipoServicoSolicitante(TipoServicoSolicitante tipoServicoSolicitante) {
		this.tipoServicoSolicitante = tipoServicoSolicitante;
	}
	//bi-directional many-to-one association to Estadosservico
	@OneToMany(mappedBy="servico", cascade = CascadeType.ALL)
	public List<EstadosServico> getEstadosServicos() {
		if( this.estadosServicos == null ){
			this.estadosServicos = new ArrayList<EstadosServico>();
		}
		return this.estadosServicos;
	}

	public void setEstadosServicos(List<EstadosServico> estadosServicos) {
		this.estadosServicos = estadosServicos;
	}
	
	public EstadosServico retornaEstadoAtual() {
		List<EstadosServico> estadosServicoList = this.getEstadosServicos();
		if(estadosServicoList != null && estadosServicoList.size() > 0) {
			return estadosServicoList.get(estadosServicoList.size() - 1);
		} else {
			return null;
		}
	}


}