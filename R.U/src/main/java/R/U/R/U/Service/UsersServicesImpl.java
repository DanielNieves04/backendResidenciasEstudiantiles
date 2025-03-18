package R.U.R.U.Service;

import R.U.R.U.Controller.models.AuthResponse;
import R.U.R.U.Controller.models.AuthenticateRequest;
import R.U.R.U.Controller.models.RegisterRequest;
import R.U.R.U.Entity.Residence;
import R.U.R.U.Entity.User;
import R.U.R.U.Repository.ResidencesRepository;
import R.U.R.U.Repository.UserRepository;
import R.U.R.U.config.JwtService;
import R.U.R.U.error.UsersNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsersServicesImpl implements UsersServices{

    private final UserRepository userRepository;

    private final ResidencesRepository residencesRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findUserById(Long id) throws UsersNotFoundException {
        Optional<User> users = userRepository.findById(id);
        if(users.isEmpty()){
            throw new UsersNotFoundException("Usuario no encontrado");
        }
        return users.get();
    }

    @Override
    public Optional<User> findUserByMail(String mail) {
        return userRepository.findUserByMail(mail);
    }

    @Override
    public AuthResponse saveUser(RegisterRequest request) {
        var user1 = User.builder()
                .first_name(request.getFirst_name())
                .lastName(request.getLastName())
                .mail(request.getMail())
                .password(passwordEncoder.encode(request.getPassword()))
                .phone(request.getPhone())
                .city(request.getCity())
                .department(request.getDepartment())
                .role(request.getRole())
                .residences(new ArrayList<>())
                .build();
        userRepository.save(user1);
        var jwtToken = jwtService.generateToken(user1);
        return AuthResponse.builder()
                .token(jwtToken).build();
    }

    @Override
    public AuthResponse authenticateUser(AuthenticateRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getMail(),
                        request.getPassword()
                )
        );
        var user1 = userRepository.findUserByMail(request.getMail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user1);
        return AuthResponse.builder()
                .token(jwtToken).build();
    }


    @Override
    public User updateUser(Long id, User user) {
        Optional<User> users1 = userRepository.findById(id);
        if(!users1.isPresent()){
            throw new UsersNotFoundException("Usuario no encontrado");
        }
        users1.get().setFirst_name(user.getFirst_name());
        users1.get().setLastName(user.getLastName());
        users1.get().setCity(user.getCity());
        users1.get().setMail(user.getMail());
        users1.get().setDepartment(user.getDepartment());
        users1.get().setPhone(user.getPhone());
        users1.get().setRole(user.getRole());
        users1.get().setPassword(user.getPassword());
        return userRepository.save(users1.get());
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void addFavoriteResidence(Long idUsers, Long idResidences) {
        User user = userRepository.findById(idUsers)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Residence residence = residencesRepository.findById(idResidences)
                .orElseThrow(() -> new RuntimeException("Residencia no encontrada"));

        // Verificar que la residencia no esté ya en la lista
        if (!user.getFavoriteResidences().contains(residence)) {
            user.getFavoriteResidences().add(residence);
            userRepository.save(user); // Actualiza el usuario en la base de datos
        }
    }

    @Override
    public void deleteFavoriteResidence(Long idUsers, Long idResidences) {
        User user = userRepository.findById(idUsers)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Residence residence = residencesRepository.findById(idResidences)
                .orElseThrow(() -> new RuntimeException("Residencia no encontrada"));

        // Verificar que la residencia esté ya en la lista
        if (user.getFavoriteResidences().contains(residence)) {
            user.getFavoriteResidences().remove(residence);
            userRepository.save(user); // Actualiza el usuario en la base de datos
        }else {
            throw new RuntimeException("La residencia no está en la lista de favoritos del usuario");
        }
    }
}
