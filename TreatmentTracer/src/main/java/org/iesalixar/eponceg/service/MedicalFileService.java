package org.iesalixar.eponceg.service;

import java.util.List;

import org.iesalixar.eponceg.model.MedicalFile;
import org.iesalixar.eponceg.model.User;
import org.iesalixar.eponceg.repository.MedicalFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicalFileService {

	@Autowired
	private MedicalFileRepository medicalFile;
	
	public List<MedicalFile> ListForAnUser(User id){
		return this.medicalFile.findByUserId(id);
	}
	
	public List<MedicalFile> ListForAnUserOrdeByTitle(Long id){
		return this.medicalFile.findByUserIdOrderByTitle(id);
	}
	
	public List<MedicalFile> ListForAnUserOrdeByTitleDesc(Long id){
		return this.medicalFile.findByUserIdOrderByTitleDesc(id);
	}
	
	public List<MedicalFile> ListForAnUserOrderByDate(Long id){
		return this.medicalFile.findByUserIdOrderByUploadDate(id);
	}
	
	public MedicalFile uploadFile(MedicalFile medicalFile) {
		return this.medicalFile.save(medicalFile);
	}
	
	public void deleteFile(Long id) {
		this.medicalFile.deleteById(id);
	}
	
}
