package R.U.R.U.Service;

import R.U.R.U.Entity.Geolocation;

import java.util.List;


public interface GeolocationService {
    List<Geolocation> findAllGeolocation();
    Geolocation saveGeolocation(Geolocation geolocation);
    Geolocation updateGeolocation(Long id,Geolocation geolocation);
    void deleteGeolocation(Long id);
}
