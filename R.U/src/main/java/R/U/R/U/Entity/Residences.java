package R.U.R.U.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
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
public class Residences {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "resi_seq")
    @SequenceGenerator(name = "resi_seq", sequenceName = "tbl_residences_seq", allocationSize = 1)
    private Long idResidences;
    @NotBlank(message = "Por favor agrega un nombre")
    private String name_residence;
    //Manejarla con  AWS S3 y solo traer las url
    private String imageUrls;
    @NotBlank(message = "Por favor selecciona la ubicación")
    private String address;
    private String city;
    @NotBlank(message = "Por favor indíca el barrio")
    private String neighborhood;
    private String department;
    @Min(1)
    private Integer rooms;
//    @Column(nullable = true)
    private Integer price;
    private String services;
    private String description;
    @Min(1)
    private Integer ability;
    private String category;
    private Boolean state;
    @Embedded
    private Geolocation geolocation;

    @ManyToOne(
            cascade = CascadeType.ALL//cualquier acción que se realice en la entidad principal se propagará automáticamente a la entidad asociada
    )
    @JoinColumn(
            name="id_Users",
            referencedColumnName = "idUsers"
    )
    private Users users;


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
