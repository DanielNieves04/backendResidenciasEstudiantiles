package R.U.R.U.Repository;

import R.U.R.U.Entity.Geolocation;
import R.U.R.U.Entity.Residence;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ResidenceRepositoryTest {

    @Autowired
    private ResidencesRepository residencesRepository;

    public ResidenceRepositoryTest(){
    }

    @Test
    public void findAllResidences(){
        List<Residence> residenceList = residencesRepository.findAll();
        System.out.println("ResidencesList = " + residenceList);
    }

    @Test
    public void  findResidenceById(){
        Long residenceId = 3L;
        Residence residenceById =residencesRepository.findById(residenceId).orElseThrow(() -> new RuntimeException("Product not found"));
        System.out.println("Residence =" + residenceById);
    }

    @Test
    public void saveResidence(){

        Geolocation geolocation = Geolocation.builder()
                .latitude(-72.648814)
                .longitude(7.381675)
                .build();

        Residence residenceSave = Residence.builder()
                .name_residence("Apartamento de estudiantes")
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
        residencesRepository.save(residenceSave);

    }

    @Test
    public void updateResidences(){
        Long residenceId = 101L;
        Residence residenceUpdate =residencesRepository.findById(residenceId).orElseThrow(() -> new RuntimeException("Product not found"));
        residenceUpdate.setName_residence("Apartamento para estudiantes");
        residencesRepository.save(residenceUpdate);
    }

    @Test
    public void deleteResidence(){
        Long residenceId = 3L;
        residencesRepository.deleteById(residenceId);
    }

}