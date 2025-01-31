package R.U.R.U.Service;

import R.U.R.U.Entity.Residence;
import R.U.R.U.error.ResidencesNotFoundException;

import java.util.List;

public interface ResidencesServices {
    List<Residence> findAllResidences();
    Residence findResidencesById(Long id) throws ResidencesNotFoundException;
    Residence saveResidences(Residence residence);
    Residence updateResidences (Long id, Residence residence);
    void deleteResidences (Long id);
}
