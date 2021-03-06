package com.spring.app.spring_web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WelcomeController {

	@GetMapping("/welcome")
	public String welcome(@RequestParam(name="name", required = true) String name, Model model) {
		model.addAttribute("name", name);
		return "index";
	}

	/*
	Se 'name' fosse opcional e tivesse o valor default 'fellow visitor'
	@GetMapping("/welcome")
	public String welcome(@RequestParam(name="name", required = false, defaultValue = "fellow visitor") String name, Model model) {
		model.addAttribute("name", name);
		return "index";
	}
	*/
}
