package sky.pro.SkyDreamTeam.AnimalService.model.DogShelter;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import sky.pro.SkyDreamTeam.AnimalService.model.CatShelter.CatShelterPet;

import javax.persistence.*;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Slf4j
public class DogShelterPet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String breed;

    @OneToOne
    @JoinColumn(name = "chat_id")
    private DogShelterPerson dogShelterPerson;



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
