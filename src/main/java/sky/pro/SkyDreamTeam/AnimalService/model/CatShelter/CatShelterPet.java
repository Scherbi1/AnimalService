package sky.pro.SkyDreamTeam.AnimalService.model.CatShelter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class CatShelterPet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String breed;

    public CatShelterPet() {

    }
    public CatShelterPet(String name, String breed) {
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
        if (o == null || getClass() != o.getClass()) return false;
        CatShelterPet that = (CatShelterPet) o;
        return Objects.equals(name, that.name) && Objects.equals(breed, that.breed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, breed);
    }
}

