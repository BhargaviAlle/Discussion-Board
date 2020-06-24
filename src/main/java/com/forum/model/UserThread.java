package com.forum.model;


import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "userthread")
public class UserThread {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="sno")
	private int sno;

	@Column(name = "userid")
	private int userid;
	
	@Column(name = "authorname")
	private String authorname;

	@Column(name = "subject")
	private String subject;
	
	@Column(name = "message")
	private String message;
	
	@Column(name = "status", nullable = false, columnDefinition = "int default 0") 
	private int status;
	
//	@Column(name = "created_date", columnDefinition="DATETIME",nullable = false)
	private Timestamp created_date;
	

	public UserThread() {
		super();
	}


	public UserThread(int userid, String authorname, String subject, String message, Timestamp created_date) {
		super();
		this.userid = userid;
		this.authorname = authorname;
		this.subject = subject;
		this.message = message;
		this.created_date = created_date;
	}


	public int getSno() {
		return sno;
	}


	public void setSno(int sno) {
		this.sno = sno;
	}


	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	public int getUserid() {
		return userid;
	}


	public void setUserid(int userid) {
		this.userid = userid;
	}
	
	public String getAuthorname() {
		return authorname;
	}


	public void setAuthorname(String authorname) {
		this.authorname = authorname;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}


	public Timestamp getCreated_date() {
		return created_date;
	}


	public void setCreated_date(Timestamp created_date) {
		this.created_date = created_date;
	}


	


}
