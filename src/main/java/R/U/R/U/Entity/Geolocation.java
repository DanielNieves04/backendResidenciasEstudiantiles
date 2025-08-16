package R.U.R.U.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//Tabla Incrustable
@Table(name="tbl_geolocation")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Embeddable
public class Geolocation {

    private double latitude;
    private double longitude;

}
