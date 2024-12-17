package R.U.R.U.Repository;

import R.U.R.U.Entity.Role;
import R.U.R.U.Entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    public UserRepositoryTest(){

    }

    @Test
    public void findAllUsers(){
        List<User> users = userRepository.findAll();
        System.out.println("Users = "+ users);
    }

    @Test
    public void findUserByMail() {
        String mail = "pipe@daniel.com";
        User user = userRepository.findUserByMail(mail).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        System.out.println("User = " + user);
    }

    @Test
    public void findUserById(){
        Long id = 1l;
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        System.out.println("User = "+user);
    }

    @Test
    public void saveUser(){
        Role role = Role.Arrendador;

        User userSave = User.builder()
                .first_name("Tatiana")
                .lastName("Nives")
                .mail("tita@daniel.com")
                .phone("3227992136")
                .password("tita2007")
                .city("Paz de Ariporo")
                .department("Casanare")
                .role(role)
                .build();
        userRepository.save(userSave);
    }

    @Test
    public void updateUser(){
        Long id = 6L;
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        user.setCity("Hato Corozal");
        userRepository.save(user);
    }

    @Test
    public void deleteUser(){
        Long id = 4L;
        userRepository.deleteById(id);
    }
}