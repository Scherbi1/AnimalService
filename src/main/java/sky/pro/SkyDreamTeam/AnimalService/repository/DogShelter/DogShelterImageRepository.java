package sky.pro.SkyDreamTeam.AnimalService.repository.DogShelter;

import org.springframework.data.jpa.repository.JpaRepository;
import sky.pro.SkyDreamTeam.AnimalService.model.DogShelter.DogShelterImage;
import sky.pro.SkyDreamTeam.AnimalService.model.Image;

import java.util.Optional;

public interface DogShelterImageRepository extends JpaRepository<DogShelterImage,Long> {
    Optional<DogShelterImage> findByDescription(String description);

}
