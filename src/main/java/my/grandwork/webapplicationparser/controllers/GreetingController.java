package my.grandwork.webapplicationparser.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GreetingController {

	@GetMapping("/greeting")
	@ResponseBody
	public String greeting(@RequestParam(name="name", required=false, defaultValue="or not") String name, Model model) {
		return "greeting ... " + name;
	}

}