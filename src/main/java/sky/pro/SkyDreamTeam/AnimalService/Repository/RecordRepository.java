package sky.pro.SkyDreamTeam.AnimalService.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sky.pro.SkyDreamTeam.AnimalService.Model.Record;

public interface RecordRepository extends JpaRepository<Record, Long> {
}