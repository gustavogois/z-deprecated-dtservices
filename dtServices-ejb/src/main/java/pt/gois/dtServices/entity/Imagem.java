package pt.gois.dtServices.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the imagem database table.
 * 
 */
@Entity
@Table(name="t_imagem")
@NamedQueries( {
	@NamedQuery(name="Imagem.findAll", query="SELECT i FROM Imagem i"),
	@NamedQuery(name="Imagem.findByImovel", query="SELECT i FROM Imagem i join i.imovels im WHERE im.id = :imovelId"),
	@NamedQuery(name="Imagem.findById", query="SELECT i FROM Imagem i WHERE i.id = :id")
})

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
		name="t_imagem_imovel"
		, joinColumns={
			@JoinColumn(name="imagemId")
			}
		, inverseJoinColumns={
			@JoinColumn(name="imovelId")
			}
		)
	public List<Imovel> getImovels() {
		if( this.imovels == null ){
			this.imovels = new ArrayList<Imovel>();
		}
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