package R.U.R.U.Service;



import R.U.R.U.Controller.models.AuthResponse;
import R.U.R.U.Controller.models.AuthenticateRequest;
import R.U.R.U.Controller.models.RegisterRequest;
import R.U.R.U.Entity.User;
import R.U.R.U.error.UsersNotFoundException;

import java.util.List;
import java.util.Optional;

public interface UsersServices {
    List<User> findAllUsers();
    User findUserById(Long id) throws UsersNotFoundException;
    Optional<User> findUserByMail(String mail);
    AuthResponse saveUser (RegisterRequest request);
    AuthResponse authenticateUser (AuthenticateRequest request);
    User updateUser (Long id, User user);
    void deleteUser (Long id);
    void addFavoriteResidence(Long idUsers, Long idResidences);
    void deleteFavoriteResidence(Long idUsers, Long idResidences);
}
