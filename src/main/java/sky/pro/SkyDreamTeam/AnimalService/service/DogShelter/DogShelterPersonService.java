package sky.pro.SkyDreamTeam.AnimalService.service.DogShelter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import sky.pro.SkyDreamTeam.AnimalService.model.DogShelter.DogShelterPerson;
import sky.pro.SkyDreamTeam.AnimalService.model.Person;
import sky.pro.SkyDreamTeam.AnimalService.model.menu.DogShelterMenu;
import sky.pro.SkyDreamTeam.AnimalService.repository.DogShelter.DogShelterPersonRepository;
import sky.pro.SkyDreamTeam.AnimalService.repository.PersonRepository;

@Service
public class DogShelterPersonService {

    private final DogShelterPersonRepository personRepository;

    public DogShelterPersonService(DogShelterPersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    Logger logger = LoggerFactory.getLogger(DogShelterPersonService.class);

    public DogShelterPerson createPerson(DogShelterPerson person) {
        logger.info("Was invoked method for creatPerson");
        return personRepository.save(person);
    }

    public DogShelterPerson editPerson(DogShelterPerson person) {
        logger.info("Was invoked method for editPerson");
        if (personRepository.findByChatId(person.getChatId()) == null) {
            logger.error("There is no such person");
            return null;
        }
        logger.debug("Person id {} is edited", person.getChatId());
        return personRepository.save(person);
    }

    public DogShelterPerson findPersonByChatId(long id) {
        logger.info("Was invoked method for findPerson");
        return personRepository.findByChatId(id);
    }

    public void deletePerson(long id) {
        personRepository.deleteById(id);
    }

    public DogShelterMenu getBotMenuByChatId(long chatId) {
        logger.info("Was invoked method for getBotMenuByChatId");
        return personRepository.getBotMenuByChatId(chatId);
    }


    public DogShelterMenu setBotMenuByChatId(long chatId, DogShelterMenu botMenu) {
        logger.info("Was invoked method for setBotMenuForChatId");
        DogShelterPerson person = findPersonByChatId(chatId);
        person.setBotMenu(botMenu);
        editPerson(person);
        logger.debug("setBotMenuByChatId id {} is edited", botMenu);
        return botMenu;
    }
}
