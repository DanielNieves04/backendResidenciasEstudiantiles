package R.U.R.U.Repository;

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



}