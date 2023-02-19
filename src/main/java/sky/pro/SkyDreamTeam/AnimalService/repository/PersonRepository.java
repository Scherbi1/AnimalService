package sky.pro.SkyDreamTeam.AnimalService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sky.pro.SkyDreamTeam.AnimalService.model.DogShelter.DogShelterMenu;
import sky.pro.SkyDreamTeam.AnimalService.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

    Person findById(long id);

    Person findByChatId(long id);

    @Query(value = "SELECT bot_menu FROM person where chat_id=:ChatId", nativeQuery = true)
    DogShelterMenu getBotMenuByChatId(long ChatId);
//Заставить работать что быбыстрее было
//    @Query(value = "UPDATE person SET bot_menu=:menuNumber WHERE chat_id=:ChatId", nativeQuery = true)
//    DogShelterMenuService setBotMenuByChatId(long ChatId,int menuNumber);


}
