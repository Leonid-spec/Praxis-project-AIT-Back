package de.dental_clinic.g_43_praxis.controller;

import de.dental_clinic.g_43_praxis.domain.dto.ImageDto;
import de.dental_clinic.g_43_praxis.service.interfaces.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")

public class ImageController {
    private final ImageService imageService;

    @RequestMapping(value = "/image", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)//Long dentalServiceId, Long doctorId
    public ImageDto addImage(@RequestParam("file") MultipartFile file, @RequestParam("dentalServiceId") Long dentalServiceId, @RequestParam("doctorId") Long doctorId) {
        return imageService.addImage(file, dentalServiceId, doctorId)  ;
    }

    @GetMapping("/image/{id}")
    public ImageDto findImageById(@PathVariable Long id) {
        return imageService.findImageById(id);
    }

    @DeleteMapping("/image/{id}")
    public ImageDto deleteImage(@PathVariable Long id) {
        return imageService.deleteImage(id);
    }

    @RequestMapping(value = "/image", method = RequestMethod.PUT)
    public ImageDto updateImage(@RequestParam("file") MultipartFile file, @RequestParam("image_id") Long image_id, @RequestParam("product_id") Long product_id) {
        return imageService.updateImage(file, image_id, product_id);
    }

    @RequestMapping(value = "/image/file", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public String pushImageFile(@RequestParam("file") MultipartFile file) {
        return imageService.pushImageFileCloudinary(file);
    }

    @DeleteMapping("/image/file")
    public Boolean deleteImageFile(@RequestBody String link)
    {
        return imageService.deleteImageFileCloudinary(link);
    }

    @GetMapping("/image/file/{filename}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        // Путь к файлу на сервере
        File file = new File(filename);//GlobalVariables.getImagesPath() + filename);

        if (file.exists()) {
            Resource resource = new FileSystemResource(file);
            return ResponseEntity.ok()
                    .body(resource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}