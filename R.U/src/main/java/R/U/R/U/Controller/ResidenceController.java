package R.U.R.U.Controller;

import R.U.R.U.Entity.Residence;
import R.U.R.U.Service.ResidencesServices;
import R.U.R.U.error.ResidencesNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Residence")
public class ResidenceController {

    @Autowired
    ResidencesServices residencesServices;

    @GetMapping("/findAllResidences")
    public List<Residence> findAllResidences() {
        return residencesServices.findAllResidences();
    }

    @GetMapping("/findResidenceById/{id}")
    public Residence findResidenceById(@PathVariable Long id) throws ResidencesNotFoundException {
        return residencesServices.findResidencesById(id);
    }

    @PostMapping("/saveResidence")
    public Residence saveResidences(@Valid @RequestBody Residence residence) {
        return residencesServices.saveResidences(residence);
    }

    @PutMapping("/updateResidence/{id}")
    public Residence updateResidences(@PathVariable Long id, @Valid @RequestBody Residence residence) {
        return residencesServices.updateResidences(id, residence);
    }

    @DeleteMapping("/deleteResidence/{id}")
    public String deleteResidences(@PathVariable Long id) {
        residencesServices.deleteResidences(id);
        return "Residences deleting successfully";
    }
    
}
