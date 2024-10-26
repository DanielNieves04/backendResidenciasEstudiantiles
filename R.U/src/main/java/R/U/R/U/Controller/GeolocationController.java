package R.U.R.U.Controller;

import R.U.R.U.Entity.Geolocation;
import R.U.R.U.Service.GeolocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/GeolocationController")
public class GeolocationController {

    @Autowired
    GeolocationService geolocationService;

    @GetMapping("/findAllGeolocations")
    public List<Geolocation> getGeolocation() {
        return geolocationService.findAllGeolocation();
    }

    @PostMapping("/saveGeolocation")
    public Geolocation saveGeolocation(@RequestBody Geolocation geolocation) {
        return geolocationService.saveGeolocation(geolocation);
    }

    @PutMapping("/updateGeolocation/{id}")
    public Geolocation updateGeolocation(@PathVariable Long id, @RequestBody Geolocation geolocation) {
        return geolocationService.updateGeolocation(id, geolocation);
    }

    @DeleteMapping("/deleteGeolocation/{id}")
    public String deleteGeolocation(@PathVariable Long id) {
        geolocationService.deleteGeolocation(id);
        return "Geolocation deleting successfully";
    }
}
