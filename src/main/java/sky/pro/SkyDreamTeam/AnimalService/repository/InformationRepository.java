package sky.pro.SkyDreamTeam.AnimalService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sky.pro.SkyDreamTeam.AnimalService.model.Information;

public interface InformationRepository extends JpaRepository<Information, String> {
    Information findByQuestion(String question);

    @Query(value = "SELECT answer FROM information where question=:key", nativeQuery = true)
    String getMessageByKey(String key);
}
