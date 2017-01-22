package pt.gois.dtServices.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the t_ref_tipo_servico database table.
 * 
 */
@Entity
@Table(name="t_ref_tipo_estado")
@NamedQuery(name="TipoEstado.findAll", query="SELECT t FROM TipoEstado t")
public class TipoEstado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private String grupo;

	private String nome;

	//bi-directional many-to-one association to TEstadoProcesso
	@OneToMany(mappedBy="tipoEstado")
	private List<EstadoProcesso> estadoProcessos;

	//bi-directional many-to-one association to TEstadoServico
	@OneToMany(mappedBy="tipoEstado")
	private List<EstadoServico> estadoServicos;

	public TipoEstado() {
	}

	public TipoEstado(Integer id) {
		this.id = id;
	}
	
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<EstadoProcesso> getEstadoProcessos() {
		return this.estadoProcessos;
	}

	public void setEstadoProcessos(List<EstadoProcesso> estadoProcessos) {
		this.estadoProcessos = estadoProcessos;
	}

	public EstadoProcesso addEstadoProcesso(EstadoProcesso estadoProcesso) {
		getEstadoProcessos().add(estadoProcesso);
		estadoProcesso.setTipoEstado(this);

		return estadoProcesso;
	}

	public EstadoProcesso removeEstadoProcesso(EstadoProcesso estadoProcesso) {
		getEstadoProcessos().remove(estadoProcesso);
		estadoProcesso.setTipoEstado(null);

		return estadoProcesso;
	}

	public List<EstadoServico> getEstadoServicos() {
		return this.estadoServicos;
	}

	public void setEstadoServicos(List<EstadoServico> estadoServicos) {
		this.estadoServicos = estadoServicos;
	}

	public EstadoServico addEstadoServico(EstadoServico estadoServico) {
		getEstadoServicos().add(estadoServico);
		estadoServico.setTipoEstado(this);

		return estadoServico;
	}

	public EstadoServico removeEstadoServico(EstadoServico estadoServico) {
		getEstadoServicos().remove(estadoServico);
		estadoServico.setTipoEstado(null);

		return estadoServico;
	}
	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

}