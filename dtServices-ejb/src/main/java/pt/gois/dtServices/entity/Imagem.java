package pt.gois.dtServices.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the imagem database table.
 * 
 */
@Entity
@NamedQueries( {
	@NamedQuery(name="Imagem.findAll", query="SELECT i FROM Imagem i"),
	@NamedQuery(name="Imagem.findByImovel", query="SELECT i FROM Imagem i join i.imovels im WHERE im.id = :imovelId"),
	@NamedQuery(name="Imagem.findById", query="SELECT i FROM Imagem i WHERE i.id = :id")
})
public class Imagem extends GeneralEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String descricao;
	private byte[] imagem;
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


	@Lob
	public byte[] getImagem() {
		return this.imagem;
	}

	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
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
		if( this.imovels == null ){
			this.imovels = new ArrayList<Imovel>();
		}
		return this.imovels;
	}

	public void setImovels(List<Imovel> imovels) {
		this.imovels = imovels;
	}


	//bi-directional many-to-one association to ImagemServico
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