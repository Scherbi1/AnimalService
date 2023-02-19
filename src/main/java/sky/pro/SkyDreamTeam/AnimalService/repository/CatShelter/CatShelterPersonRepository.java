package sky.pro.SkyDreamTeam.AnimalService.repository.CatShelter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sky.pro.SkyDreamTeam.AnimalService.model.CatShelter.CatShelterPerson;
import sky.pro.SkyDreamTeam.AnimalService.model.CatShelter.CatShelterMenu;


public interface CatShelterPersonRepository extends JpaRepository<CatShelterPerson, Long> {
    CatShelterPerson findById(long id);

    CatShelterPerson findByChatId(long id);

    @Query(value = "SELECT bot_menu FROM cat_shelter_person where chat_id=:ChatId", nativeQuery = true)
    CatShelterMenu getBotMenuByChatId(long ChatId);

    CatShelterPerson save(CatShelterPerson person);

}
