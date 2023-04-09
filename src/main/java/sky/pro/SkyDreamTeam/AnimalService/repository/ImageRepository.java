package sky.pro.SkyDreamTeam.AnimalService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sky.pro.SkyDreamTeam.AnimalService.model.Image;

public interface ImageRepository extends JpaRepository<Image,Long> {
    Image findByDescription(String discription);

}
