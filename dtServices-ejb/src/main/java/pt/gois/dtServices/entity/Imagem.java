package pt.gois.dtServices.entity;

import java.io.Serializable;
import javax.persistence.*;
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
	private byte[] imagem;
	private List<Imovel> imovels;
	private List<ImagemServico> imagemServicos;

	public Imagem() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
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
		return this.imovels;
	}

	public void setImovels(List<Imovel> imovels) {
		this.imovels = imovels;
	}


	//bi-directional many-to-one association to ImagemServico
	@OneToMany(mappedBy="imagem")
	public List<ImagemServico> getImagemServicos() {
		return this.imagemServicos;
	}

	public void setImagemServicos(List<ImagemServico> imagemServicos) {
		this.imagemServicos = imagemServicos;
	}

	public ImagemServico addImagemServico(ImagemServico imagemServico) {
		getImagemServicos().add(imagemServico);
		imagemServico.setImagem(this);

		return imagemServico;
	}

	public ImagemServico removeImagemServico(ImagemServico imagemServico) {
		getImagemServicos().remove(imagemServico);
		imagemServico.setImagem(null);

		return imagemServico;
	}

}