package de.dental_clinic.g_43_praxis.domain.dto;

import java.util.Objects;

public class RoleDto {
    private Long id;
    private String name;

    public RoleDto() {}

    public RoleDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        RoleDto roleDto = (RoleDto) o;
        return Objects.equals(id, roleDto.id) && Objects.equals(name, roleDto.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return String.format("RoleDto: id = %d, name = '%s'", id, name);
    }
}