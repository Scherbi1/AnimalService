package sky.pro.SkyDreamTeam.AnimalService.repository.CatShelter;

import org.springframework.data.jpa.repository.JpaRepository;
import sky.pro.SkyDreamTeam.AnimalService.model.CatShelter.CatShelterReport;

import java.util.List;
import java.util.Optional;

public interface CatShelterReportRepository extends JpaRepository<CatShelterReport, Long> {

    List<CatShelterReport> findReportByChatId(Long chatId);

    void removeAllByChatId(Long chatId);
}
