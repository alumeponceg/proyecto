package org.iesalixar.eponceg.controller;

import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.iesalixar.eponceg.model.MedicalFile;
import org.iesalixar.eponceg.model.User;
import org.iesalixar.eponceg.repository.UserRepository;
import org.iesalixar.eponceg.service.DiseaseService;
import org.iesalixar.eponceg.service.MedicalFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MedicalFileController {

	@Autowired
	private MedicalFileService medicalFiles;
	
	@Autowired
	private UserRepository users;
	
	@Autowired 
	private DiseaseService diseases;
	
	@GetMapping({"/files"})
	public String getFiles(Model model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = null;
		if (principal instanceof UserDetails) {
		  userDetails = (UserDetails) principal;
		}
		String email = userDetails.getUsername();
		Optional<User> u = this.users.findByEmail(email);
		Set<User> usuarios = new HashSet<>();
		usuarios.add(u.get());
		if(this.diseases.readDiseases(usuarios).isEmpty()) {
			model.addAttribute("withoutDisease", true);
		}
		model.addAttribute("files", this.medicalFiles.ListForAnUser(u.get()));
		return "files";
		
	}
	@RequestMapping(value = { "/uploadFile" }, method = { RequestMethod.POST, RequestMethod.PUT,  RequestMethod.GET})
	public String uploadFile(@RequestParam(value = "nameFile") String title ,@RequestParam(value = "desFile") String description, @RequestParam(value = "pic") String url,  Model model) {
		
		MedicalFile file = new MedicalFile(title,description, url);
		Date date= new Date();
		file.setUploadDate(date);
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = null;
		if (principal instanceof UserDetails) {
		  userDetails = (UserDetails) principal;
		}
		String email = userDetails.getUsername();
		Optional<User> u = this.users.findByEmail(email);
		
		file.setUserId(u.get());
		this.medicalFiles.uploadFile(file);
		return "redirect:/files";
	}
	
	@RequestMapping(value = { "/removeFile" }, method = { RequestMethod.POST, RequestMethod.DELETE })
	public String deleteTreatment(@RequestParam(value = "id") String id, Model model) {
		this.medicalFiles.deleteFile(Long.parseLong(id));
		return "redirect:/files";
	}
	
}
