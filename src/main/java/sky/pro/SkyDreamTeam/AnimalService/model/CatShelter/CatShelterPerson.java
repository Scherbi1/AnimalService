package sky.pro.SkyDreamTeam.AnimalService.model.CatShelter;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.util.Objects;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Slf4j

public class CatShelterPerson {
    @Id
    private Long chatId;
    private String fullName;
    private String phone;

    private String telegram;
    private CatShelterMenu botMenu;
    @OneToOne
    @JoinColumn(name = "cat_id")
    private CatShelterPet catShelterPet;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CatShelterPerson person)) return false;
        return getChatId().equals(person.getChatId());
    }


    @Override
    public int hashCode() {
        return Objects.hash(getChatId());
    }


}
