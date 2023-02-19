package sky.pro.SkyDreamTeam.AnimalService.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sky.pro.SkyDreamTeam.AnimalService.model.menu.DogShelterMenu;
import sky.pro.SkyDreamTeam.AnimalService.model.Person;
import sky.pro.SkyDreamTeam.AnimalService.service.PersonService;

@RestController
@RequestMapping(path = "person")
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }


    Logger logger = LoggerFactory.getLogger(PersonService.class);

    @PostMapping
    public Person creatPerson(@RequestBody Person person) {

        logger.debug("Person id {} is created", person.getChatId());
        return personService.creatPerson(person);
    }

    /*@DeleteMapping
    public ResponseEntity<Person> deletePerson(@RequestParam long id) {
        logger.debug("Person id {} is delete");
        personService.deletePerson(id);
        return ResponseEntity.ok().build();
    }*/

    @GetMapping
    public ResponseEntity<Person> findPerson(@RequestParam long id) {
        logger.debug("Person id {} is find");
        Person foundPerson = personService.findPersonByChatId(id);
        return ResponseEntity.ok(foundPerson);

    }
    @PutMapping
    public ResponseEntity<Person> editPerson(@RequestBody Person person) {
        Person foundPerson = personService.editPerson(person);
        if (foundPerson == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        logger.debug("Person id {} is edited", person.getChatId());
        return ResponseEntity.ok(foundPerson);
    }

    @GetMapping(path = "getBotMenu/ByChatId/{chatId}")
    public ResponseEntity<DogShelterMenu> getBotMenuByChatId(@PathVariable long chatId) {
        return ResponseEntity.ok(personService.getBotMenuByChatId(chatId));
    }


}
