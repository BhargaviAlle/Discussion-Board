package com.forum.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.forum.model.UserThread;


@Repository("userthreadRepository")
public interface UserThreadRepository<User, Long extends Serializable> extends JpaRepository<UserThread, Integer> {
	
	
	//public static final ReplyThread replyThread = new ReplyThread();
	//select subject,message,sno FROM userthread 
	//SELECT subject,message,sno,firstname name FROM userthread a, user l where a.userid=l.id
	@Query(value="select subject,message,sno,userid,authorname,created_date,status,ifnull(discussionsCount,0) discussionsCount FROM userthread a left join (select ifnull(count(*),0) discussionsCount,created_thread_id from reply_thread group by created_thread_id) as b on a.sno=b.created_thread_id WHERE STATUS=0", nativeQuery=true)
	List<Object[]> getAllTopics();

	@Query(value = "UPDATE TABLE userthread SET STATUS=1 WHERE SNO=? AND USERID=?", nativeQuery = true)
	void delete(int id, int sno);

	@Transactional
	@Modifying
	@Query(value = "UPDATE userthread SET subject = :subject, message = :message  WHERE sno=:sno ", nativeQuery = true)	
	int updateQuery(@Param("sno") int sno, @Param("subject") String subject, @Param("message") String message);

	@Transactional
	@Modifying
	@Query(value="UPDATE userthread SET STATUS=1 WHERE sno=:sno", nativeQuery = true)
	int deleteQuery(@Param("sno") int sno);

	@Query(value = "SELECT subject FROM userthread where sno=:sno",nativeQuery = true)
	String getAllThreads(@Param("sno") int sno);

	//@Query(value="select subject,message,sno,userid,authorname,created_date,status FROM userthread WHERE STATUS=0 AND userid=:sno", nativeQuery=true)
	@Query(value="select subject,message,sno,userid,authorname,created_date,status,ifnull(discussionsCount,0) discussionsCount FROM userthread a left join (select ifnull(count(*),0) discussionsCount,created_thread_id from reply_thread group by created_thread_id) as b on a.sno=b.created_thread_id WHERE STATUS=0 AND userid=:sno",nativeQuery = true)
	List<Object[]> getUserThreads(@Param("sno") int sno);

	
	  
}