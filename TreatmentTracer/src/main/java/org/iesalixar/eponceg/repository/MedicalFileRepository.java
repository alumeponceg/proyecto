package org.iesalixar.eponceg.repository;

import java.util.List;

import org.iesalixar.eponceg.model.MedicalFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalFileRepository extends JpaRepository<MedicalFile,Long> {

	//Método para encontrar los archivos de un usuario
	public List<MedicalFile> findByUserId(Long id);
	
	//Método para ordenar los arhivos de un usuario por su titulo
	public List<MedicalFile> findByUserIdOrderByTitle(Long id);
	
	//Método para ordenar los arhivos de un usuario por su titulo descendente
	public List<MedicalFile> findByUserIdOrderByTitleDesc(Long id);
	
	//Método para ordenar los arhivos de un usuario por su fecha de subida
	public List<MedicalFile> findByUserIdOrderByUploadDate(Long id);
	
	//Método para añadir un archivo a la base de datos
	@SuppressWarnings("unchecked")
	public MedicalFile save(MedicalFile medicalFile);
	
	//Método para borrar un archivo
	public void deleteById(Long id);
}
