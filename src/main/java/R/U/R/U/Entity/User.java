package R.U.R.U.Entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

//1
@Entity
@Table(name="tbl_users")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "tbl_users_seq", allocationSize = 1)
    private Long idUsers;
    @NotBlank(message = "Por favor agrega tu nombre")
    private String first_name;
    @NotBlank(message = "Por favor agrega tu apellido")
    private String lastName;
    @NotBlank(message = "Por favor asigna un correo electronico")
    @Column(name = "mail", nullable = false, unique = true)
    private String mail;
    @NotBlank(message = "Debes crear una contraseña")
    private String password;
    @NotNull(message = "Por favor digita tu número telefonico")
    private String phone;
    private String city;
    private String department;
    private String imageUrl;

    @NotNull(message = "Por favor selecciona tu rol")
    @Enumerated(EnumType.ORDINAL)
    private Role role;

    @ManyToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @JoinTable(
            name = "user_has_residence",
            joinColumns = @JoinColumn(name = "id_users", referencedColumnName = "idUsers"),
            inverseJoinColumns = @JoinColumn(name = "id_residences", referencedColumnName = "idResidences")
    )
    private List<Residence> favoriteResidences;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Residence> residences = new ArrayList<>();

    //Vamos a controlar los roles
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return mail;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
