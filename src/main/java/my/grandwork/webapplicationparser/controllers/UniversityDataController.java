package my.grandwork.webapplicationparser.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import my.grandwork.UniversitiesParser.Data.enums.InfoTypeClassification;
import my.grandwork.UniversitiesParser.Data.enums.StudyGrade;
import my.grandwork.webapplicationparser.dao.DatabaseInformationWorker;
import my.grandwork.webapplicationparser.dao.dataWrappers.UniversityName;
import my.grandwork.webapplicationparser.service.UniversityDataCore.UniversityDataModelFiller;

@Controller
public class UniversityDataController {

    @Autowired
	private DatabaseInformationWorker db_worker;

    @GetMapping("/university/{u_id}") 
	public String getUniveristyByPath(@PathVariable int u_id, Model model) {	
		if (fillUniversityDataChoosingModel(u_id, model) == false) return "ulist";
		return "universityDataChoosing";
	}

	@GetMapping("/university/{u_id}/info") 
	public String getInfoAboutUniversity(@PathVariable int u_id, 
										@RequestParam(name="infoType", required = false) String infoType, 
										@RequestParam(name="gradeType",required = false) String gradeType, 
										Model model) 
    {
        if (infoType == null | gradeType == null) return "redirect:/university/" + u_id;
		if (fillUniversityDataChoosingModel(u_id, model) == false) return "ulist";
        
        model.addAttribute("path", chooseDataContainerByInfoType(infoType));
        new UniversityDataModelFiller(db_worker).fillUniverDataByInfoAndGradeType(u_id, infoType, gradeType, model);
		return "universityInfo";
	}

    private String chooseDataContainerByInfoType(String infoTypeStr) {
        String path = "parts/university_data_containers/";
        return path + infoTypeStr.toLowerCase();
    }

    private boolean fillUniversityDataChoosingModel(int u_id, Model model) {
        UniversityName uname = db_worker.getUniversityById(u_id);
		if (uname == null) return false;
		model.addAttribute("name", uname);
		model.addAttribute("contentType", InfoTypeClassification.values());
		model.addAttribute("type", InfoTypeClassification.class);
		model.addAttribute("grades", StudyGrade.values());
		model.addAttribute("grade", StudyGrade.class);

        return true;
    }
}
