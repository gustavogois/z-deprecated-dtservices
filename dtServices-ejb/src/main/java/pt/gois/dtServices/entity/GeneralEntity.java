package pt.gois.dtServices.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@MappedSuperclass
public class GeneralEntity extends GeneralEntityRef{
	static final long serialVersionUID = 1L;

	Date updateDt;
	Integer updateUser;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updateDt", nullable = false, length = 29)
	public Date getUpdateDt() {
		if( updateDt == null ){
			updateDt = new Date();
		}
		return updateDt;
	}

	public void setUpdateDt(Date updateDt) {
		this.updateDt = updateDt;
	}
	
	public Integer getUpdateUser() {
		if( updateUser == null ){
			updateUser = 0;
		}
		return updateUser;
	}
	
	public void setUpdateUser(Integer updateUser) {
		this.updateUser = updateUser;
	}
	
	@PreUpdate
	@PrePersist
	public void updateUserUpdate(){
		this.updateUser = 0;
	}

}
