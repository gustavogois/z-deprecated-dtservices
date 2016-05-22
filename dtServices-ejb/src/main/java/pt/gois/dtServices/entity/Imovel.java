package pt.gois.dtServices.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the imovel database table.
 * 
 */
@Entity
@Table(name="imovel")
@NamedQuery(name="Imovel.findAll", query="SELECT i FROM Imovel i")
public class Imovel implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String crp;
	private String inquilino;
	private Endereco endereco;
	private List<Iimagem> iimagems;
	private List<Processo> processos;

	public Imovel() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}


	@Column(length=30)
	public String getCrp() {
		return this.crp;
	}

	public void setCrp(String crp) {
		this.crp = crp;
	}


	@Column(length=30)
	public String getInquilino() {
		return this.inquilino;
	}

	public void setInquilino(String inquilino) {
		this.inquilino = inquilino;
	}


	//bi-directional many-to-one association to Endereco
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="enderecoId")
	public Endereco getEndereco() {
		return this.endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}


	//bi-directional many-to-many association to Iimagem
	@ManyToMany
	@JoinTable(
		name="iimovelimagens"
		, joinColumns={
			@JoinColumn(name="imovelId", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="imagemId", nullable=false)
			}
		)
	public List<Iimagem> getIimagems() {
		return this.iimagems;
	}

	public void setIimagems(List<Iimagem> iimagems) {
		this.iimagems = iimagems;
	}


	//bi-directional many-to-one association to Processo
	@OneToMany(mappedBy="imovel")
	public List<Processo> getProcessos() {
		return this.processos;
	}

	public void setProcessos(List<Processo> processos) {
		this.processos = processos;
	}

	public Processo addProcesso(Processo processo) {
		getProcessos().add(processo);
		processo.setImovel(this);

		return processo;
	}

	public Processo removeProcesso(Processo processo) {
		getProcessos().remove(processo);
		processo.setImovel(null);

		return processo;
	}

}