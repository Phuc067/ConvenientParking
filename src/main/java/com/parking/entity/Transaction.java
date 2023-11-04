package com.parking.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "`transactions`")

public class Transaction implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2649799223636198414L;
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;
	@Column
	@NotBlank(message = "Type shouldn't be empty")
	private String type;
	
	//ticketId
	@OneToOne
	@JoinColumn(name= "ticketId", referencedColumnName = "id")
	@JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
	private Ticket ticket;
	@Column
	
	@CreationTimestamp
	private Timestamp tDate;
	
	@Column
	private String tLog;

	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Transaction( @NotBlank(message = "Type shouldn't be empty") String type, Ticket ticket,
			Timestamp tDate, String tLog) {
		super();
		this.type = type;
		this.ticket = ticket;
		this.tDate = tDate;
		this.tLog = tLog;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public Timestamp gettDate() {
		return tDate;
	}

	public void settDate(Timestamp tDate) {
		this.tDate = tDate;
	}

	public String gettLog() {
		return tLog;
	}

	public void settLog(String tLog) {
		this.tLog = tLog;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
