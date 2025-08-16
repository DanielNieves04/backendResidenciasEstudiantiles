package R.U.R.U.Entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name="tbl_residences")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
//@ToString(exclude = "geolocation")

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "idResidences"
)
public class Residence {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "resi_seq")
    @SequenceGenerator(name = "resi_seq", sequenceName = "tbl_residences_seq", allocationSize = 1)
    private Long idResidences;
    @NotBlank(message = "Por favor agrega un nombre")
    private String name_residence;

    //Manejarla con  AWS S3 y solo traer las url
    @ElementCollection
    @CollectionTable(name = "imagen_urls", joinColumns = @JoinColumn(name = "id_residences"))
    @Column(name = "url")
    @Size(min = 5, message = "Debe proporcionar al menos 5 URLs")
    private List<String> imageUrls;

    @NotBlank(message = "Por favor indíca la dirección")
    private String address;
    private String city;
    @NotBlank(message = "Por favor indíca el barrio")
    private String neighborhood;
    private String department;
    @NotNull(message = "Es obligatorio indicar el número de habitaciones")
    @Min(1)
    private Integer rooms;
//    @Column(nullable = true)
    @NotNull(message = "Es obligatorio asignar un precio a la residencia")
    private Integer price;
    @NotNull(message = "Es obligatorio indicar los servicios con los que cuenta la residencia")
    private String services;
    private String description;
    @NotNull(message = "Es obligatorio indicar la capacidad de personas de la residencia")
    @Min(1)
    private Integer ability;
    @NotNull(message = "Es obligatorio seleccionar una categoria de su residencia")
    private String category;
    private Boolean state;
    @NotNull(message = "Es obligatorio asignar una geolocalización a la residencia")
    @Embedded
    private Geolocation geolocation;


    @JsonIgnoreProperties(value = {"idUsers","department","role", "enabled",
            "authorities","username","accountNonExpired","credentialsNonExpired",
            "accountNonLocked","password", "favoriteResidences", "residences"})
    @ManyToOne
    @JoinColumn(name = "id_users", nullable = false)
    private User user;

    // Metodo para obtener los servicios como lista
    public List<String> getServices() {
        return services != null ? Arrays.asList(services.split(",")) : new ArrayList<>();
    }

    // Metodo para establecer los servicios desde una lista
    public void setServices(List<String> services) {
        this.services = String.join(",", services);
    }
}
