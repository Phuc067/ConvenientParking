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
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
	
}
