package sky.pro.SkyDreamTeam.AnimalService.service.DogShelter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sky.pro.SkyDreamTeam.AnimalService.model.DogShelter.DogShelterPet;
import sky.pro.SkyDreamTeam.AnimalService.repository.DogShelter.DogShelterPetRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DogShelterPetServiceTest {
    @Mock
    private DogShelterPetRepository repository;
    @InjectMocks
    private DogShelterPetService out;

    DogShelterPet testPet = new DogShelterPet(1L, "TestName", "TestBreed", null);

    Long id = 1L;
    String name = "TestName";

    @Test
    public void returnsAddedPet() {
        when(repository.save(testPet)).thenReturn(testPet);
        assertEquals(testPet, out.createPet(testPet));
    }

    @Test
    public void returnsEditedPet() {
        when(repository.save(testPet)).thenReturn(testPet);
        assertEquals(testPet, out.editPet(testPet));
    }

    @Test
    public void returnsFoundPet() {
        when(repository.findDogShelterPetByName(name)).thenReturn(Optional.ofNullable(testPet));
        assertEquals(testPet, out.findPetByName(name));
    }
}
