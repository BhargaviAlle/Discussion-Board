package com.forum.repository;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.forum.model.ReplyThread;

@Repository("replythreadrepository")
public interface ReplyThreadRepository<UserThread, Integer extends Serializable> extends JpaRepository<ReplyThread, Integer> {

	
	@Transactional
	@Modifying
	@Query(value="insert into reply_thread (created_thread_id, message, reply_user_id,reply_to,created_date ) values (:sno,:message,:id,:userid,:created_date) ",nativeQuery = true)
	void insert(@Param("sno") int sno, @Param("message") String message, @Param("id") int id,@Param("userid") int userid,@Param("created_date") Timestamp created_date);

	//@Query(value= "select sno, created_date, created_thread_id, message, reply_user_id, status from reply_thread where created_thread_id=:sno", nativeQuery = true)
	
	//@Query(value="select sno, created_date, created_thread_id, message, reply_user_id, status,firstname,lastname from reply_thread a left join user b on a.reply_user_id=b.id where created_thread_id=:sno",nativeQuery = true)
	
	@Query(value="select sno, created_date, created_thread_id, message, reply_user_id, status,firstname,lastname,reply_to from reply_thread a left join user b on a.reply_user_id=b.id where created_thread_id=:sno group by a.sno,a.reply_to,a.reply_user_id,a.created_thread_id",nativeQuery = true)
	List<Object[]> getData(@Param("sno") int sno);

}