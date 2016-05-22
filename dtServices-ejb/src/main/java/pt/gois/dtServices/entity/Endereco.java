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
	private String concelho;
	private String endereco;
	private Bairro bairro;
	private Freguesia freguesia;
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


	@Column(nullable=false, length=30)
	public String getConcelho() {
		return this.concelho;
	}

	public void setConcelho(String concelho) {
		this.concelho = concelho;
	}


	@Column(length=60)
	public String getEndereco() {
		return this.endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}


	//bi-directional many-to-one association to Bairro
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="bairroId", nullable=false)
	public Bairro getBairro() {
		return this.bairro;
	}

	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
	}


	//bi-directional many-to-one association to Freguesia
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="freguesiaId", nullable=false)
	public Freguesia getFreguesia() {
		return this.freguesia;
	}

	public void setFreguesia(Freguesia freguesia) {
		this.freguesia = freguesia;
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