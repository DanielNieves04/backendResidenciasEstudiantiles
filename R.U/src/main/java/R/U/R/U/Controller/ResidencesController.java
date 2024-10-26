package R.U.R.U.Controller;

import R.U.R.U.Entity.Residences;
import R.U.R.U.Service.ResidencesServices;
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

    @PostMapping("/saveResidences")
    public Residences saveResidences(@RequestBody Residences residences) {
        return residencesServices.saveResidences(residences);
    }

    @PutMapping("/updateResidences/{id}")
    public Residences updateResidences(@PathVariable Long id, @RequestBody Residences residences) {
        return residencesServices.updateResidences(id, residences);
    }

    @DeleteMapping("/deleteResidences/{id}")
    public String deleteResidences(@PathVariable Long id) {
        residencesServices.deleteResidences(id);
        return "Residences deleting successfully";
    }
}
