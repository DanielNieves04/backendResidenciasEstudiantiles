package R.U.R.U.Service;

import R.U.R.U.Entity.Residence;
import R.U.R.U.Entity.User;
import R.U.R.U.Repository.ResidencesRepository;
import R.U.R.U.Repository.UserRepository;
import R.U.R.U.error.ResidencesNotFoundException;
import R.U.R.U.error.UsersNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResidencesServicesImpl implements ResidencesServices {

    @Autowired
    ResidencesRepository residencesRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public List<Residence> findAllResidences() {
        return residencesRepository.findAll();
    }

    @Override
    public Residence findResidencesById(Long id) {
        Optional<Residence> residences = residencesRepository.findById(id);
        if(!residences.isPresent()){
            throw new ResidencesNotFoundException("Esta residencia no existe");
        }
        return residences.get();
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
                .imageUrls(String.join(",", residence.getImageUrls()))
                .neighborhood(residence.getNeighborhood())
                .department(residence.getDepartment())
                .rooms(residence.getRooms())
                .price(residence.getPrice())
                .ability(residence.getAbility())
                .services(String.join(",", residence.getServices()))
                .geolocation(residence.getGeolocation())
                .user(user)
                .build();

        // Guardar la residencia
        return residencesRepository.save(residence1);
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
