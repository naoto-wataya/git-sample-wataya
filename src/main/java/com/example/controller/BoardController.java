package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.form.Post;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private ServletContext application;
	
	@RequestMapping("")
	public String index() {
		List<Post> posts = (List<Post>)application.getAttribute("posts");
		if(posts == null) {
			posts = new ArrayList<Post>();
		}
		application.setAttribute("posts", posts);
		return "msg-board";
	}

	@RequestMapping("/post")
	public String post(Post post) {
		System.out.println("post = [name="+post.getName()+",comment"+post.getComment()+"]");
		List<Post> posts = (List<Post>)application.getAttribute("posts");
		
		posts.add(0, post);
		for(Post eachPost:posts) {
			System.out.println("eachPost = [name="+eachPost.getName()+",comment"+eachPost.getComment()+"]");
		}
		
		application.setAttribute("posts", posts);		
		return "msg-board";
	}
}
