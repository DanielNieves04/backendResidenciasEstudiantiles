package R.U.R.U.Entity;

import jakarta.persistence.*;
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
    private String name;
    //Manejarla con  AWS S3 y solo traer las url
    private String imageUrls;
    private String address;
    private String city;
    private String neighborhood;
    private String department;
    private int rooms;
//    @Column(nullable = true)
    private int price;
    private String services;
    private String description;
    private int ability;
    private String category;
    private Boolean state;

    @OneToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "id_Geolocation",
            referencedColumnName = "idGeolocation"
    )
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
