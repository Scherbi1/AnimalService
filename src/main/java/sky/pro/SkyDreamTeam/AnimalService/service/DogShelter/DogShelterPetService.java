package sky.pro.SkyDreamTeam.AnimalService.service.DogShelter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import sky.pro.SkyDreamTeam.AnimalService.exceptions.PetNotFoundException;
import sky.pro.SkyDreamTeam.AnimalService.model.DogShelter.DogShelterPet;
import sky.pro.SkyDreamTeam.AnimalService.repository.DogShelter.DogShelterPetRepository;

import javax.transaction.Transactional;

@Service
@Transactional
public class DogShelterPetService {
    private final DogShelterPetRepository petRepository;

    public DogShelterPetService(DogShelterPetRepository petRepository) {
        this.petRepository = petRepository;
    }

    Logger logger = LoggerFactory.getLogger(DogShelterPetService.class);

    public DogShelterPet createPet(DogShelterPet pet) {
        logger.info("Dog shelter Was invoked method for createPet");
        return petRepository.save(pet);
    }

    public DogShelterPet editPet(DogShelterPet pet) {
        return petRepository.save(pet);
    }

    public DogShelterPet findPetByName(String name) {
        logger.info("Dog shelter Was invoked method for findPerson");
        return petRepository.findDogShelterPetByName(name).orElseThrow(PetNotFoundException::new);
    }

    public void deletePet(long id) {
        logger.info("Dog shelter Was invoked method for deletePet");
        petRepository.deleteById(id);
    }

    public void deletePet(String name) {
        logger.info("Dog shelter Was invoked method for deletePet");
        petRepository.removeByName(name);
    }
}