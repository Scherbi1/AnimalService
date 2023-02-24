package sky.pro.SkyDreamTeam.AnimalService.model.CatShelter;

import sky.pro.SkyDreamTeam.AnimalService.model.DogShelter.DogShelterPerson;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class CatShelterPet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String breed;
    @OneToOne
    @JoinColumn(name = "chat_id")
    private CatShelterPerson catShelterPerson;

    public CatShelterPet() {

    }

    public CatShelterPet(Long id, String name, String breed, CatShelterPerson catShelterPerson) {
        this.id = id;
        this.name = name;
        this.breed = breed;
        this.catShelterPerson = catShelterPerson;
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

    public CatShelterPerson getCatShelterPerson() {
        return catShelterPerson;
    }

    public void setCatShelterPerson(CatShelterPerson catShelterPerson) {
        this.catShelterPerson = catShelterPerson;
    }
}

