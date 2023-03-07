package sky.pro.SkyDreamTeam.AnimalService.controllers.DogShelter;

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
import sky.pro.SkyDreamTeam.AnimalService.model.DogShelter.DogShelterPet;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.http.HttpStatus.OK;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DogShelterPetControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private DogShelterPetController petController;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void contextLoads() throws Exception {
        Assertions.assertThat(petController).isNotNull();
    }

    @Test
    public void testPostPet() throws Exception {
        DogShelterPet pet = new DogShelterPet();
        pet.setBreed("testBreed");
        pet.setName("testName");
        Assertions
                .assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/dogShelter/pet", pet,
                        CatShelterPerson.class).getName()).isEqualTo("testName");
        restTemplate.delete("http://localhost:" + port + "/dogShelter/pet?name=testName");
    }

    @Test
    public void testPutPet() throws Exception {
        DogShelterPet pet = new DogShelterPet();
        pet.setBreed("testBreed");
        pet.setName("testName");
        HttpEntity<DogShelterPet> entity = new HttpEntity<DogShelterPet>(pet);
        ResponseEntity<CatShelterPet> response = restTemplate.exchange("http://localhost:" + port +
                "/dogShelter/pet", HttpMethod.PUT, entity, CatShelterPet.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(OK);
        Assertions.assertThat(Objects.requireNonNull(response.getBody()).getName()).isEqualTo("testName");
        restTemplate.delete("http://localhost:" + port + "/dogShelter/pet?name=testName");
    }

    @Test
    public void testGetPet() throws Exception {
        DogShelterPet pet = new DogShelterPet();
        pet.setBreed("testBreed");
        pet.setName("testName");
        Long testId = petController.createPet(pet).getBody().getId();
        Assertions.assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/dogShelter/pet/testName",
                        DogShelterPet.class)).isEqualTo(pet);
        restTemplate.delete("http://localhost:" + port + "/dogShelter/pet?id=" + testId);
    }
    @Test
    public void testDeletePet() throws Exception {
        DogShelterPet pet = new DogShelterPet();
        pet.setBreed("testBreed");
        pet.setName("testName");
        Long testId = petController.createPet(pet).getBody().getId();
        restTemplate.delete("http://localhost:" + port + "/dogShelter/pet?id=" + testId);
        assertThrows(PetNotFoundException.class, () -> petController.getPet("testName"));
    }
}
