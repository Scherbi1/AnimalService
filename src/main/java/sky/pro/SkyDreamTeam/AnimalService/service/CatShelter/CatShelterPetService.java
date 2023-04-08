package sky.pro.SkyDreamTeam.AnimalService.service.CatShelter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import sky.pro.SkyDreamTeam.AnimalService.exceptions.PetNotFoundException;
import sky.pro.SkyDreamTeam.AnimalService.model.CatShelter.CatShelterPet;
import sky.pro.SkyDreamTeam.AnimalService.repository.CatShelter.CatShelterPetRepository;

import javax.transaction.Transactional;
import java.util.Collection;

@Service
@Transactional
public class CatShelterPetService {
    private final CatShelterPetRepository petRepository;

    public CatShelterPetService(CatShelterPetRepository petRepository) {
        this.petRepository = petRepository;
    }

    Logger logger = LoggerFactory.getLogger(CatShelterPetService.class);

    /**
     * Этот метод создает нового питомца в базе данных кошек.
     * <br> Используется метод репозитория {@link JpaRepository#save(Object)}
     *
     * @param pet данные о животном в формате JSON
     * @return данные о животном в формате JSON, которые были сохранены в БД
     */
    public CatShelterPet createPet(CatShelterPet pet) {
        logger.info("Cat shelter Was invoked method for createPet");
        return petRepository.save(pet);
    }

    /**
     * Этот метод редактирует запись о питомце в базе данных кошек.
     * <br> Используется метод репозитория {@link JpaRepository#save(Object)}
     *
     * @param pet данные о животном в формате JSON
     * @return данные о животном в формате JSON, которые были сохранены в БД
     */
    public CatShelterPet editPet(CatShelterPet pet) {
        logger.info("Cat shelter Was invoked method for editPet");
        return petRepository.save(pet);
    }

    /**
     * Этот метод позволяет найти запись о питомце в базе данных кошек по имени.
     * <br> Используется метод репозитория {@link CatShelterPetRepository#findCatShelterPetByName(String)}
     *
     * @param name имя кошки
     * @return данные о животном в формате JSON, которые были сохранены в БД
     */
    public CatShelterPet findPetByName(String name) {
        logger.info("Cat shelter Was invoked method for findPet");
        return petRepository.findCatShelterPetByName(name).orElseThrow(PetNotFoundException::new);
    }

    /**
     * Этот метод позволяет найти все записи о питомцах в базе данных кошек.
     * <br> Используется метод репозитория {@link CatShelterPetRepository#findAll()}
     *
     * @return данные о животных в формате JSON, которые были сохранены в БД
     */
    public Collection<CatShelterPet> findAllCatPets() {
        logger.info("Cat shelter Was invoked method for findAllCatPets");
        return petRepository.findAll();
    }

    /**
     * Этот метод удаляет запись о питомце в базе данных кошек по ID.
     * <br> Используется метод репозитория {@link CatShelterPetRepository#deleteById(Object)}
     *
     * @param id идентификатор записи о кошке в базе данных
     */
    public void deletePet(long id) {
        logger.info("Cat shelter Was invoked method for deletePet");
        petRepository.deleteById(id);
    }

    /**
     * Этот метод удаляет запись о питомце в базе данных кошек по имени.
     * <br> Используется метод репозитория {@link CatShelterPetRepository#removeByName(String)}
     *
     * @param name имя кошки
     */
    public void deletePet(String name) {
        logger.info("Cat shelter Was invoked method for deletePet");
        petRepository.removeByName(name);
    }


}
