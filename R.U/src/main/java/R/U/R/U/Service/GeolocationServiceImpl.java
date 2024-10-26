package R.U.R.U.Service;

import R.U.R.U.Entity.Geolocation;
import R.U.R.U.Repository.GeolocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class GeolocationServiceImpl implements GeolocationService {

    @Autowired
    GeolocationRepository geolocationRepository;

    @Override
    public List<Geolocation> findAllGeolocation() {
        return geolocationRepository.findAll();
    }

    @Override
    public Geolocation saveGeolocation(Geolocation geolocation) {
        return geolocationRepository.save(geolocation);
    }

    @Override
    public Geolocation updateGeolocation(Long id, Geolocation geolocation) {
        Geolocation geolocationdb = geolocationRepository.findById(id).get();
        geolocation.setLatitude(geolocationdb.getLatitude());
        geolocation.setLatitude(geolocationdb.getLongitude());
        return geolocationRepository.save(geolocationdb);
    }

    @Override
    public void deleteGeolocation(Long id) {
        geolocationRepository.deleteById(id);
    }
}
