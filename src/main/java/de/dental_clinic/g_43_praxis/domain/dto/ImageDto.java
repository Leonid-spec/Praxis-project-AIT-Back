package de.dental_clinic.g_43_praxis.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

@JsonPropertyOrder({
        "id",
        "path"
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ImageDto {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("path")
    private String path;

    private Long dentalServiceId;
    private Long doctorId;
}