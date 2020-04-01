package com.example;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/jdbc")
public class JdbcController {
	@Autowired
	private NamedParameterJdbcTemplate template;
	@Autowired
	private JdbcTemplate jdbcTemplage;
	
	@RequestMapping("/execute")
	public String execute(Model model) {
		String sql = "SELECT count(*) FROM employees WHERE id = :firstId OR id = :secondId";
		SqlParameterSource param = new MapSqlParameterSource().addValue("firstId",1).addValue("secondId",3);
		Integer result = template.queryForObject(sql, param, Integer.class);
		System.out.println("result = " + result);
		model.addAttribute("model", model);
		return "finished";
	}
	
	@RequestMapping("/developments")
	public String developments() {
		String sql = "SELECT count(*) FROM developments WHERE development_name = :firstId OR development_name = :secondId";
		SqlParameterSource param = new MapSqlParameterSource().addValue("firstId","総務部").addValue("secondId","技術部");
		Integer result = template.queryForObject(sql, param, Integer.class);
		System.out.println("result = " + result);
		return "finished";
	}
	
	@RequestMapping("/employees")
	public String employees() {
		String sql = "SELECT * FROM employees";
		List<Map<String,Object>> resultList = jdbcTemplage.queryForList(sql);
		for(Map result : resultList) {
			System.out.println((String)result.get("NAME"));
		}
		return "finished";
	}
}
