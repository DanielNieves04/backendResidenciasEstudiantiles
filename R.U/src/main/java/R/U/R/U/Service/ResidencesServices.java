package R.U.R.U.Service;

import R.U.R.U.Entity.Residences;
import R.U.R.U.error.ResidencesNotFoundException;

import java.util.List;

public interface ResidencesServices {
    List<Residences> findAllResidences();
    Residences findResidencesById(Long id) throws ResidencesNotFoundException;
    Residences saveResidences(Residences residences);
    Residences updateResidences (Long id,Residences residences);
    void deleteResidences (Long id);
}
