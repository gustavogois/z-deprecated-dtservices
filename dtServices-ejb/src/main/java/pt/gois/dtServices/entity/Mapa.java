package pt.gois.dtServices.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the mapa database table.
 * 
 */
@Entity
@Table(name="mapa")
@NamedQuery(name="Mapa.findAll", query="SELECT m FROM Mapa m")
public class Mapa implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private double latitude;
	private double longitude;
	private List<Endereco> enderecos;

	public Mapa() {
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


	@Column(nullable=false)
	public double getLatitude() {
		return this.latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}


	@Column(nullable=false)
	public double getLongitude() {
		return this.longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}


	//bi-directional many-to-one association to Endereco
	@OneToMany(mappedBy="mapa")
	public List<Endereco> getEnderecos() {
		return this.enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public Endereco addEndereco(Endereco endereco) {
		getEnderecos().add(endereco);
		endereco.setMapa(this);

		return endereco;
	}

	public Endereco removeEndereco(Endereco endereco) {
		getEnderecos().remove(endereco);
		endereco.setMapa(null);

		return endereco;
	}

}