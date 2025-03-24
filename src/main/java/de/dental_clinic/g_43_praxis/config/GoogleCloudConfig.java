package de.dental_clinic.g_43_praxis.config;

import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GoogleCloudConfig {
    @Value("${gcp.storage.bucket}")
    private String bucketName;

    @Bean
    public Storage storage() {
        return StorageOptions.getDefaultInstance().getService();
    }

    public String getBucketName() {
        return bucketName;
    }
}
