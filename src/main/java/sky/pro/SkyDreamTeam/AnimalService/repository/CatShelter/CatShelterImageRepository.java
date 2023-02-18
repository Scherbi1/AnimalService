package sky.pro.SkyDreamTeam.AnimalService.repository.CatShelter;

import org.springframework.data.jpa.repository.JpaRepository;
import sky.pro.SkyDreamTeam.AnimalService.model.CatShelter.CatShelterImage;

import java.util.Optional;

public interface CatShelterImageRepository extends JpaRepository<CatShelterImage, Long> {
    Optional<CatShelterImage> findByDescription(String description);
}
