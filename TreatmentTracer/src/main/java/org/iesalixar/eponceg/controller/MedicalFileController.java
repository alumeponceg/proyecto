package org.iesalixar.eponceg.controller;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.iesalixar.eponceg.model.MedicalFile;
import org.iesalixar.eponceg.model.User;
import org.iesalixar.eponceg.service.DiseaseService;
import org.iesalixar.eponceg.service.MedicalFileService;
import org.iesalixar.eponceg.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MedicalFileController {
	
	final static Logger logger = LoggerFactory.getLogger(MedicalFileController.class);

	@Autowired
	private MedicalFileService medicalFiles;
	
	@Autowired
	private UserService user;
	
	@Autowired 
	private DiseaseService diseases;
	
	@GetMapping({"/user/files"})
	public String getFiles(Model model) {
		User u = this.user.userLogged();
		Set<User> usuarios = new HashSet<>();
		usuarios.add(u);
		if(this.diseases.readDiseases(usuarios).isEmpty()) {
			model.addAttribute("withoutDisease", true);
		}
		model.addAttribute("files", this.medicalFiles.ListForAnUser(u));
		logger.warn("Se ha mostrado todos los archivos médicos del usuario " + u.getId());
		return "files";
		
	}
	@RequestMapping(value = { "/user/uploadFile" }, method = { RequestMethod.POST, RequestMethod.PUT,  RequestMethod.GET})
	public String uploadFile(@RequestParam(value = "nameFile") String title ,@RequestParam(value = "desFile") String description, @RequestParam(value = "pic") String url,  Model model) {
		
		MedicalFile file = new MedicalFile(title,description, url);
		Date date= new Date();
		file.setUploadDate(date);
		User u = this.user.userLogged();
		file.setUserId(u);
		this.medicalFiles.uploadFile(file);
		
		logger.warn("Se ha subido un nuevo archivo médico al perfil del usuario " + u.getId());
		return "redirect:/user/files";
	}
	
	@RequestMapping(value = { "/removeFile" }, method = { RequestMethod.POST, RequestMethod.DELETE })
	public String deleteTreatment(@RequestParam(value = "id") String id, Model model) {
		this.medicalFiles.deleteFile(Long.parseLong(id));
		logger.warn("Se ha eliminado el archivo con id : " + id);
		return "redirect:/user/files";
	}
	
}
