package sky.pro.SkyDreamTeam.AnimalService.model.DogShelter;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class DogShelterPet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String breed;

    public DogShelterPet() {
    }

    public DogShelterPet (String name, String breed) {
        this.name = name;
        this.breed = breed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DogShelterPet that)) return false;
        return getName().equals(that.getName()) && getBreed().equals(that.getBreed());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getBreed());
    }
}
