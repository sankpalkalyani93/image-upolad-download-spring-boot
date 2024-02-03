package com.example.PetStoreImageUploadDownload;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PetService {
	
	@Autowired
	private PetRepository petRepository;

	public void uploadImage(Long id, MultipartFile file){
		Pet2 pet = petRepository.getById(id);
		
		try {
			pet.setImage(file.getBytes());
			petRepository.save(pet);
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public Pet2 getPetById(Long id) {
		return petRepository.getById(id);
	}
}
