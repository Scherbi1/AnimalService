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
import sky.pro.SkyDreamTeam.AnimalService.model.CatShelter.CatShelterPerson;

import java.util.Objects;

import static org.springframework.http.HttpStatus.OK;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CatShelterPersonControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private CatShelterPersonController personController;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void contextLoads() throws Exception {
        Assertions.assertThat(personController).isNotNull();
    }

    @Test
    public void testPostPerson() throws Exception {
        CatShelterPerson person = new CatShelterPerson();
        person.setChatId(12L);
        person.setName("Hermione");

        Assertions
                .assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/catShelter/person", person,
                        CatShelterPerson.class).getName()).isEqualTo("Hermione");
    }

    @Test
    public void testPutPerson() throws Exception {
        CatShelterPerson person = new CatShelterPerson();
        person.setChatId(12L);
        person.setName("Hermione");
        HttpEntity<CatShelterPerson> entity = new HttpEntity<CatShelterPerson>(person);

        ResponseEntity<CatShelterPerson> response = restTemplate.exchange("http://localhost:" + port +
                        "/catShelter/person", HttpMethod.PUT, entity, CatShelterPerson.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(OK);
        Assertions.assertThat(Objects.requireNonNull(response.getBody()).getName()).isEqualTo("Hermione");
    }
}
