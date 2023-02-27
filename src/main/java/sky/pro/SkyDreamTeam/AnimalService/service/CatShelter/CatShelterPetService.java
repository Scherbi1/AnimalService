package sky.pro.SkyDreamTeam.AnimalService.service.CatShelter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import sky.pro.SkyDreamTeam.AnimalService.exceptions.PetNotFoundException;
import sky.pro.SkyDreamTeam.AnimalService.model.CatShelter.CatShelterPet;
import sky.pro.SkyDreamTeam.AnimalService.repository.CatShelter.CatShelterPetRepository;

import javax.transaction.Transactional;

@Service
@Transactional
public class CatShelterPetService {
    private final CatShelterPetRepository petRepository;

    public CatShelterPetService(CatShelterPetRepository petRepository) {
        this.petRepository = petRepository;
    }

    Logger logger = LoggerFactory.getLogger(CatShelterPetService.class);

    public CatShelterPet createPet(CatShelterPet pet) {
        logger.info("Cat shelter Was invoked method for createPet");
        return petRepository.save(pet);
    }

    public CatShelterPet editPet(CatShelterPet pet) {
        logger.info("Cat shelter Was invoked method for editPet");
        return petRepository.save(pet);
    }

    public CatShelterPet findPetByName(String name) {
        logger.info("Cat shelter Was invoked method for findPet");
        return petRepository.findCatShelterPetByName(name).orElseThrow(PetNotFoundException::new);
    }

    public void deletePet(long id) {
        logger.info("Cat shelter Was invoked method for deletePet");
        petRepository.deleteById(id);
    }
    public void deletePet(String name) {
        logger.info("Cat shelter Was invoked method for deletePet");
        petRepository.removeByName(name);
    }
}
