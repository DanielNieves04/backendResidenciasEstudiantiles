package R.U.R.U.Service;

import R.U.R.U.Entity.Residence;
import R.U.R.U.Entity.User;
import R.U.R.U.Repository.ResidencesRepository;
import R.U.R.U.Repository.UserRepository;
import R.U.R.U.error.ResidencesNotFoundException;
import R.U.R.U.error.UsersNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResidencesServicesImpl implements ResidencesServices {

    @Autowired
    ResidencesRepository residencesRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public List<Residence> findAllResidences() {
        List<Residence> residences = new ArrayList<>();

        while (residences.isEmpty()) {
            residences = residencesRepository.findAll();

            try {
                Thread.sleep(0,500); // Espera 1 segundo antes de volver a intentar
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Thread was interrupted", e);
            }
        }

        return residences;
    }

    @Override
    public Residence findResidencesById(Long id) {
        return residencesRepository.findById(id)
                .orElseThrow(() -> new ResidencesNotFoundException("Esta residencia no existe"));
    }

    @Override
    public Residence saveResidences(Residence residence) {
        // Validar que el usuario y su ID no sean nulos
        if (residence.getUser() == null || residence.getUser().getIdUsers() == null) {
            throw new IllegalArgumentException("User ID must not be null");
        }

        // Verificar que el usuario existe en la base de datos
        User user = userRepository.findById(residence.getUser().getIdUsers())
                .orElseThrow(() -> new UsersNotFoundException("User not found"));

        // Construir la residencia
        Residence residence1 = Residence.builder()
                .name_residence(residence.getName_residence())
                .address(residence.getAddress())
                .city(residence.getCity())
                .category(residence.getCategory())
                .description(residence.getDescription())
                .imageUrls(residence.getImageUrls())
                .neighborhood(residence.getNeighborhood())
                .department(residence.getDepartment())
                .rooms(residence.getRooms())
                .price(residence.getPrice())
                .ability(residence.getAbility())
                .state(residence.getState())
                .services(String.join(",", residence.getServices()))
                .geolocation(residence.getGeolocation())
                .user(user)
                .build();

        // Guardar la residencia
        user.getResidences().add(residence1);

        // Guardar la residencia y actualizar el usuario
        residence1 = residencesRepository.save(residence1);
        userRepository.save(user); // Asegurar que el usuario se actualice con la nueva residencia

        return residence1;
    }


    @Override
    public Residence updateResidences(Long id, Residence residence) {
        Residence residencesdb = residencesRepository.findById(id).get();
        residencesdb.setName_residence(residence.getName_residence());
        residencesdb.setAddress(residence.getAddress());
        residencesdb.setCity(residence.getCity());
        residencesdb.setCategory(residence.getCategory());
        residencesdb.setDescription(residence.getDescription());
        residencesdb.setImageUrls(residence.getImageUrls());
        residencesdb.setNeighborhood(residence.getNeighborhood());
        residencesdb.setDepartment(residence.getDepartment());
        residencesdb.setRooms(residence.getRooms());
        residencesdb.setPrice(residence.getPrice());
        residencesdb.setAbility(residence.getAbility());
        residencesdb.setServices(residence.getServices());
        residencesdb.setGeolocation(residence.getGeolocation());
        return residencesRepository.save(residencesdb);
    }

    @Override
    public void deleteResidences(Long id) {
        residencesRepository.deleteById(id);
    }
}
