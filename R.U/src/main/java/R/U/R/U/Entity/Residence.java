package R.U.R.U.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class Residence {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "resi_seq")
    @SequenceGenerator(name = "resi_seq", sequenceName = "tbl_residences_seq", allocationSize = 1)
    private Long idResidences;
    @NotBlank(message = "Por favor agrega un nombre")
    private String name_residence;
    //Manejarla con  AWS S3 y solo traer las url
    private String imageUrls;
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

    @NotNull(message = "Es obligatorio asignar un usuario a esta residencia")
    @ManyToOne
    @JoinColumn(
            name="id_Users",
            referencedColumnName = "idUsers",
            nullable = false
    )
    @JsonBackReference
    private User user;


    // Metodo para obtener las URLs como lista
    public List<String> getImageUrls() {
        return imageUrls != null ? Arrays.asList(imageUrls.split(",")) : new ArrayList<>();
    }

    // Metodo para establecer las URLs desde una lista
    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = String.join(",", imageUrls);
    }

    // Metodo para obtener los servicios como lista
    public List<String> getServices() {
        return services != null ? Arrays.asList(services.split(",")) : new ArrayList<>();
    }

    // Metodo para establecer los servicios desde una lista
    public void setServices(List<String> services) {
        this.services = String.join(",", services);
    }
}