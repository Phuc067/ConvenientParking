package com.parking.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "permissions")

public class Permission implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6720364013420900699L;
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;
	@Column
	private String name;
	
	@Column(name = "`description`")
	private String description;
	@Column
	private Boolean allowAdd;
	@Column
	private Boolean allowEdit;
	@Column
	private Boolean allowDelete;
	@Column
	private Boolean allowExport;
	public Permission() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Permission(Long id, String name, String description, Boolean allowAdd, Boolean allowEdit,
			Boolean allowDelete, Boolean allowExport) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.allowAdd = allowAdd;
		this.allowEdit = allowEdit;
		this.allowDelete = allowDelete;
		this.allowExport = allowExport;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Boolean getAllowAdd() {
		return allowAdd;
	}
	public void setAllowAdd(Boolean allowAdd) {
		this.allowAdd = allowAdd;
	}
	public Boolean getAllowEdit() {
		return allowEdit;
	}
	public void setAllowEdit(Boolean allowEdit) {
		this.allowEdit = allowEdit;
	}
	public Boolean getAllowDelete() {
		return allowDelete;
	}
	public void setAllowDelete(Boolean allowDelete) {
		this.allowDelete = allowDelete;
	}
	public Boolean getAllowExport() {
		return allowExport;
	}
	public void setAllowExport(Boolean allowExport) {
		this.allowExport = allowExport;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
