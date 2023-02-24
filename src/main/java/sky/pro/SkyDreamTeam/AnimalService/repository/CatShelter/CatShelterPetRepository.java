package sky.pro.SkyDreamTeam.AnimalService.repository.CatShelter;

import org.springframework.data.jpa.repository.JpaRepository;
import sky.pro.SkyDreamTeam.AnimalService.model.CatShelter.CatShelterPet;
import sky.pro.SkyDreamTeam.AnimalService.model.DogShelter.DogShelterPet;

import java.util.List;
import java.util.Optional;

public interface CatShelterPetRepository extends JpaRepository<CatShelterPet, Long> {
    Optional<CatShelterPet> findCatShelterPetByName (String name);
    List<CatShelterPet> findCatShelterPetByBreed (String breed);
}
