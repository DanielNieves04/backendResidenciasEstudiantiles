package R.U.R.U.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="tbl_residencesFavorites")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ResidencesFavorites {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fav_seq")
    @SequenceGenerator(name = "fav_seq", sequenceName = "tbl_residencesFavorites_seq", allocationSize = 1)
    private Long idResidencesFavorites;


    @ManyToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name="id_Users",
            referencedColumnName = "idUsers"
    )
    private Users users;

    @ManyToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "id_Residences",
            referencedColumnName = "idResidences"
    )
    private Residences residences;
}
