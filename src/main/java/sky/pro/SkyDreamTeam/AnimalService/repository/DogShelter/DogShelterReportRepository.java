package sky.pro.SkyDreamTeam.AnimalService.repository.DogShelter;

import org.springframework.data.jpa.repository.JpaRepository;
import sky.pro.SkyDreamTeam.AnimalService.model.CatShelter.CatShelterReport;
import sky.pro.SkyDreamTeam.AnimalService.model.DogShelter.DogShelterReport;

import java.util.List;
import java.util.Optional;


public interface DogShelterReportRepository extends JpaRepository<DogShelterReport, Long> {
    void removeAllByChatId(Long chatId);

    List<DogShelterReport> findReportByChatId(Long chatId);
}
