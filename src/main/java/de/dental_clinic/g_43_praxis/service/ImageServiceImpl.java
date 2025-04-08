package de.dental_clinic.g_43_praxis.service;


import de.dental_clinic.g_43_praxis.controller.ImageController;
import de.dental_clinic.g_43_praxis.domain.dto.ImageDto;
import de.dental_clinic.g_43_praxis.domain.entity.DentalService;
import de.dental_clinic.g_43_praxis.domain.entity.Doctor;
import de.dental_clinic.g_43_praxis.domain.entity.Image;
import de.dental_clinic.g_43_praxis.exception_handling.exceptions.DentalServiceNotFoundException;
import de.dental_clinic.g_43_praxis.exception_handling.exceptions.DoctorNotFoundException;
import de.dental_clinic.g_43_praxis.exception_handling.exceptions.FailedDependencyException;
import de.dental_clinic.g_43_praxis.exception_handling.exceptions.ImageNotFoundException;
import de.dental_clinic.g_43_praxis.repository.DentalServiceRepository;
import de.dental_clinic.g_43_praxis.repository.DoctorRepository;
import de.dental_clinic.g_43_praxis.repository.ImageRepository;
import de.dental_clinic.g_43_praxis.service.interfaces.ImageService;
import de.dental_clinic.g_43_praxis.util.CloudinaryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final DentalServiceRepository dentalServiceRepository;
    private final DoctorRepository doctorRepository;
    private ImageController imageController;

    private final ImageRepository imageRepository;
    private final ModelMapper modelMapper;

    private final CloudinaryService cloudinaryService;

//*

    @Override
    public String pushImageFile(MultipartFile file) {
        return pushImageFileCloudinary(file);
        //return pushImageFileFromDiskSpace(file);
    }

    @Override
    public Boolean deleteImageFile(String link) {
        return deleteImageFileCloudinary(link);
        //return  deleteImageFileFromDiskSpace(link);
    }


    @Transactional
    @Override
    public ImageDto addImage(MultipartFile file, Long dentalServiceId, Long doctorId) {
        Image image = new Image();
        image.setId(null);
        image.setDoctor(null);
        image.setDentalService(null);
        image.setPath(pushImageFile(file));
        image = imageRepository.saveAndFlush(image);
        if(image != null) {

            if(dentalServiceId != 0)
            {
                DentalService dentalService = dentalServiceRepository.findById(dentalServiceId).orElseThrow(() -> new DentalServiceNotFoundException("not found" + dentalServiceId));
                dentalService.getImages().add(image);
                dentalServiceRepository.save(dentalService);
                image.setDentalService(dentalService);
                imageRepository.save(image);
            }
            if(doctorId != 0)
            {
                Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(() -> new DoctorNotFoundException("not found" + doctorId));
                doctor.getImages().add(image);
                doctorRepository.save(doctor);
                image.setDoctor(doctor);
                imageRepository.save(image);
            }
            return modelMapper.map(image, ImageDto.class);
        }
        return null;
    }

    @Override
    public ImageDto findImageById(Long imageId) {
        Image image = imageRepository.findById(imageId).orElseThrow(() -> new ImageNotFoundException(   imageId   ));
        return modelMapper.map(image, ImageDto.class);
    }

    @Transactional
    @Override
    public ImageDto deleteImage(Long imageId) {
        Image image = imageRepository.findById(imageId).orElseThrow(() -> new ImageNotFoundException(   imageId   ));
        String temp = image.getPath();
        imageRepository.delete(image);
        if(image.getDoctor() != null) { image.getDoctor().getImages().remove(image); }
        if(image.getDentalService() != null) { image.getDentalService().getImages().remove(image);  }
        deleteImageFile(temp);
        return modelMapper.map(image, ImageDto.class);
    }

    @Transactional
    @Override
    public ImageDto updateImage(MultipartFile file, Long image_id) {
        Image image = imageRepository.findById(image_id).orElseThrow(() -> new ImageNotFoundException( image_id  ));
        if(!(file.isEmpty())){
            String temp = image.getPath();
            image.setPath(pushImageFile(file));
            deleteImageFile(temp);
        }
        return modelMapper.map(imageRepository.save(image), ImageDto.class);
    }

    @Transactional
    @Override
    public Image store(Image image) {
        return imageRepository.saveAndFlush(image);
    }



    //@Transactional
    @Override
    public String pushImageFileCloudinary(MultipartFile file) {
        try {
            return cloudinaryService.uploadImage(file).replace("https://", "").replace("http://", "");
        } catch (IOException e) {
            throw new FailedDependencyException("file is not saved");
        }
    }


    @Override
    public String pushImageFileFromDiskSpace(MultipartFile file) {
        String fileName = String.valueOf(System.currentTimeMillis())
                + String.valueOf(System.nanoTime())
                + String.valueOf(new Random().nextInt(1000) + 1000);
        String filePath = fileName + "." + FilenameUtils.getExtension(file.getOriginalFilename());//GlobalVariables.getPathsPath() + fileName + "." + FilenameUtils.getExtension(file.getOriginalFilename());

        try {
            // Сохраняем файл на сервере
            file.transferTo(new File(filePath))   ;
        } catch (IOException e) {
            throw new FailedDependencyException("file is not saved");
        }
        return filePath;
    }

    @Override
    public Boolean deleteImageFileFromDiskSpace(String name) {
        Path path = Paths.get(name);
        try {
            // Проверяем, существует ли файл
            if (Files.exists(path)) {
                Files.delete(path);
                System.out.println("The file:  " +  path + "is deleted");
                return true;
            } else {
                System.out.println("The file:  " +  path + "is not exist");
                return false;
            }
        } catch (IOException e) {
            System.out.println("error, the file: " +  path + "is not deleted...  " + e.getMessage());
            return false;
        }
    }

    @Override
    public Boolean deleteImageFileCloudinary(String name) {
        String temp;
        try {
            temp = name.substring(name.lastIndexOf('/') + 1, name.lastIndexOf('.'));
        } catch (Exception e) {
            System.out.println("Error deleting image: " + e.getMessage());//
            return false;
        }

        if(temp.isEmpty() || temp.isBlank() ) {
            try {
                return (cloudinaryService.deleteImage("")).equals("ok");
            } catch (Exception e) {
                System.out.println("Error deleting image: " + e.getMessage());//
                return false;
            }
        }
        return true;
    }

    @Transactional
    @Override
    public Boolean updateImageFile(MultipartFile file, String name) {
        Path path = Paths.get(name);
        try {
            // Проверяем, существует ли файл
            if (Files.exists(path)) {
                Files.delete(path);

                try {
                    // Сохраняем файл на сервере
                    file.transferTo(new File(String.valueOf(path)))   ;
                } catch (IOException e) {
                    throw new FailedDependencyException("file is not saved");
                }

                System.out.println("The file:  " +  path + "is updated");
                return true;
            } else {
                System.out.println("The file:  " +  path + "is not exist");
                return false;
            }
        } catch (IOException e) {
            System.out.println("error, the file: " +  path + "is not deleted...  " + e.getMessage());
            return false;
        }
    }

    @Transactional
    @Override
    public String updateImageFileLink(String newLink, String lastLink) {
        return updateImageFileLinkCloudinary(newLink, lastLink);
        //return updateImageFileLinkFromDiskSpace(newLink, lastLink);
    }

    @Override
    public String updateImageFileLinkCloudinary(String newLink, String lastLink) {
        if (!newLink.equals(lastLink)) {
            deleteImageFile(lastLink);
            return newLink;
        }
        else {
            return lastLink;
        }
    }


    @Override
    public String updateImageFileLinkFromDiskSpace(String newLink, String lastLink) {
        Path newpath = Paths.get(newLink);

        if ( (!newLink.equals(lastLink)) && (Files.exists(newpath)) ) {
            deleteImageFile(lastLink);
            return newLink;
        }
        else {
            return lastLink;
        }
    }

    //*/
}
