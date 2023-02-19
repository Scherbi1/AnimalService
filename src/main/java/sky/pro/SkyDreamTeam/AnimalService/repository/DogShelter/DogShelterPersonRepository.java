package sky.pro.SkyDreamTeam.AnimalService.repository.DogShelter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sky.pro.SkyDreamTeam.AnimalService.model.DogShelter.DogShelterPerson;
import sky.pro.SkyDreamTeam.AnimalService.model.DogShelter.DogShelterMenu;

public interface DogShelterPersonRepository extends JpaRepository<DogShelterPerson, Long> {

    DogShelterPerson findById(long id);

    DogShelterPerson findByChatId(long id);

    @Query(value = "SELECT bot_menu FROM person where chat_id=:ChatId", nativeQuery = true)
    DogShelterMenu getBotMenuByChatId(long ChatId);
//Заставить работать что быбыстрее было
//    @Query(value = "UPDATE person SET bot_menu=:menuNumber WHERE chat_id=:ChatId", nativeQuery = true)
//    DogShelterMenuService setBotMenuByChatId(long ChatId,int menuNumber);


}
