package sky.pro.SkyDreamTeam.AnimalService.service.DogShelter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import sky.pro.SkyDreamTeam.AnimalService.model.CatShelter.CatShelterMenu;
import sky.pro.SkyDreamTeam.AnimalService.model.CatShelter.CatShelterPerson;
import sky.pro.SkyDreamTeam.AnimalService.model.DogShelter.DogShelterPerson;
import sky.pro.SkyDreamTeam.AnimalService.model.DogShelter.DogShelterMenu;
import sky.pro.SkyDreamTeam.AnimalService.repository.CatShelter.CatShelterPersonRepository;
import sky.pro.SkyDreamTeam.AnimalService.repository.DogShelter.DogShelterPersonRepository;
import sky.pro.SkyDreamTeam.AnimalService.service.CatShelter.CatShelterPersonService;

@Service
public class DogShelterPersonService {

    private final DogShelterPersonRepository dogShelterPersonRepository;

    public DogShelterPersonService(DogShelterPersonRepository dogShelterPersonRepository) {
        this.dogShelterPersonRepository = dogShelterPersonRepository;
    }

    Logger logger = LoggerFactory.getLogger(DogShelterPersonService.class);

    /**
     * Этот метод создает нового пользователя в базе пользователей приюта для собак.
     * <br> Используется метод репозитория {@link JpaRepository#save(Object)}
     *
     * @param person данные о пользователе в формате JSON
     * @return данные о пользователе в формате JSON, которые были сохранены в БД
     */
    public DogShelterPerson createPerson(DogShelterPerson person) {
        logger.info("Dog Shelter Was invoked method for creatPerson");
        return dogShelterPersonRepository.save(person);
    }

    /**
     * Этот метод удаляет запись о пользователя в базе пользователей приюта для собак по chatId из Телеграм.
     * <br> Используется метод репозитория {@link DogShelterPersonRepository#deleteById(Object)}
     *
     * @param chatId chatId пользователя из Телеграм
     */
    public void deleteByChatId(long chatId) {
        logger.info("Cat shelter Was invoked method for delete Person by chatID");
        dogShelterPersonRepository.deleteById(chatId);
    }

    /**
     * Этот метод редактирует запись о пользователе в базе данных приюта для собак.
     * <br> Используется метод репозитория {@link JpaRepository#save(Object)}
     *
     * @param person данные о пользователе в формате JSON
     * @return данные о пользователе в формате JSON, которые были сохранены в БД
     */
    public DogShelterPerson editPerson(DogShelterPerson person) {
        logger.info("Dog Shelter Was invoked method for editPerson");
        if (dogShelterPersonRepository.findByChatId(person.getChatId()) == null) {
            logger.error("There is no such person");
            return null;
        }
        logger.debug("Dog Shelter Person id {} is edited", person.getChatId());
        return dogShelterPersonRepository.save(person);
    }

    /**
     * Этот метод позволяет найти запись о пользователе в базе данных приюта для собак по chatId из Телеграм.
     * <br> Используется метод репозитория {@link DogShelterPersonRepository#findByChatId(long)}
     *
     * @param id chatId пользователя из Телеграм
     * @return данные о пользователе в формате JSON, которые были сохранены в БД
     */
    public DogShelterPerson findPersonByChatId(long id) {
        logger.info("Dog Shelter Was invoked method for findPerson");
        return dogShelterPersonRepository.findByChatId(id);
    }

    /**
     * Этот метод возвращает уровень меню из Телеграм, в котором находится пользователь.
     * Он используется для работы Телеграм-бота
     * <br> Используется метод репозитория {@link DogShelterPersonRepository#getBotMenuByChatId(long)}
     *
     * @param chatId chatId пользователя из Телеграм
     * @return пункт меню в формате ENUM DogShelterMenu
     */
    @Cacheable("dogShelterMenus")
    public DogShelterMenu getBotMenuByChatId(long chatId) {
        logger.info("Dog Shelter Was invoked method for getBotMenuByChatId");
        return dogShelterPersonRepository.getBotMenuByChatId(chatId);
    }

    /**
     * Этот метод переключает уровень меню из Телеграм, в котором находится пользователь.
     * Он используется для работы Телеграм-бота
     * <br> Используются методы {@link DogShelterPersonService#findPersonByChatId(long)} ,
     * <br> {@link DogShelterPersonService#editPerson(DogShelterPerson)}  ,
     * <br> {@link DogShelterPerson#setBotMenu(DogShelterMenu)}
     *
     * @param chatId  chatId пользователя из Телеграм
     * @param botMenu пункт пользователя из Телеграм, который будет задан
     * @return установленный пункт меню в формате ENUM CatShelterMenu
     */
    @CachePut(value = "dogShelterMenus", key = "#dogShelterPerson.chatId")
    public DogShelterMenu setBotMenuByChatId(long chatId, DogShelterMenu botMenu) {
        logger.info("Dog Shelter Was invoked method for setBotMenuForChatId");
        DogShelterPerson person = findPersonByChatId(chatId);
        person.setBotMenu(botMenu);
        editPerson(person);
        logger.debug("Dog Shelter setBotMenuByChatId id {} is edited", botMenu);
        return botMenu;
    }

    /**
     * Этот метод возвращает ID собаки, которая находится на попечении пользователя.
     * Он используется для работы с ежедневными отчетами, которые пользователи присылают через Телеграм.
     * <br> Используется метод репозитория {@link DogShelterPersonRepository#getPetIdShelterPetByChatId(long)}
     *
     * @param chatId chatId пользователя из Телеграм
     * @return ID собаки в базе данных
     */
    public Long getPetByChatId(long chatId) {
        logger.info("Dog Shelter Was invoked method for getBotMenuByChatId");
        return dogShelterPersonRepository.getPetIdShelterPetByChatId(chatId);
    }

    /**
     * Этот метод привязывает к записи о пользователе в БД номер телефона.
     * Он используется, чтобы сохранить телефонный номер пользователя.
     * <br> Используются методы {@link CatShelterPersonService#findPersonByChatId(long)} ,
     * <br> {@link CatShelterPersonService#editPerson(CatShelterPerson)} ,
     * <br> {@link CatShelterPerson#setPhone(String)}
     *
     * @param chatId chatId пользователя из Телеграм
     * @param phone  номер телефона пользоваетля.
     * @return установленный пункт меню в формате ENUM CatShelterMenu
     */
    public String setPhoneByChatId(long chatId, String phone) {
        logger.info("Dog Shelter Was invoked method for setPhoneByChatId");
        DogShelterPerson person = findPersonByChatId(chatId);
        person.setPhone(phone);
        editPerson(person);
        logger.debug("Dog Shelter setPhoneByChatId id {} is edited", phone);
        return phone;
    }
}
