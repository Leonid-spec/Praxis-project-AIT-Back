package de.dental_clinic.g_43_praxis.service.interfaces;

import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import de.dental_clinic.g_43_praxis.config.GoogleCloudConfig;
import de.dental_clinic.g_43_praxis.domain.entity.Image;
import de.dental_clinic.g_43_praxis.repository.ImageRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ImageService {

    private final ImageRepository imageRepository;
    private final Storage storage;
    private final GoogleCloudConfig googleCloudConfig;

    @Transactional
    public Image addImage(MultipartFile file, String path) throws IOException {
        Image image = Image.builder()
                .path(path)
                .build();
        return imageRepository.save(image);
    }

    @Transactional
    public void deleteImage(Long id) {
        if (imageRepository.existsById(id)) {
            imageRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Image with id " + id + " does not exist");
        }
    }
}
