package sky.pro.SkyDreamTeam.AnimalService.service.DogShelter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import sky.pro.SkyDreamTeam.AnimalService.exceptions.PetNotFoundException;
import sky.pro.SkyDreamTeam.AnimalService.model.DogShelter.DogShelterPet;
import sky.pro.SkyDreamTeam.AnimalService.repository.DogShelter.DogShelterPetRepository;

import javax.transaction.Transactional;
import java.util.Collection;

@Service
@Transactional
public class DogShelterPetService {
    private final DogShelterPetRepository petRepository;

    public DogShelterPetService(DogShelterPetRepository petRepository) {
        this.petRepository = petRepository;
    }

    Logger logger = LoggerFactory.getLogger(DogShelterPetService.class);

    /**
     * Этот метод создает нового питомца в базе данных собак.
     * <br> Используется метод репозитория {@link JpaRepository#save(Object)}
     *
     * @param pet данные о животном в формате JSON
     * @return данные о животном в формате JSON, которые были сохранены в БД
     */
    public DogShelterPet createPet(DogShelterPet pet) {
        logger.info("Dog shelter Was invoked method for createPet");
        return petRepository.save(pet);
    }

    /**
     * Этот метод редактирует запись о питомце в базе данных собак.
     * <br> Используется метод репозитория {@link JpaRepository#save(Object)}
     *
     * @param pet данные о животном в формате JSON
     * @return данные о животном в формате JSON, которые были сохранены в БД
     */
    public DogShelterPet editPet(DogShelterPet pet) {
        return petRepository.save(pet);
    }

    /**
     * Этот метод позволяет найти запись о питомце в базе данных собак по имени.
     * <br> Используется метод репозитория {@link DogShelterPetRepository#findDogShelterPetByName(String)}
     *
     * @param name имя собаки
     * @return данные о животном в формате JSON, которые были сохранены в БД
     */
    public DogShelterPet findPetByName(String name) {
        logger.info("Dog shelter Was invoked method for findPerson");
        return petRepository.findDogShelterPetByName(name).orElseThrow(PetNotFoundException::new);
    }

    /**
     * Этот метод позволяет найти все записи о питомцах в базе данных собак.
     * <br> Используется метод репозитория {@link DogShelterPetRepository#findAll()}
     *
     * @return данные о животном в формате JSON, которые были сохранены в БД
     */
    public Collection<DogShelterPet> findAllDogPets() {
        logger.info("Dog shelter Was invoked method for findAllDogPets");
        return petRepository.findAll();
    }
    /**
     * Этот метод удаляет запись о питомце в базе данных собак по ID.
     * <br> Используется метод репозитория {@link DogShelterPetRepository#deleteById(Object)}
     *
     * @param id идентификатор записи о собаке в базе данных
     */
    public void deletePet(long id) {
        logger.info("Dog shelter Was invoked method for deletePet");
        petRepository.deleteById(id);
    }

    /**
     * Этот метод удаляет запись о питомце в базе данных собак по имени.
     * <br> Используется метод репозитория {@link DogShelterPetRepository#removeByName(String)}
     *
     * @param name имя собаки
     */
    public void deletePet(String name) {
        logger.info("Dog shelter Was invoked method for deletePet");
        petRepository.removeByName(name);
    }


}