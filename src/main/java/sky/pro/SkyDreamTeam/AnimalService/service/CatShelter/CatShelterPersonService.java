package sky.pro.SkyDreamTeam.AnimalService.service.CatShelter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import sky.pro.SkyDreamTeam.AnimalService.model.CatShelter.CatShelterPerson;
import sky.pro.SkyDreamTeam.AnimalService.model.CatShelter.CatShelterMenu;
import sky.pro.SkyDreamTeam.AnimalService.repository.CatShelter.CatShelterPersonRepository;
import sky.pro.SkyDreamTeam.AnimalService.service.TelegramBotService;


@Service
public class CatShelterPersonService {

   private final CatShelterPersonRepository catShelterPersonRepository;
    private Logger logger = LoggerFactory.getLogger(TelegramBotService.class);

    public CatShelterPersonService(CatShelterPersonRepository catShelterPersonRepository) {
        this.catShelterPersonRepository = catShelterPersonRepository;
    }



    public CatShelterPerson creatPerson(CatShelterPerson person) {
        logger.info("Was invoked method for creatPerson");
        return catShelterPersonRepository.save(person);
    }

    public void deleteByChatId(long chatId) {
        logger.info("Was invoked method for creatPerson");
        catShelterPersonRepository.deleteById(chatId);
    }

    public CatShelterPerson editPerson(CatShelterPerson person) {
        logger.info("Was invoked method for editPerson");
        if (catShelterPersonRepository.findByChatId(person.getChatId()) == null) {
            logger.error("There is no such person");
            return null;
        }
        logger.debug("Person id {} is edited", person.getChatId());
        return catShelterPersonRepository.save(person);
    }
    public CatShelterPerson findPersonByChatId(long id) {
        logger.info("Was invoked method for findPerson");
        return catShelterPersonRepository.findByChatId(id);
    }

    public CatShelterMenu getBotMenuByChatId(long chatId) {
        logger.info("Was invoked method for getBotMenuByChatId");
        return catShelterPersonRepository.getBotMenuByChatId(chatId);
    }


    public CatShelterMenu setBotMenuByChatId(long chatId, CatShelterMenu botMenu) {
        logger.info("Was invoked method for setBotMenuForChatId");
        CatShelterPerson person=findPersonByChatId(chatId);
        person.setBotMenu(botMenu);
        editPerson(person);
        logger.debug("setBotMenuByChatId id {} is edited", botMenu);
        return botMenu;
    }

    public String setPhoneByChatId(long chatId, String phone) {
        logger.info("Was invoked method for Cat Shelter setPhoneByChatId");
        CatShelterPerson person=findPersonByChatId(chatId);
        person.setPhone(phone);
        editPerson(person);
        logger.debug("Cat Shelter setPhoneByChatId id {} is edited", phone);
        return phone;
    }
}
