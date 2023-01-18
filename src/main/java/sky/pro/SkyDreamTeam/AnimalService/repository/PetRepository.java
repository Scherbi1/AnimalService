package sky.pro.SkyDreamTeam.AnimalService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sky.pro.SkyDreamTeam.AnimalService.Model.Pet;

public interface PetRepository extends JpaRepository<Pet, Long> {
}
