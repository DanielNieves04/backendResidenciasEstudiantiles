package R.U.R.U.Controller.models;

import R.U.R.U.Entity.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @NotBlank(message = "Por favor agrega tu nombre")
    private String first_name;
    @NotBlank(message = "Por favor agrega tu apellido")
    private String lastName;
    @NotBlank(message = "Por favor asigna un correo electronico")
    private String mail;
    @NotBlank(message = "Debes crear una contraseña")
    private String password;
    @NotNull(message = "Por favor digita tu número telefonico")
    private String phone;
    private String city;
    private String department;
    @NotNull(message = "El campo 'role' es obligatorio")
    private Role role;
}
