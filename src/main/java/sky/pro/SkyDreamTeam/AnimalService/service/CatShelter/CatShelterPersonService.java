package sky.pro.SkyDreamTeam.AnimalService.service.CatShelter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import sky.pro.SkyDreamTeam.AnimalService.model.CatShelter.CatShelterPerson;
import sky.pro.SkyDreamTeam.AnimalService.model.CatShelter.CatShelterMenu;
import sky.pro.SkyDreamTeam.AnimalService.repository.CatShelter.CatShelterPersonRepository;
import sky.pro.SkyDreamTeam.AnimalService.repository.CatShelter.CatShelterPetRepository;
import sky.pro.SkyDreamTeam.AnimalService.service.TelegramBotService;


@Service
public class CatShelterPersonService {

    private final CatShelterPersonRepository catShelterPersonRepository;
    private Logger logger = LoggerFactory.getLogger(TelegramBotService.class);

    public CatShelterPersonService(CatShelterPersonRepository catShelterPersonRepository) {
        this.catShelterPersonRepository = catShelterPersonRepository;
    }


    /**
     * Этот метод создает нового пользователя в базе пользователей приюта для кошек.
     * <br> Используется метод репозитория {@link JpaRepository#save(Object)}
     *
     * @param person данные о пользователе в формате JSON
     * @return данные о пользователе в формате JSON, которые были сохранены в БД
     */
    public CatShelterPerson createPerson(CatShelterPerson person) {
        logger.info("Cat shelter Was invoked method for createPerson");
        return catShelterPersonRepository.save(person);
    }

    /**
     * Этот метод удаляет запись о пользователя в базе пользователей приюта для кошек по chatId из Телеграм.
     * <br> Используется метод репозитория {@link CatShelterPersonRepository#deleteById(Object)}
     *
     * @param chatId chatId пользователя из Телеграм
     */
    public void deleteByChatId(long chatId) {
        logger.info("Cat shelter Was invoked method for delete Person by chatID");
        catShelterPersonRepository.deleteById(chatId);
    }

    /**
     * Этот метод редактирует запись о пользователе в базе данных приюта для кошек.
     * <br> Используется метод репозитория {@link JpaRepository#save(Object)}
     *
     * @param person данные о пользователе в формате JSON
     * @return данные о пользователе в формате JSON, которые были сохранены в БД
     */
    public CatShelterPerson editPerson(CatShelterPerson person) {
        logger.info("Cat shelter Was invoked method for editPerson");
        if (catShelterPersonRepository.findByChatId(person.getChatId()) == null) {
            logger.error("Cat shelter There is no such person");
            return null;
        }
        logger.debug("Cat shelter Person id {} is edited", person.getChatId());
        return catShelterPersonRepository.save(person);
    }

    /**
     * Этот метод позволяет найти запись о пользователе в базе данных приюта для кошек по chatId из Телеграм.
     * <br> Используется метод репозитория {@link CatShelterPersonRepository#findByChatId(long)}
     *
     * @param id chatId пользователя из Телеграм
     * @return данные о пользователе в формате JSON, которые были сохранены в БД
     */
    public CatShelterPerson findPersonByChatId(long id) {
        logger.info("Cat shelter Was invoked method for findPerson");
        return catShelterPersonRepository.findByChatId(id);
    }

    /**
     * Этот метод возвращает уровень меню из Телеграм, в котором находится пользователь.
     * Он используется для работы Телеграм-бота
     * <br> Используется метод репозитория {@link CatShelterPersonRepository#getBotMenuByChatId(long)}
     *
     * @param chatId chatId пользователя из Телеграм
     * @return пункт меню в формате ENUM CatShelterMenu
     */
    @Cacheable("catShelterMenus")
    public CatShelterMenu getBotMenuByChatId(long chatId) {
        logger.info("Cat shelter Was invoked method for getBotMenuByChatId");
        return catShelterPersonRepository.getBotMenuByChatId(chatId);
    }

    /**
     * Этот метод переключает уровень меню из Телеграм, в котором находится пользователь.
     * Он используется для работы Телеграм-бота
     * <br> Используются методы {@link CatShelterPersonService#findPersonByChatId(long)} ,
     * <br> {@link CatShelterPersonService#editPerson(CatShelterPerson)} ,
     * <br> {@link CatShelterPerson#setBotMenu(CatShelterMenu)}
     *
     * @param chatId  chatId пользователя из Телеграм
     * @param botMenu пункт пользователя из Телеграм, который будет задан
     * @return установленный пункт меню в формате ENUM CatShelterMenu
     */
    @CachePut(value = "catShelterMenus", key = "#catShelterPerson.chatId")
    public CatShelterMenu setBotMenuByChatId(long chatId, CatShelterMenu botMenu) {
        logger.info("Cat shelter Was invoked method for setBotMenuForChatId");
        CatShelterPerson person = findPersonByChatId(chatId);
        person.setBotMenu(botMenu);
        editPerson(person);
        logger.debug("Cat shelter setBotMenuByChatId id {} is edited", botMenu);
        return botMenu;
    }

    /**
     * Этот метод возвращает ID кошки, которая находится на попечении пользователя.
     * Он используется для работы с ежедневными отчетами, которые пользователи присылают через Телеграм.
     * <br> Используется метод репозитория {@link CatShelterPersonRepository#getPetIdShelterPetByChatId(long)}
     *
     * @param chatId chatId пользователя из Телеграм
     * @return ID кошки в базе данных
     */
    public Long getPetIdByChatId(long chatId) {
        logger.info("Dog Shelter Was invoked method for getBotMenuByChatId");
        return catShelterPersonRepository.getPetIdShelterPetByChatId(chatId);
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
        logger.info("Cat shelter Was invoked method for Cat Shelter setPhoneByChatId");
        CatShelterPerson person = findPersonByChatId(chatId);
        person.setPhone(phone);
        editPerson(person);
        logger.debug("Cat Shelter setPhoneByChatId id {} is edited", phone);
        return phone;
    }
}
