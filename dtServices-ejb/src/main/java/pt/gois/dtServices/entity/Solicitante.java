package pt.gois.dtServices.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the t_solicitante database table.
 * 
 */
@Entity
@Table(name="t_solicitante")
@NamedQuery(name="Solicitante.findAll", query="SELECT t FROM Solicitante t")
public class Solicitante implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private String cc;

	private String codigoPostal;

	private String complemento;

	private String dd;

	private String localidade;

	private String nif;

	private String nome;

	private String ruaPorta;

	private String sigla;

	private String telefone;

	@Temporal(TemporalType.TIMESTAMP)
	private Date updateDt;

	private String updateUser;

	//bi-directional many-to-one association to Processo
	@OneToMany(mappedBy="solicitante")
	private List<Processo> processos;

	public Solicitante() {
	}

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

	public List<Processo> getProcessos() {
		return this.processos;
	}

	public void setProcessos(List<Processo> processos) {
		this.processos = processos;
	}

	public Processo addProcesso(Processo processo) {
		getProcessos().add(processo);
		processo.setSolicitante(this);

		return processo;
	}

	public Processo removeProcesso(Processo processo) {
		getProcessos().remove(processo);
		processo.setSolicitante(null);

		return processo;
	}

}