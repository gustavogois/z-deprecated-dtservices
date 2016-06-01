package pt.gois.dtServices.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the imagem_servico database table.
 * 
 */
@Entity
@Table(name="imagem_servico")
@NamedQuery(name="ImagemServico.findAll", query="SELECT i FROM ImagemServico i")
public class ImagemServico implements Serializable {
	private static final long serialVersionUID = 1L;
	private ImagemServicoPK imagemServicoId;
	private Integer id;
	private Imagem imagem;
	private Servico servico;

	public ImagemServico() {
	}


	@EmbeddedId
	public ImagemServicoPK getImagemServicoId() {
		return this.imagemServicoId;
	}

	public void setId(ImagemServicoPK id) {
		this.imagemServicoId = id;
	}


	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	//bi-directional many-to-one association to Imagem
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="imagemId")
	public Imagem getImagem() {
		return this.imagem;
	}

	public void setImagem(Imagem imagem) {
		this.imagem = imagem;
	}


	//bi-directional many-to-one association to Servico
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="servicoId")
	public Servico getServico() {
		return this.servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

}