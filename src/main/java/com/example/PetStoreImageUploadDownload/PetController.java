package com.example.PetStoreImageUploadDownload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/pets")
public class PetController {

	@Autowired
	private PetService petService;
	
	@PostMapping("/{id}/upload-image")
	public ResponseEntity<String> handleImageUpload(@PathVariable Long id, @RequestBody MultipartFile file){
		petService.uploadImage(id, file);
		return ResponseEntity.ok("Image Upload successful..!");
	}
	
	@GetMapping("/{id}/download-image")
	public ResponseEntity<byte[]> handleImageDownload(@PathVariable Long id){
		
		Pet2 pet = petService.getPetById(id);
		byte[] image = pet.getImage();
		System.out.println("pet name : " + pet.getName());
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_JPEG);
		System.out.println("Image available .................");
		return new ResponseEntity<>(image, HttpStatus.OK);
	}
}
