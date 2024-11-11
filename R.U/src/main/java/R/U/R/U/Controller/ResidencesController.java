package R.U.R.U.Controller;

import R.U.R.U.Entity.Residences;
import R.U.R.U.Service.ResidencesServices;
import R.U.R.U.error.ResidencesNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ResidencesController")
public class ResidencesController {

    @Autowired
    ResidencesServices residencesServices;

    @GetMapping("/findAllResidences")
    public List<Residences> findAllResidences() {
        return residencesServices.findAllResidences();
    }

    @GetMapping("/findResidenceById/{id}")
    public Residences findResidenceById(@PathVariable Long id) throws ResidencesNotFoundException {
        return residencesServices.findResidencesById(id);
    }

    @PostMapping("/saveResidence")
    public Residences saveResidences(@Valid @RequestBody Residences residences) {
        return residencesServices.saveResidences(residences);
    }

    @PutMapping("/updateResidence/{id}")
    public Residences updateResidences(@PathVariable Long id, @RequestBody Residences residences) {
        return residencesServices.updateResidences(id, residences);
    }

    @DeleteMapping("/deleteResidence/{id}")
    public String deleteResidences(@PathVariable Long id) {
        residencesServices.deleteResidences(id);
        return "Residences deleting successfully";
    }
}
