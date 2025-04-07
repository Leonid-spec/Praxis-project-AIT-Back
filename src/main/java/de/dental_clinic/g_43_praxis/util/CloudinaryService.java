package de.dental_clinic.g_43_praxis.util;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryService {

    private final Cloudinary cloudinary;

    public CloudinaryService(@Value("${CLOUD_NAME}") String cloudName,
                             @Value("${API_KEY}") String apiKey,
                             @Value("${API_SECRET}") String apiSecret) {
        this.cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", cloudName,
                "api_key", apiKey,
                "api_secret", apiSecret));
    }

    public String uploadImage(MultipartFile file) throws IOException {
        Map<String, Object> uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
        return (String) uploadResult.get("url");  // Публичный URL изображения
    }


    public String deleteImage(String publicId) throws IOException {
        try {
            Map<String, Object> result = cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
            return (String) result.get("result");  // Возвращает "ok" если удаление прошло успешно
        } catch (Exception e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();  // Обработка ошибок
        }
    }
}
