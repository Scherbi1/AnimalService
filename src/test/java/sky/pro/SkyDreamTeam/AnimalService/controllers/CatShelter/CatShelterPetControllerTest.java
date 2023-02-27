package sky.pro.SkyDreamTeam.AnimalService.controllers.CatShelter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import sky.pro.SkyDreamTeam.AnimalService.exceptions.PetNotFoundException;
import sky.pro.SkyDreamTeam.AnimalService.model.CatShelter.CatShelterPerson;
import sky.pro.SkyDreamTeam.AnimalService.model.CatShelter.CatShelterPet;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.http.HttpStatus.OK;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CatShelterPetControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private CatShelterPetController petController;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void contextLoads() throws Exception {
        Assertions.assertThat(petController).isNotNull();
    }

    @Test
    public void testPostPet() throws Exception {
        CatShelterPet pet = new CatShelterPet();
        pet.setBreed("testBreed");
        pet.setName("testName");
        Assertions
                .assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/catShelter/pet", pet,
                        CatShelterPerson.class).getName()).isEqualTo("testName");
        restTemplate.delete("http://localhost:" + port + "/catShelter/pet?name=testName");
    }

    @Test
    public void testPutPet() throws Exception {
        CatShelterPet pet = new CatShelterPet();
        pet.setBreed("testBreed");
        pet.setName("testName");
        HttpEntity<CatShelterPet> entity = new HttpEntity<CatShelterPet>(pet);
        ResponseEntity<CatShelterPet> response = restTemplate.exchange("http://localhost:" + port +
                "/catShelter/pet", HttpMethod.PUT, entity, CatShelterPet.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(OK);
        Assertions.assertThat(Objects.requireNonNull(response.getBody()).getName()).isEqualTo("testName");
        restTemplate.delete("http://localhost:" + port + "/catShelter/pet?name=testName");
    }

    @Test
    public void testGetPet() throws Exception {
        CatShelterPet pet = new CatShelterPet();
        pet.setBreed("testBreed");
        pet.setName("testName");
        Long testId = petController.createPet(pet).getBody().getId();
        Assertions.assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/catShelter/pet/testName",
                        CatShelterPet.class)).isEqualTo(pet);
        restTemplate.delete("http://localhost:" + port + "/catShelter/pet?id=" + testId);
    }
    @Test
    public void testDeletePet() throws Exception {
        CatShelterPet pet = new CatShelterPet();
        pet.setBreed("testBreed");
        pet.setName("testName");
        Long testId = petController.createPet(pet).getBody().getId();
        restTemplate.delete("http://localhost:" + port + "/catShelter/pet?id=" + testId);
        assertThrows(PetNotFoundException.class, () -> petController.getPet("testName"));
    }
}
