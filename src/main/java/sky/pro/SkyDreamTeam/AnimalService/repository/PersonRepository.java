package sky.pro.SkyDreamTeam.AnimalService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sky.pro.SkyDreamTeam.AnimalService.model.BotMenu;
import sky.pro.SkyDreamTeam.AnimalService.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

    Person findById(long id);

    Person findByChatId(long id);

    @Query(value = "SELECT bot_menu FROM person where chat_id=:ChatId", nativeQuery = true)
    BotMenu getBotMenuByChatId(long ChatId);
//Заставить работать что быбыстрее было
//    @Query(value = "UPDATE person SET bot_menu=:menuNumber WHERE chat_id=:ChatId", nativeQuery = true)
//    BotMenu setBotMenuByChatId(long ChatId,int menuNumber);


}
