package pt.gois.dtServices.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the tbl_solicitante database table.
 * 
 */
@Entity
@Table(name="tbl_solicitante")
@NamedQuery(name="Solicitante.findAll", query="SELECT s FROM Solicitante s")
public class Solicitante implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String cc;
	private int chaveSolicitanteProcesso;
	private String codigoPostal;
	private String complemento;
	private String dd;
	private String localidade;
	private String nif;
	private String nome;
	private String ruaPorta;
	private String sigla;
	private String telefone;
	private Date updateDt;
	private String updateUser;
	private List<EntidadeDeFacturacao> tblEntidadeDeFacturacaos;
	private List<Processoexterno> tblProcessoexternos;
	private List<TipoServicoSolicitante> tblTipoServicoSolicitantes;

	public Solicitante() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public String getCc() {
		return this.cc;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}


	public int getChaveSolicitanteProcesso() {
		return this.chaveSolicitanteProcesso;
	}

	public void setChaveSolicitanteProcesso(int chaveSolicitanteProcesso) {
		this.chaveSolicitanteProcesso = chaveSolicitanteProcesso;
	}


	public String getCodigoPostal() {
		return this.codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}


	public String getComplemento() {
		return this.complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}


	public String getDd() {
		return this.dd;
	}

	public void setDd(String dd) {
		this.dd = dd;
	}


	public String getLocalidade() {
		return this.localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}


	public String getNif() {
		return this.nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}


	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getRuaPorta() {
		return this.ruaPorta;
	}

	public void setRuaPorta(String ruaPorta) {
		this.ruaPorta = ruaPorta;
	}


	public String getSigla() {
		return this.sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}


	public String getTelefone() {
		return this.telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}


	@Temporal(TemporalType.TIMESTAMP)
	public Date getUpdateDt() {
		return this.updateDt;
	}

	public void setUpdateDt(Date updateDt) {
		this.updateDt = updateDt;
	}


	public String getUpdateUser() {
		return this.updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}


	//bi-directional many-to-one association to EntidadeDeFacturacao
	@OneToMany(mappedBy="tblSolicitante")
	public List<EntidadeDeFacturacao> getTblEntidadeDeFacturacaos() {
		return this.tblEntidadeDeFacturacaos;
	}

	public void setTblEntidadeDeFacturacaos(List<EntidadeDeFacturacao> tblEntidadeDeFacturacaos) {
		this.tblEntidadeDeFacturacaos = tblEntidadeDeFacturacaos;
	}

	public EntidadeDeFacturacao addTblEntidadeDeFacturacao(EntidadeDeFacturacao tblEntidadeDeFacturacao) {
		getTblEntidadeDeFacturacaos().add(tblEntidadeDeFacturacao);
		tblEntidadeDeFacturacao.setTblSolicitante(this);

		return tblEntidadeDeFacturacao;
	}

	public EntidadeDeFacturacao removeTblEntidadeDeFacturacao(EntidadeDeFacturacao tblEntidadeDeFacturacao) {
		getTblEntidadeDeFacturacaos().remove(tblEntidadeDeFacturacao);
		tblEntidadeDeFacturacao.setTblSolicitante(null);

		return tblEntidadeDeFacturacao;
	}


	//bi-directional many-to-one association to Processoexterno
	@OneToMany(mappedBy="tblSolicitante")
	public List<Processoexterno> getTblProcessoexternos() {
		return this.tblProcessoexternos;
	}

	public void setTblProcessoexternos(List<Processoexterno> tblProcessoexternos) {
		this.tblProcessoexternos = tblProcessoexternos;
	}

	public Processoexterno addTblProcessoexterno(Processoexterno tblProcessoexterno) {
		getTblProcessoexternos().add(tblProcessoexterno);
		tblProcessoexterno.setTblSolicitante(this);

		return tblProcessoexterno;
	}

	public Processoexterno removeTblProcessoexterno(Processoexterno tblProcessoexterno) {
		getTblProcessoexternos().remove(tblProcessoexterno);
		tblProcessoexterno.setTblSolicitante(null);

		return tblProcessoexterno;
	}


	//bi-directional many-to-one association to TipoServicoSolicitante
	@OneToMany(mappedBy="tblSolicitante")
	public List<TipoServicoSolicitante> getTblTipoServicoSolicitantes() {
		return this.tblTipoServicoSolicitantes;
	}

	public void setTblTipoServicoSolicitantes(List<TipoServicoSolicitante> tblTipoServicoSolicitantes) {
		this.tblTipoServicoSolicitantes = tblTipoServicoSolicitantes;
	}

	public TipoServicoSolicitante addTblTipoServicoSolicitante(TipoServicoSolicitante tblTipoServicoSolicitante) {
		getTblTipoServicoSolicitantes().add(tblTipoServicoSolicitante);
		tblTipoServicoSolicitante.setTblSolicitante(this);

		return tblTipoServicoSolicitante;
	}

	public TipoServicoSolicitante removeTblTipoServicoSolicitante(TipoServicoSolicitante tblTipoServicoSolicitante) {
		getTblTipoServicoSolicitantes().remove(tblTipoServicoSolicitante);
		tblTipoServicoSolicitante.setTblSolicitante(null);

		return tblTipoServicoSolicitante;
	}

}