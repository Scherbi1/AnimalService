package sky.pro.SkyDreamTeam.AnimalService.model.DogShelter;

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
public class DogShelterPerson {
    @Id
    private Long chatId;
    private String fullName;
    private String phone;
    private String telegram;
    private DogShelterMenu botMenu;

    @OneToOne
    @JoinColumn(name = "dog_id")
    private DogShelterPet dogShelterPet;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DogShelterPerson person)) return false;
        return getChatId().equals(person.getChatId());
    }


    @Override
    public int hashCode() {
        return Objects.hash(getChatId());
    }


}
