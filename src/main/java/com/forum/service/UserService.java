package com.forum.service;

import java.util.List;

import com.forum.dto.DiscussionsDTO;
import com.forum.model.ReplyThread;
import com.forum.model.User;
import com.forum.model.UserThread;

public interface UserService {

	public User findUserByEmail(String email);

	public void saveUser(User user);
	
	public List<DiscussionsDTO> getAllTopics();

	public void delete(int id, int sno);

	public void insert(UserThread userthread, int id, String firstname);

	public void insertReply(ReplyThread replythread, int id, int sno,UserThread userthread,int userid);

	public List<DiscussionsDTO> getUserThreads(int sno);

	public List<DiscussionsDTO> getData(int sno);

}