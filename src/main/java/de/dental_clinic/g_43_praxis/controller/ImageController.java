package de.dental_clinic.g_43_praxis.controller;

import de.dental_clinic.g_43_praxis.domain.entity.Image;
import de.dental_clinic.g_43_praxis.service.interfaces.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@RequestMapping("/api/image")
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping
    public ResponseEntity<Image> addImage(@RequestParam("file") MultipartFile file) {
        try {
            String path = "https://cloud.com/" + file.getOriginalFilename();
            Image savedImage = imageService.addImage(file, path);
            return ResponseEntity.ok(savedImage);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteImage(@PathVariable Long id) {
        try {
            imageService.deleteImage(id);
            return ResponseEntity.ok("Image deleted successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}






//@RestController
//@RequestMapping("/api/image")
//@RequiredArgsConstructor
//public class ImageController {
//
//    private final ImageService imageService;
//
//    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
////    @PreAuthorize("hasRole('ROLE_USER')")
//    public ResponseEntity<ImageJson> uploadImage(@RequestParam("file") MultipartFile file) {
//        String imageUrl = imageService.uploadImage(file);
//        Image image = new Image();
//        image.setPath(imageUrl);
//        Image savedImage = imageService.saveImage(image);
//        return ResponseEntity.ok(new ImageJson(savedImage.getId(), savedImage.getPath()));
//    }
//
//    @DeleteMapping("/{id}")
////    @PreAuthorize("hasRole('ROLE_USER')")
//    public ResponseEntity<?> deleteImage(@PathVariable Long id) {
//        imageService.deleteImage(id);
//        return ResponseEntity.ok().build();
//    }
//}
