package de.dental_clinic.g_43_praxis.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "admin")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Admin implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "login", nullable = false, unique = true)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "admin_roles",
            joinColumns = @JoinColumn(name = "admin_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return (Collection<? extends GrantedAuthority>) roles;
    }

    @Override
    public String getUsername() {
        return login;
    }

    // Это временный метод, который служит для создания
    // тестового зашифрованного пароля
//    public static void main(String[] args) {
//        System.out.println(new BCryptPasswordEncoder().encode("admin"));
//    }
}

