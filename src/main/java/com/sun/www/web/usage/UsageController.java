package com.sun.www.web.usage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UsageController {
	
	@GetMapping("/dev/usage")
	public String usage() {
		return "usage/sample";
	}

}
