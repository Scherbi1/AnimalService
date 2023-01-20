package sky.pro.SkyDreamTeam.AnimalService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sky.pro.SkyDreamTeam.AnimalService.model.Information;

public interface InformationRepository extends JpaRepository<Information, Long> {
}
