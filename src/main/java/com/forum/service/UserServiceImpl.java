package com.forum.service;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.forum.dto.DiscussionsDTO;
import com.forum.model.ReplyThread;
import com.forum.model.Role;
import com.forum.model.User;
import com.forum.model.UserThread;
import com.forum.repository.ReplyThreadRepository;
import com.forum.repository.RoleRespository;
import com.forum.repository.UserRepository;
import com.forum.repository.UserThreadRepository;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRespository roleRespository;

	@Autowired
	private UserThreadRepository userthreadrepository;
	
	@Autowired
	private ReplyThreadRepository replythreadrepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public void saveUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setActive(1);
		Role userRole = roleRespository.findByRole("ADMIN");
		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		userRepository.save(user);
	}

	@Override
	public List<DiscussionsDTO> getAllTopics() {
		List<Object[]> forum = userthreadrepository.getAllTopics();
		List<DiscussionsDTO> responseList = new ArrayList<DiscussionsDTO>();
		for(Object[] topicArray : forum) {
			Integer count = ((BigInteger) topicArray[7]).intValue();
			System.out.println("TopicArray"+Arrays.toString(topicArray));
			DiscussionsDTO obj = new DiscussionsDTO();
			obj.setSubject(String.valueOf(topicArray[0]));
			obj.setMessage(String.valueOf(topicArray[1]));
			obj.setSno((int) topicArray[2]);
			obj.setUserid((int) topicArray[3]);
			obj.setAuthorname(String.valueOf(topicArray[4]));
			obj.setCreated_date((Timestamp) topicArray[5]);
			obj.setStatus((int) topicArray[6]);
			obj.setDiscussionsCount(count);
			responseList.add(obj);
		}
		
		// forum.forEach(System.out::println);
		return responseList;
	}
	
	@Override
	public List<DiscussionsDTO> getUserThreads(int sno) {
		
		List<Object[]> forum = userthreadrepository.getUserThreads(sno);
		List<DiscussionsDTO> responseList = new ArrayList<DiscussionsDTO>();
		for(Object[] topicArray : forum) {
			Integer count = ((BigInteger) topicArray[7]).intValue();
		//	System.out.println("TopicArray"+Arrays.toString(topicArray));
			DiscussionsDTO obj = new DiscussionsDTO();
			obj.setSubject(String.valueOf(topicArray[0]));
			obj.setMessage(String.valueOf(topicArray[1]));
			obj.setSno((int) topicArray[2]);
			obj.setUserid((int) topicArray[3]);
			obj.setAuthorname(String.valueOf(topicArray[4]));
			obj.setCreated_date((Timestamp) topicArray[5]);
			obj.setStatus((int) topicArray[6]);
			obj.setDiscussionsCount(count);
			responseList.add(obj);
		}
		return responseList;
		
	}

	@Override
	public List<DiscussionsDTO> getData(int sno) {
		List<Object[]> forum = replythreadrepository.getData(sno);
		List<DiscussionsDTO> responseList = new ArrayList<DiscussionsDTO>();
		for(Object[] topicArray : forum) {
			System.out.println("TopicArray"+Arrays.toString(topicArray));
			DiscussionsDTO obj = new DiscussionsDTO();
			
			// sno, created_date, created_thread_id, message, reply_user_id, status
			obj.setSno((int) topicArray[0]);
			obj.setCreated_date((Timestamp) topicArray[1]);
			obj.setCreated_thread_id((int) topicArray[2]);
			obj.setMessage(String.valueOf(topicArray[3]));
			obj.setReply_user_id((int) (topicArray[4]));
			obj.setStatus((int) topicArray[5]);
			obj.setAuthorname((String) topicArray[6]+" "+topicArray[7]);
			responseList.add(obj);
		}
		return responseList;
	}

	@Override
	public void insert(UserThread userthread, int id, String authorname) {
		Date date = new Date();
		// TODO Auto-generated method stub
		userthread.setCreated_date(new Timestamp(date.getTime()));
		userthread.setSubject(userthread.getSubject());
		userthread.setMessage(userthread.getMessage());
		userthread.setUserid(id);
		userthread.setAuthorname(authorname);
		userthreadrepository.save(userthread);
	}
	
	

	@Override
	public void delete(int id, int sno) {
		userthreadrepository.delete(id, sno);
	}

	@Override
	public void insertReply(ReplyThread replythread, int id, int sno,UserThread userthread,int userid) {
		Date date = new Date();
		
		replythread.setCreated_date(new Timestamp(date.getTime()));
		replythread.setReply_user_id(sno);
		replythread.setMessage(userthread.getMessage());
		replythread.setCreated_thread_id(id);
		replythread.setReply_to(userid);
		//System.out.println(userid);
		replythreadrepository.insert(sno,userthread.getMessage(),id,userid,new Timestamp(date.getTime()));		
	}
	
}