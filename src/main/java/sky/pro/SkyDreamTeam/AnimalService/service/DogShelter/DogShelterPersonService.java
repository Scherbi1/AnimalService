package sky.pro.SkyDreamTeam.AnimalService.service.DogShelter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import sky.pro.SkyDreamTeam.AnimalService.model.DogShelter.DogShelterPerson;
import sky.pro.SkyDreamTeam.AnimalService.model.DogShelter.DogShelterMenu;
import sky.pro.SkyDreamTeam.AnimalService.repository.DogShelter.DogShelterPersonRepository;

@Service
public class DogShelterPersonService {

    private final DogShelterPersonRepository dogShelterPersonRepository;

    public DogShelterPersonService(DogShelterPersonRepository dogShelterPersonRepository) {
        this.dogShelterPersonRepository = dogShelterPersonRepository;
    }

    Logger logger = LoggerFactory.getLogger(DogShelterPersonService.class);

    public DogShelterPerson createPerson(DogShelterPerson person) {
        logger.info("Dog Shelter Was invoked method for creatPerson");
        return dogShelterPersonRepository.save(person);
    }

    public DogShelterPerson editPerson(DogShelterPerson person) {
        logger.info("Dog Shelter Was invoked method for editPerson");
        if (dogShelterPersonRepository.findByChatId(person.getChatId()) == null) {
            logger.error("There is no such person");
            return null;
        }
        logger.debug("Dog Shelter Person id {} is edited", person.getChatId());
        return dogShelterPersonRepository.save(person);
    }

    public DogShelterPerson findPersonByChatId(long id) {
        logger.info("Dog Shelter Was invoked method for findPerson");
        return dogShelterPersonRepository.findByChatId(id);
    }

    public void deletePerson(long id) {
        dogShelterPersonRepository.deleteById(id);
    }

    @Cacheable("dogShelterMenus")
    public DogShelterMenu getBotMenuByChatId(long chatId) {
        logger.info("Dog Shelter Was invoked method for getBotMenuByChatId");
        return dogShelterPersonRepository.getBotMenuByChatId(chatId);
    }


    @CachePut(value = "dogShelterMenus",key = "#dogShelterPerson.chatId")
    public DogShelterMenu setBotMenuByChatId(long chatId, DogShelterMenu botMenu) {
        logger.info("Dog Shelter Was invoked method for setBotMenuForChatId");
        DogShelterPerson person = findPersonByChatId(chatId);
        person.setBotMenu(botMenu);
        editPerson(person);
        logger.debug("Dog Shelter setBotMenuByChatId id {} is edited", botMenu);
        return botMenu;
    }


    public Long getPetByChatId(long chatId) {
        logger.info("Dog Shelter Was invoked method for getBotMenuByChatId");
        return dogShelterPersonRepository.getPetIdShelterPetByChatId(chatId);
    }
    public String setPhoneByChatId(long chatId, String phone) {
        logger.info("Dog Shelter Was invoked method for setPhoneByChatId");
        DogShelterPerson person=findPersonByChatId(chatId);
        person.setPhone(phone);
        editPerson(person);
        logger.debug("Dog Shelter setPhoneByChatId id {} is edited", phone);
        return phone;
    }
}
