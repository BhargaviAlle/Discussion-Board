package com.forum.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.forum.dto.DiscussionsDTO;
import com.forum.model.ReplyThread;
import com.forum.model.User;
import com.forum.model.UserThread;
import com.forum.repository.ReplyThreadRepository;
import com.forum.repository.UserRepository;
import com.forum.repository.UserThreadRepository;
import com.forum.service.UserService;


@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserThreadRepository userthreadRepository;
	
	@Autowired
	private ReplyThreadRepository replythreadrepository;

	@RequestMapping(value= {"/", "/login"}, method=RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView model = new ModelAndView();

		model.setViewName("user/login");
		return model;
	}

	@RequestMapping(value= {"/signup"}, method=RequestMethod.GET)
	public ModelAndView signup() {
		ModelAndView model = new ModelAndView();
		User user = new User();
		model.addObject("user", user);
		model.setViewName("user/signup");

		return model;
	}

	@RequestMapping(value= {"/signup"}, method=RequestMethod.POST)
	public ModelAndView createUser(@Valid User user, BindingResult bindingResult) {
		ModelAndView model = new ModelAndView();
		User userExists = userService.findUserByEmail(user.getEmail());

		if(userExists != null) {
			bindingResult.rejectValue("email", "error.user", "This email already exists!");
		}
		if(bindingResult.hasErrors()) {
			model.setViewName("user/signup");
		} else {
			userService.saveUser(user);
			model.addObject("msg", "User has been registered successfully!");
			model.addObject("user", new User());
			model.setViewName("user/signup");
		}

		return model;
	}

	@RequestMapping(value= {"/home/home"}, method=RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView model = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		System.out.println(userService.findUserByEmail(auth.getName()));
		model.addObject("userName", user.getFirstname() + " " + user.getLastname());
		model.addObject("userid", user.getId());
		//System.out.println(user.getId());
		model.setViewName("home/home");
		return model;
	}

	@RequestMapping(value= {"/create"}, method=RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView model = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());

		model.addObject("userName", user.getFirstname() + " " + user.getLastname());
		model.setViewName("home/create");
		return model;
	}

	
	@RequestMapping(value= {"/createThread"}, method=RequestMethod.POST) 
	public ModelAndView createThread(UserThread userthread,User user) { 
	ModelAndView model  = new ModelAndView();
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	User user1 = userService.findUserByEmail(auth.getName());
	//String authorname=user1.getFirstname()+" "+user1.getLastname();
	model.addObject("userName", user1.getFirstname() + " " + user1.getLastname());
	model.addObject("userid", user1.getId());
	model.setViewName("home/home");
	userService.insert(userthread,user1.getId(),user1.getFirstname()+" "+user1.getLastname());
	  
	
	return model; 
	}
	
	
	@RequestMapping("/lectureforummodel")
	public List<DiscussionsDTO> getAllTopics(){	
		return userService.getAllTopics();
	}

	@RequestMapping("/lectureforummodel/{sno}")
	public List<DiscussionsDTO> getUserThreads(@PathVariable("sno") int sno){	
		System.out.println(userthreadRepository.getUserThreads(sno));
		return userService.getUserThreads(sno);
	}

	@RequestMapping(value= {"/edit/{sno}"}, method=RequestMethod.GET)
	public ModelAndView edit(@PathVariable("sno") int sno,UserThread userthread) {
		ModelAndView model = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		model.addObject("userName", user.getFirstname() + " " + user.getLastname());
		model.addObject("sno",sno);
		model.addObject("userthread", userthreadRepository.findById(sno).orElse(null));
		model.setViewName("home/edit");
		return model;

	}
	
	@RequestMapping(value= {"/updateThread/{sno}"}, method=RequestMethod.POST) 
	public ModelAndView updateThread(@PathVariable("sno") int sno, UserThread userthread) { 
	ModelAndView model  = new ModelAndView();
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	User user = userService.findUserByEmail(auth.getName());
	model.addObject("userName", user.getFirstname() + " " + user.getLastname());
	model.addObject("userid", user.getId());
	int edit = userthreadRepository.updateQuery(sno, userthread.getSubject(),userthread.getMessage());
    System.out.println("Update count: " + edit);
	model.setViewName("home/home");
	return model; 
	}

	@RequestMapping(value= {"/deleteThread/{sno}"}, method=RequestMethod.GET)
	public ModelAndView deleteThread(@PathVariable("sno") int sno) {
		ModelAndView model  = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		model.addObject("userName", user.getFirstname() + " " + user.getLastname());
		model.addObject("userid", user.getId());		
		userthreadRepository.deleteQuery(sno);
		model.setViewName("home/home");
		return model; 
	}
	
	@RequestMapping(value = "/reply/{sno}",method = RequestMethod.GET)
	public ModelAndView getAllThreads(@PathVariable("sno") int sno,ReplyThread replythread){
		ModelAndView model  = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		model.addObject("userName", user.getFirstname() + " " + user.getLastname());
		
		model.addObject("sno",sno);
		model.addObject("userthread", userthreadRepository.findById(sno).orElse(null));

		model.setViewName("home/reply");
		return model;
	}
	
	@RequestMapping(value = "/getAllRepliedThreads/{sno}",method = RequestMethod.GET)
	public List<DiscussionsDTO> getAllRepliedThreads(@PathVariable("sno") int sno,ReplyThread replythread){
		ModelAndView model= new ModelAndView();
		model.addObject("sno", sno);
		return userService.getData(sno);
	}

	@RequestMapping(value="/insertReply/{sno}",method = RequestMethod.POST)
	public ModelAndView insertReply(@PathVariable("sno") int sno,@RequestParam("replyto") int replyto,ReplyThread replythread,UserThread userthread) {
		ModelAndView model  = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		model.addObject("userName", user.getFirstname() + " " + user.getLastname());
		System.out.println(replyto);
		model.addObject("sno",sno);
		model.addObject("userid",user.getId());
		model.addObject("userthread", userthreadRepository.findById(sno).orElse(null));
		userService.insertReply(replythread,user.getId(),sno,userthread,replyto);
				
		model.setViewName("home/reply");
		System.out.println("hello");
		return model;
		
	}
	
	@RequestMapping(value= {"/views"}, method=RequestMethod.GET)
	public ModelAndView views() {
		ModelAndView model = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		System.out.println(userService.findUserByEmail(auth.getName()));
		model.addObject("userName", user.getFirstname() + " " + user.getLastname());
		model.setViewName("home/views");
		return model;
	}

	
	@RequestMapping(value = "/multipleReplies/{sno}",method = RequestMethod.GET)
	public ModelAndView multipleReplies(@PathVariable("sno") int sno,ReplyThread replythread){
		ModelAndView model = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		System.out.println("Enter into multiple");
		model.addObject("userName", user.getFirstname() + " " + user.getLastname());
		model.setViewName("home/mutiplereply");
		return model;
	}
	
	@RequestMapping(value= {"/access_denied"}, method=RequestMethod.GET)
	public ModelAndView accessDenied() {
		ModelAndView model = new ModelAndView();
		model.setViewName("errors/access_denied");
		return model;
	}

}