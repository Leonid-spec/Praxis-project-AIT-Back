package de.dental_clinic.g_43_praxis.domain.dto;

import java.util.Objects;
import java.util.Set;

public class AdminDto {
    private Long id;
    private String login;
    private Set<RoleDto> roles;

    public AdminDto() {}

    public AdminDto(Long id, String login, Set<RoleDto> roles) {
        this.id = id;
        this.login = login;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Set<RoleDto> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleDto> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        AdminDto adminDto = (AdminDto) o;
        return Objects.equals(id, adminDto.id) && Objects.equals(login, adminDto.login) && Objects.equals(roles, adminDto.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, roles);
    }

    @Override
    public String toString() {
        return String.format("AdminDto: id = %d, login = '%s', roles = '%s'", id, login, roles);
    }
}
