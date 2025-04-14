package R.U.R.U.Controller;

import R.U.R.U.Controller.models.AuthResponse;
import R.U.R.U.Controller.models.AuthenticateRequest;
import R.U.R.U.Controller.models.RegisterRequest;
import R.U.R.U.Entity.User;
import R.U.R.U.Repository.UserBasicInfo;
import R.U.R.U.Service.UsersServices;
import R.U.R.U.error.UsersNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/User")
public class UserController {

    @Autowired
    UsersServices usersServices;

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

    @GetMapping("/findInfoById/{id}")
    private Optional<UserBasicInfo> findInfoById(@PathVariable Long id){
        return usersServices.findBasicInfoById(id);
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
}
