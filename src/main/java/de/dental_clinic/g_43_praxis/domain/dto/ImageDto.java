package de.dental_clinic.g_43_praxis.domain.dto;

public class ImageDto {
    private Long id;
    private String path;

    public ImageDto() {}

    public ImageDto(Long id, String path) {
        this.id = id;
        this.path = path;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return String.format("ImageDto: id = %d, path = '%s'", id, path);
    }

}
