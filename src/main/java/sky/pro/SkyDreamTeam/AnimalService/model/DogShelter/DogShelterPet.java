package sky.pro.SkyDreamTeam.AnimalService.model.DogShelter;
import sky.pro.SkyDreamTeam.AnimalService.model.CatShelter.CatShelterPet;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class DogShelterPet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String breed;

    @OneToOne
    @JoinColumn(name = "chat_id")
    private DogShelterPerson dogShelterPerson;
    public DogShelterPet() {
    }

    public DogShelterPet(Long id, String name, String breed, DogShelterPerson dogShelterPerson) {
        this.id = id;
        this.name = name;
        this.breed = breed;
        this.dogShelterPerson = dogShelterPerson;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DogShelterPerson getDogShelterPerson() {
        return dogShelterPerson;
    }

    public void setDogShelterPerson(DogShelterPerson dogShelterPerson) {
        this.dogShelterPerson = dogShelterPerson;
    }
}
