package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.form.Post;

@Controller
@RequestMapping("/application-scope")
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
}
