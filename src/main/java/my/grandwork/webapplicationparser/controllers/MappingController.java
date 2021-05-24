package my.grandwork.webapplicationparser.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import my.grandwork.UniversitiesParser.Data.enums.InfoTypeClassification;
import my.grandwork.UniversitiesParser.Data.enums.StudyGrade;
import my.grandwork.webapplicationparser.dao.DatabaseInformationWorker;
import my.grandwork.webapplicationparser.dao.dataWrappers.UniversityName;
import my.grandwork.webapplicationparser.service.SearchEngine;

@Controller
public class MappingController {

	@Autowired
	private DatabaseInformationWorker db_worker;

	@GetMapping("/")
	public ModelAndView startPage(ModelMap model) {
        return new ModelAndView("redirect:/greeting", model);
	}

	@GetMapping("/jsonGreeting")
	@ResponseBody
	public String jsonGreeting(@RequestParam(name="name", required=false, defaultValue="or not") String name, Model model) {
		return "greeting ... " + name;
	}

	@GetMapping("/greeting")
	public String greeting(Model model) {
		return "greeting";
	}

	@GetMapping("/ulist")
	public String ulist(Model model) {
		List<UniversityName> names = db_worker.getUniversitiesNamesList();
		model.addAttribute("names", names);
		model.addAttribute("name", new UniversityName());
		return "ulist";
	}

	@GetMapping("/university/{u_id}") 
	public String getUniveristyByPath(@PathVariable int u_id, Model model) {
		UniversityName uname = db_worker.getUniversityById(u_id);
		if (uname == null) return "404";

		model.addAttribute("name", uname);
		model.addAttribute("contentType", InfoTypeClassification.values());
		model.addAttribute("type", InfoTypeClassification.class);
		model.addAttribute("grades", StudyGrade.values());
		model.addAttribute("grade", StudyGrade.class);

		return "universityInfo";
	}

	@GetMapping("/search")
	public String searchUniversity(@RequestParam String uname, Model model) {
		SearchEngine enj = new SearchEngine(db_worker);
		int id = enj.findNameIdInDb(uname);
		if (id == -1) return "redirect:/ulist";
		return "redirect:/university/" + id;
	}
}
