package com.forum.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name="reply_thread")
public class ReplyThread {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="sno")
	private int sno;
	
	
	@Column(name="created_thread_id",nullable = false)
	private int created_thread_id;
	
	@Lob
	@Column(name="message",nullable = false,columnDefinition = "LONGTEXT")
	private String message;
	
	@Column(name = "status", nullable = false, columnDefinition = "int default 0") 
	private int status;
	
	private Timestamp created_date;
	
	@Column(name="reply_user_id",nullable = false)
	private int reply_user_id;

	@Column(name = "reply_to", columnDefinition = "int default 0") 
	private int reply_to;
	
	
	
	public ReplyThread() {
		super();
	}
	
	
	public ReplyThread( int created_thread_id, String message, Timestamp created_date,
			int reply_user_id) {
		super();
		
		this.created_thread_id = created_thread_id;
		this.message = message;	
		this.created_date = created_date;
		this.reply_user_id = reply_user_id;
	}




	public int getSno() {
		return sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}

	public int getCreated_thread_id() {
		return created_thread_id;
	}

	public void setCreated_thread_id(int created_thread_id) {
		this.created_thread_id = created_thread_id;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
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




	public int getReply_user_id() {
		return reply_user_id;
	}

	public void setReply_user_id(int reply_user_id) {
		this.reply_user_id = reply_user_id;
	}




	public int getReply_to() {
		return reply_to;
	}




	public void setReply_to(int reply_to) {
		this.reply_to = reply_to;
	}
	
	
}
