package R.U.R.U.Service;

import R.U.R.U.Entity.Residences;
import R.U.R.U.Repository.ResidencesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResidencesServicesImpl implements ResidencesServices {

    @Autowired
    ResidencesRepository residencesRepository;

    @Override
    public List<Residences> findAllResidences() {
        return residencesRepository.findAll();
    }

    @Override
    public Residences findResidencesById(Long id) {
        return residencesRepository.findById(id).get();
    }

    @Override
    public Residences saveResidences(Residences residences) {
        return residencesRepository.save(residences);
    }

    @Override
    public Residences updateResidences(Long id, Residences residences) {
        Residences residencesdb = residencesRepository.findById(id).get();
        residencesdb.setName_residence(residences.getName_residence());
        residencesdb.setAddress(residences.getAddress());
        residencesdb.setCity(residences.getCity());
        residencesdb.setCategory(residences.getCategory());
        residencesdb.setDescription(residences.getDescription());
        residencesdb.setImageUrls(residences.getImageUrls());
        residencesdb.setNeighborhood(residences.getNeighborhood());
        residencesdb.setDepartment(residences.getDepartment());
        residencesdb.setRooms(residences.getRooms());
//        residencesdb.setPrice(residences.getPrice());
        residencesdb.setAbility(residences.getAbility());
        residencesdb.setServices(residences.getServices());
        return residencesRepository.save(residencesdb);
    }

    @Override
    public void deleteResidences(Long id) {
        residencesRepository.deleteById(id);
    }
}
