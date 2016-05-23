package pt.gois.dtServices.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the endereco database table.
 * 
 */
@Entity
@Table(name="endereco")
@NamedQuery(name="Endereco.findAll", query="SELECT e FROM Endereco e")
public class Endereco implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String codigoPostal;
	private String endereco;
	private Distrito distrito;
	private Mapa mapa;
	private List<Imovel> imovels;

	public Endereco() {
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


	@Column(nullable=false, length=9)
	public String getCodigoPostal() {
		return this.codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}


	@Column(length=60)
	public String getEndereco() {
		return this.endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}


	//bi-directional many-to-one association to Distrito
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idDistrito", nullable=false)
	public Distrito getDistrito() {
		return this.distrito;
	}

	public void setDistrito(Distrito distrito) {
		this.distrito = distrito;
	}


	//bi-directional many-to-one association to Mapa
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="mapId", nullable=false)
	public Mapa getMapa() {
		return this.mapa;
	}

	public void setMapa(Mapa mapa) {
		this.mapa = mapa;
	}


	//bi-directional many-to-one association to Imovel
	@OneToMany(mappedBy="endereco")
	public List<Imovel> getImovels() {
		return this.imovels;
	}

	public void setImovels(List<Imovel> imovels) {
		this.imovels = imovels;
	}

	public Imovel addImovel(Imovel imovel) {
		getImovels().add(imovel);
		imovel.setEndereco(this);

		return imovel;
	}

	public Imovel removeImovel(Imovel imovel) {
		getImovels().remove(imovel);
		imovel.setEndereco(null);

		return imovel;
	}

}