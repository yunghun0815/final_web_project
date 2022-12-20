package com.mycompany.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.mycompany.webapp.service.IBatchService;

@Controller
public class MainController {

	@Autowired
	IBatchService batchService;

	@GetMapping("/")
	public String main(Model model) {
		
		model.addAttribute("batchGroupList", batchService.getBatchGroupList());
		model.addAttribute("batchAppList", batchService.getBatchAppList());

		return "home";
	}
}
