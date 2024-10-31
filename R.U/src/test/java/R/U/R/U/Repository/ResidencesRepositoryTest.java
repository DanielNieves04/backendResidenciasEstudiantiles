package R.U.R.U.Repository;

import R.U.R.U.Entity.Geolocation;
import R.U.R.U.Entity.Residences;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.awt.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ResidencesRepositoryTest {

    @Autowired
    private ResidencesRepository residencesRepository;

    public ResidencesRepositoryTest(){
    }

    @Test
    public void findAllResidences(){
        List<Residences> residencesList= residencesRepository.findAll();
        System.out.println("ResidencesList = " + residencesList);
    }

    @Test
    public void  findResidenceById(){
        Long residenceId = 101L;
        Residences residencesById=residencesRepository.findById(residenceId).orElseThrow(() -> new RuntimeException("Product not found"));
        System.out.println("Residence =" + residencesById);
    }

    @Test
    public void saveResidence(){

        Geolocation geolocation = Geolocation.builder()
                .latitude(-72.648814)
                .longitude(7.381675)
                .build();

        Residences residencesSave = Residences.builder()
                .name("Apartamento de estudiantes")
                .imageUrls("")
                .address("Carrera 3 # 32-09")
                .city("Pamplona")
                .neighborhood("La curva")
                .department("Norte de Santander")
                .rooms(5)
                .price(800000)
                .services("agua,luz,internet,derecho a cocina")
                .description("Apartamento cerca de la sede principal de la universidad, sin restricciones de llegada ni visitas")
                .ability(7)
                .category("Apartamento")
                .state(false)
                .geolocation(geolocation)
                .build();
        residencesRepository.save(residencesSave);

    }

    @Test
    public void updateResidences(){
        Long residenceId = 101L;
        Residences residencesUpdate=residencesRepository.findById(residenceId).orElseThrow(() -> new RuntimeException("Product not found"));
        residencesUpdate.setName("Apartamento para estudiantes");
        residencesRepository.save(residencesUpdate);
    }

    @Test
    public void deleteResidence(){
        Long residenceId = 101L;
        residencesRepository.deleteById(residenceId);
    }

}