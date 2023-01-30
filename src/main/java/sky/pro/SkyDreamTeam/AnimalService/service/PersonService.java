package sky.pro.SkyDreamTeam.AnimalService.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import sky.pro.SkyDreamTeam.AnimalService.model.BotMenu;
import sky.pro.SkyDreamTeam.AnimalService.model.Person;
import sky.pro.SkyDreamTeam.AnimalService.repository.PersonRepository;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    Logger logger = LoggerFactory.getLogger(PersonService.class);

    public Person creatPerson(Person person) {
        logger.info("Was invoked method for creatPerson");
        return personRepository.save(person);
    }

    public Person editPerson(Person person) {
        logger.info("Was invoked method for editPerson");
        if (personRepository.findByChatId(person.getChatId()) == null) {
            logger.error("There is no such person");
            return null;
        }
        logger.debug("Person id {} is edited", person.getChatId());
        return personRepository.save(person);
    }
    public Person findPersonByChatId(long id) {
        logger.info("Was invoked method for findPerson");
        return personRepository.findByChatId(id);
    }

    public BotMenu getBotMenuByChatId(long chatId) {
        logger.info("Was invoked method for getBotMenuByChatId");
        return personRepository.getBotMenuByChatId(chatId);
    }


   public BotMenu setBotMenuByChatId(long chatId,BotMenu botMenu) {
       logger.info("Was invoked method for setBotMenuForChatId");
        Person person=findPersonByChatId(chatId);
        person.setBotMenu(botMenu);
        editPerson(person);
        logger.debug("setBotMenuByChatId id {} is edited", botMenu);
        return botMenu;
    }
}
