package sky.pro.SkyDreamTeam.AnimalService.model.CatShelter;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import sky.pro.SkyDreamTeam.AnimalService.model.DogShelter.DogShelterPerson;

import javax.persistence.*;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Slf4j
public class CatShelterPet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String breed;
    @OneToOne
    @JoinColumn(name = "chat_id")
    private CatShelterPerson catShelterPerson;



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

