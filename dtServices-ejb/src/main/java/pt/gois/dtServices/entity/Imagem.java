package pt.gois.dtServices.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the imagem database table.
 * 
 */
@Entity
@NamedQuery(name="Imagem.findAll", query="SELECT i FROM Imagem i")
public class Imagem implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String descricao;
	private String filename;
	private byte[] imagem;
	private String mimeType;
	private int size;
	private Date updateDt;
	private String updateUser;
	private List<Imovel> imovels;
	private List<Servico> servicos;

	public Imagem() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public String getFilename() {
		return this.filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}


	@Lob
	public byte[] getImagem() {
		return this.imagem;
	}

	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}


	public String getMimeType() {
		return this.mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}


	public int getSize() {
		return this.size;
	}

	public void setSize(int size) {
		this.size = size;
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


	//bi-directional many-to-many association to Imovel
	@ManyToMany
	@JoinTable(
		name="imagem_imovel"
		, joinColumns={
			@JoinColumn(name="imagemId")
			}
		, inverseJoinColumns={
			@JoinColumn(name="imovelId")
			}
		)
	public List<Imovel> getImovels() {
		return this.imovels;
	}

	public void setImovels(List<Imovel> imovels) {
		this.imovels = imovels;
	}


	//bi-directional many-to-many association to Servico
	@ManyToMany
	@JoinTable(
		name="imagem_servico"
		, joinColumns={
			@JoinColumn(name="imagemId")
			}
		, inverseJoinColumns={
			@JoinColumn(name="servicoId")
			}
		)
	public List<Servico> getServicos() {
		return this.servicos;
	}

	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}

}