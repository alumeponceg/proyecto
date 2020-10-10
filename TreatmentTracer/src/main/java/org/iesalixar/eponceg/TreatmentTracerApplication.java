package org.iesalixar.eponceg;

import java.util.List;
import java.util.Optional;

import org.iesalixar.eponceg.model.Disease;
import org.iesalixar.eponceg.repository.DiseaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TreatmentTracerApplication implements CommandLineRunner {
	
	@Autowired
	DiseaseRepository disease;

	public static void main(String[] args) {
		SpringApplication.run(TreatmentTracerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Disease glucemia = new Disease("Glucemia", "Nivel elevado de azucar en sangre","lorem ipsum dolor sit ame", "Mareo, Sobrepeso");
		disease.save(glucemia);
		Disease alzheimer = new Disease("Alzheimer", "No existe una causa conocida","lorem ipsum dolor sit ame", "Pérdida de memoria, desorientación");
		disease.save(alzheimer);
		
		List<Disease> diseases = disease.findAll();
		
		for(Disease d : diseases) {
			System.out.println(d.toString());
		}
		
		System.out.println("=====");
		
		Optional<Disease> buscado = disease.findById(1L);
		
		if (buscado.isPresent()) {
            System.out.println(buscado.get().toString());
        }
	}
	

}
