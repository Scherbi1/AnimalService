package sky.pro.SkyDreamTeam.AnimalService.service.CatShelter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sky.pro.SkyDreamTeam.AnimalService.model.CatShelter.CatShelterPet;
import sky.pro.SkyDreamTeam.AnimalService.repository.CatShelter.CatShelterPetRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class CatShelterPetServiceTest {
    @Mock
    private CatShelterPetRepository repository;
    @InjectMocks
    private CatShelterPetService out;

    CatShelterPet testPet = new CatShelterPet(1L, "TestName", "TestBreed", null);

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
        when(repository.findCatShelterPetByName(name)).thenReturn(Optional.ofNullable(testPet));
        assertEquals(testPet, out.findPetByName(name));
    }
}
