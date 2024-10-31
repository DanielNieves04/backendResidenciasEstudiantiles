package R.U.R.U.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="tbl_geolocation")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Geolocation {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "geo_seq")
    @SequenceGenerator(name = "geo_seq", sequenceName = "tbl_geolocation_seq", allocationSize = 1)
    private Long idGeolocation;
    private double latitude;
    private double longitude;

}
