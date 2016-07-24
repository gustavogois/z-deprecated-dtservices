package pt.gois.dtServices.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the imovel database table.
 * 
 */
@Entity
@NamedQuery(name="Imovel.findAll", query="SELECT i FROM Imovel i")
public class Imovel implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String codigoPostal;
	private String crp;
	private String endereco;
	private String inquilino;
	private List<Imagem> imagems;
	private ProcessoCliente processocliente;

	public Imovel() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public String getCodigoPostal() {
		return this.codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}


	public String getCrp() {
		return this.crp;
	}

	public void setCrp(String crp) {
		this.crp = crp;
	}


	public String getEndereco() {
		return this.endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}


	public String getInquilino() {
		return this.inquilino;
	}

	public void setInquilino(String inquilino) {
		this.inquilino = inquilino;
	}


	//bi-directional many-to-many association to Imagem
	@ManyToMany(mappedBy="imovels")
	public List<Imagem> getImagems() {
		return this.imagems;
	}

	public void setImagems(List<Imagem> imagems) {
		this.imagems = imagems;
	}


	//bi-directional one-to-one association to Processocliente
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id")
	public ProcessoCliente getProcessocliente() {
		return this.processocliente;
	}

	public void setProcessocliente(ProcessoCliente processocliente) {
		this.processocliente = processocliente;
	}

}