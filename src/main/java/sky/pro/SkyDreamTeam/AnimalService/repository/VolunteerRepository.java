package sky.pro.SkyDreamTeam.AnimalService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sky.pro.SkyDreamTeam.AnimalService.Model.Volunteer;

public interface VolunteerRepository extends JpaRepository<Volunteer, Long> {
}
