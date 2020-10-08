package org.iesalixar.eponceg.repository;

import java.util.List;

import org.iesalixar.eponceg.model.MedicalFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalFileRepository extends JpaRepository<MedicalFile,Long> {

	public List<MedicalFile> findByUserId(Long id);
	
	public List<MedicalFile> findByUserIdOrderByTitle(Long id);
	
	public List<MedicalFile> findByUserIdOrderByTitleDesc(Long id);
	
	public List<MedicalFile> findByUserIdOrderByUploadDate(Long id);
}
