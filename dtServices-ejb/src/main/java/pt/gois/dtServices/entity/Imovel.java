package pt.gois.dtServices.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

/**
 * The persistent class for the imovel database table.
 * 
 */
@Entity
@NamedQuery(name = "Imovel.findAll", query = "SELECT i FROM Imovel i")
public class Imovel extends Endereco implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String crp;
	private String inquilino;
	
	String latitude;
	String longitude;
	
	private List<Imagem> imagems;
	private ProcessoExterno processoExterno;

	public Imovel() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCrp() {
		return this.crp;
	}

	public void setCrp(String crp) {
		this.crp = crp;
	}

	public String getInquilino() {
		return this.inquilino;
	}

	public void setInquilino(String inquilino) {
		this.inquilino = inquilino;
	}

	// bi-directional many-to-many association to Imagem
	@ManyToMany(mappedBy = "imovels")
	public List<Imagem> getImagems() {
		return this.imagems;
	}

	public void setImagems(List<Imagem> imagems) {
		this.imagems = imagems;
	}

	// bi-directional one-to-one association to Processocliente
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id")
	public ProcessoExterno getProcessoExterno() {
		return this.processoExterno;
	}

	public void setProcessoExterno(ProcessoExterno processoExterno) {
		this.processoExterno = processoExterno;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
}