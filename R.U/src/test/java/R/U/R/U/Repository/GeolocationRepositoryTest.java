package R.U.R.U.Repository;

//import static org.junit.jupiter.api.Assertions.*;

import R.U.R.U.Entity.Geolocation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class GeolocationRepositoryTest {

    @Autowired
    private GeolocationRepository geolocationRepository;

    public GeolocationRepositoryTest(){
    }

    @Test
    public void findAllGeolocation(){
        List<Geolocation> geolocationList= geolocationRepository.findAll();
        System.out.println("GeolocationList = " + geolocationList);
    }

    @Test
    public void saveGeolocation(){
        Geolocation geolocationSave= Geolocation.builder()
                .latitude(-72.648814)
                .longitude(7.381675)
                .build();
    }

    @Test
    public void updateGeolocation(){
        Long id = 1L;
        Geolocation geolocationUpdate= geolocationRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        geolocationUpdate.setLatitude(-72.648814);
        geolocationUpdate.setLongitude(7.381675);
        geolocationRepository.save(geolocationUpdate);
    }

    @Test
    public void deleteGeolocation(){
        Long id = 58L;
        geolocationRepository.deleteById(id);
    }
}