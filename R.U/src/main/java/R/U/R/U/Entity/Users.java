package R.U.R.U.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="tbl_users")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Users {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long idUsers;
    private String name;
    private String lastName;
    private String mail;
    private String password;
    private String phone;
    private String city;
    private String department;

    @Enumerated(EnumType.ORDINAL)
    private Role role;

}
