package sky.pro.SkyDreamTeam.AnimalService.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sky.pro.SkyDreamTeam.AnimalService.Model.Information;

public interface InformationRepository extends JpaRepository<Information, Long> {
}
