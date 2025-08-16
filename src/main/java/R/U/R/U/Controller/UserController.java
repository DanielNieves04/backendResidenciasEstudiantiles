package R.U.R.U.Controller;

import R.U.R.U.Controller.models.AuthResponse;
import R.U.R.U.Controller.models.AuthenticateRequest;
import R.U.R.U.Controller.models.RegisterRequest;
import R.U.R.U.Entity.User;
import R.U.R.U.Service.PasswordResetService;
import R.U.R.U.Service.UsersServices;
import R.U.R.U.error.UsersNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/User")
public class UserController {

    @Autowired
    UsersServices usersServices;

    @Autowired
    PasswordResetService passwordResetService;

    @GetMapping("/findAllUsers")
    private List<User> findAllUsers(){
        return usersServices.findAllUsers();
    }

    @GetMapping("/findUserById/{id}")
    private User findUserById(@PathVariable Long id) throws UsersNotFoundException {
        return usersServices.findUserById(id);
    }

    @GetMapping("/findUserByMail/{mail}")
    private Optional<User> findUserByMail(@PathVariable String mail){
        return usersServices.findUserByMail(mail);
    }

    @PostMapping("/saveUser")
    private ResponseEntity<AuthResponse> saveUser(@Valid @RequestBody RegisterRequest request){
        return ResponseEntity.ok(usersServices.saveUser(request));
    }

    @PostMapping("/authenticateUser")
    private ResponseEntity<AuthResponse> authUser(@RequestBody AuthenticateRequest request){
        return ResponseEntity.ok(usersServices.authenticateUser(request));
    }

    @PostMapping("/{idUsers}/AddFavoriteResidence/{idResidences}")
    private ResponseEntity<String> addFavoriteResidence(@PathVariable Long idUsers,@PathVariable Long idResidences){
        usersServices.addFavoriteResidence(idUsers,idResidences);
        return ResponseEntity.ok("Residencia añadida a favoritos");
    }

    @PutMapping("/updateUser/{id}")
    private User updateUser (@PathVariable Long id, @Valid @RequestBody User user){
        return usersServices.updateUser(id, user);
    }

    @DeleteMapping("/deleteUser/{id}")
    private String deleteUser (@PathVariable Long id){
        usersServices.deleteUser(id);
        return "User deleting successfully";
    }

    @DeleteMapping("/{idUsers}/DeleteFavoriteResidence/{idResidences}")
    private ResponseEntity<String> deleteFavoriteResidence(@PathVariable Long idUsers,@PathVariable Long idResidences){
        usersServices.deleteFavoriteResidence(idUsers,idResidences);
        return ResponseEntity.ok("Residencia eliminada de favoritos");
    }

    // Endpoint: /User/requestReset/{email}
    @GetMapping("/requestReset/{email}")
    public ResponseEntity<?> requestPasswordReset(@PathVariable String email) {
        boolean success = passwordResetService.sendPasswordResetCode(email);
        return success ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    // Endpoint: /User/requestReset/{email}
    @GetMapping("/requestResetLogin/{email}")
    public ResponseEntity<?> requestPasswordResetLogin(@PathVariable String email) {
        boolean success = passwordResetService.sendPasswordResetCodeLogin(email);
        return success ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    // Endpoint: /User/validateCode/{email}/{code}
    @GetMapping("/validateCode/{email}/{code}")
    public ResponseEntity<?> validateResetCode(
            @PathVariable String email,
            @PathVariable String code) {
        boolean valid = passwordResetService.validateResetCode(email, code);
        return ResponseEntity.ok().body(Collections.singletonMap("valid", valid));
    }

    // Endpoint: /User/resetPassword/{email}/{code}
    //Body: "nuevaContraseña123"
    @PostMapping("/resetPassword/{email}/{code}/{newPassword}")
    public ResponseEntity<?> resetPassword(
            @PathVariable String email,
            @PathVariable String code,
            @PathVariable String newPassword) {

        if (!passwordResetService.validateResetCode(email, code)) {
            return ResponseEntity.badRequest().body("Código inválido o expirado");
        }

        passwordResetService.changePassword(email, newPassword);
        return ResponseEntity.ok().build();
    }
}
