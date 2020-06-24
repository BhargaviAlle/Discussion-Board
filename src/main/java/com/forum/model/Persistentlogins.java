package com.forum.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "persistent_logins")
public class Persistentlogins {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="series")
	private String series;
	
	
	@Column(name="username" , nullable = false)
	private String username;
	
	@Column(name="token",nullable = false)
	private String token;
	
	@Column(name="last_used", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date last_used;

	
	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getLast_used() {
		return last_used;
	}

	public void setLast_used(Date last_used) {
		this.last_used = last_used;
	}

	public Persistentlogins(String series, String username, String token, Date last_used) {
		super();
		this.series = series;
		this.username = username;
		this.token = token;
		this.last_used = last_used;
	}

	public Persistentlogins() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
