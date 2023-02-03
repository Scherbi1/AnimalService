package sky.pro.SkyDreamTeam.AnimalService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sky.pro.SkyDreamTeam.AnimalService.model.Information;

public interface InformationRepository extends JpaRepository<Information, Long> {
    Information findLastByQuestion(String key);

    Information findInformationByKey(String key);

    Information deleteInformationByKey(String key);

    @Query(value = "SELECT information FROM information where  key=:key", nativeQuery = true)
    String getMessageByKey(String key);
}
