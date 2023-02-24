package sky.pro.SkyDreamTeam.AnimalService.repository.DogShelter;

import org.springframework.data.jpa.repository.JpaRepository;
import sky.pro.SkyDreamTeam.AnimalService.model.DogShelter.DogShelterPet;


import java.util.List;
import java.util.Optional;

public interface DogShelterPetRepository extends JpaRepository<DogShelterPet, Long> {
    Optional<DogShelterPet> findDogShelterPetByName (String name);
    List<DogShelterPet> findDogShelterPetByBreed (String breed);
}
