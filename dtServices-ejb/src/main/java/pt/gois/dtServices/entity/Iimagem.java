package pt.gois.dtServices.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the iimagem database table.
 * 
 */
@Entity
@Table(name="iimagem")
@NamedQuery(name="Iimagem.findAll", query="SELECT i FROM Iimagem i")
public class Iimagem implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String descricao;
	private byte[] imagem;
	private String nome;
	private List<Imovel> imovels;
	private List<Processo> processos;

	public Iimagem() {
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
	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	@Lob
	@Column(nullable=false)
	public byte[] getImagem() {
		return this.imagem;
	}

	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}


	@Column(length=30)
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}


	//bi-directional many-to-many association to Imovel
	@ManyToMany(mappedBy="iimagems")
	public List<Imovel> getImovels() {
		return this.imovels;
	}

	public void setImovels(List<Imovel> imovels) {
		this.imovels = imovels;
	}


	//bi-directional many-to-many association to Processo
	@ManyToMany(mappedBy="iimagems")
	public List<Processo> getProcessos() {
		return this.processos;
	}

	public void setProcessos(List<Processo> processos) {
		this.processos = processos;
	}

}