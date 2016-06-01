package pt.gois.dtServices.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the imagem_servico database table.
 * 
 */
@Embeddable
public class ImagemServicoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private int servicoId;
	private int imagemId;

	public ImagemServicoPK() {
	}

	@Column(insertable=false, updatable=false)
	public int getServicoId() {
		return this.servicoId;
	}
	public void setServicoId(int servicoId) {
		this.servicoId = servicoId;
	}

	@Column(insertable=false, updatable=false)
	public int getImagemId() {
		return this.imagemId;
	}
	public void setImagemId(int imagemId) {
		this.imagemId = imagemId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ImagemServicoPK)) {
			return false;
		}
		ImagemServicoPK castOther = (ImagemServicoPK)other;
		return 
			(this.servicoId == castOther.servicoId)
			&& (this.imagemId == castOther.imagemId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.servicoId;
		hash = hash * prime + this.imagemId;
		
		return hash;
	}
}