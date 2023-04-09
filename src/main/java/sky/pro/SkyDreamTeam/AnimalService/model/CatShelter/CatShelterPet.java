package sky.pro.SkyDreamTeam.AnimalService.model.CatShelter;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import sky.pro.SkyDreamTeam.AnimalService.model.Image;

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
    private String petInfo;

    @OneToOne(fetch = FetchType.LAZY)
    private Image image;

    @OneToOne
    @JoinColumn(name = "chat_id")
    private CatShelterPerson catShelterPerson;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CatShelterPet pet)) return false;
        return getId().equals(pet.getId());
    }


    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }


}

