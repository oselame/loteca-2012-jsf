package br.com.softal.base.model;

import java.io.Serializable;

import javax.persistence.Transient;

@SuppressWarnings("serial")
public abstract class Entity implements Serializable {
	
	public static final String STATUS_UNMODIFIED = "";
	public static final String STATUS_INSERT = "I";
	public static final String STATUS_UPDATE = "U";
	public static final String STATUS_DELETE = "D";
	
	@Transient
	public String status;

	public String getStatus() {
		if (status == null) {
			return Entity.STATUS_UNMODIFIED;
		}
		return status;
	}

	public void setStatus(String status) {
		if (status == null) {
			this.status = Entity.STATUS_UNMODIFIED;
		} else {
			this.status = status;
		}
	}
	
	public void setStatusInsert() {
		setStatus(Entity.STATUS_INSERT);
	}
	
	public void setStatusUpdate() {
		setStatus(Entity.STATUS_UPDATE);
	}
	
	public void setStatusDelete() {
		setStatus(Entity.STATUS_DELETE);
	}
	
	public void setStatusUnModified() {
		setStatus(Entity.STATUS_UNMODIFIED);
	}
	
	public Boolean isStatusInsert() {
		return getStatus() != null && !"".equalsIgnoreCase(getStatus()) && getStatus().equalsIgnoreCase(Entity.STATUS_INSERT);
	}
	
	public Boolean isStatusUpdate() {
		return getStatus() != null && !"".equalsIgnoreCase(getStatus()) && getStatus().equalsIgnoreCase(Entity.STATUS_UPDATE);
	}
	
	public Boolean isStatusDelete() {
		return getStatus() != null && !"".equalsIgnoreCase(getStatus()) && getStatus().equalsIgnoreCase(Entity.STATUS_DELETE);
	}
	
	public Boolean isStatusUnModified() {
		return getStatus() != null && !"".equalsIgnoreCase(getStatus()) && getStatus().equalsIgnoreCase(Entity.STATUS_UNMODIFIED);
	}
	
	public abstract void inicializaRelacionamentos() ;
	

	/*
	public abstract long getId();

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Long.signum(getId() ^ (getId() >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Entity other = (Entity) obj;
		if (getId() != other.getId())
			return false;
		return true;
	}

	public abstract String getSearchColumnTable();

	public abstract String getSearchColumnEntity();
	*/

}
