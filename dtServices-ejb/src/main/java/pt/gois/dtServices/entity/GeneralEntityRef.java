package pt.gois.dtServices.entity;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class GeneralEntityRef implements Serializable{
	static final long serialVersionUID = 1L;

	Integer id;
	
	public String toString(){
		if( id != null ){
			return id.toString();
		}else{
			return "";
		}
	}
}
