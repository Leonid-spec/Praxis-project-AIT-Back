package de.dental_clinic.g_43_praxis.service.interfaces;

import de.dental_clinic.g_43_praxis.domain.dto.ImageDto;
import de.dental_clinic.g_43_praxis.domain.entity.Image;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    ImageDto addImage(MultipartFile file, Long dentalServiceId, Long doctorId);//Long
    String pushImageFile(MultipartFile file);
    Boolean deleteImageFile(String link);
    String updateImageFileLink(String newLink, String lastLink);

    //*
    ImageDto findImageById(Long imageId);
    ImageDto deleteImage(Long id);
    ImageDto updateImage(MultipartFile file, Long image_id);
    Image store(Image image);
    Boolean updateImageFile(MultipartFile file, String name);


    String updateImageFileLinkCloudinary(String newLink, String lastLink);
    String updateImageFileLinkFromDiskSpace(String newLink, String lastLink);


    String pushImageFileCloudinary(MultipartFile file);
    Boolean deleteImageFileCloudinary(String link);
    String pushImageFileFromDiskSpace(MultipartFile file);
    Boolean deleteImageFileFromDiskSpace(String link);
//*/

}