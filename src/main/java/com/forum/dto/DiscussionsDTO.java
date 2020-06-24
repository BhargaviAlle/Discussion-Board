package com.forum.dto;

import java.sql.Timestamp;

public class DiscussionsDTO {
	
	private int sno;
	private int userid;
	private String authorname;
	private String subject;
	private String message;
	private int status;
	private Timestamp created_date;
	private int discussionsCount;
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
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
	public int getDiscussionsCount() {
		return discussionsCount;
	}
	public void setDiscussionsCount(int discussionsCount) {
		this.discussionsCount = discussionsCount;
	}


	private int created_thread_id;
	
	private int reply_user_id;
	
	public int getCreated_thread_id() {
		return created_thread_id;
	}
	public void setCreated_thread_id(int created_thread_id) {
		this.created_thread_id = created_thread_id;
	}
	public int getReply_user_id() {
		return reply_user_id;
	}
	public void setReply_user_id(int reply_user_id) {
		this.reply_user_id = reply_user_id;
	}
	
	

}
