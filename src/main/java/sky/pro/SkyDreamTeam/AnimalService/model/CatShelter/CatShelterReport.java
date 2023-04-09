package sky.pro.SkyDreamTeam.AnimalService.model.CatShelter;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import sky.pro.SkyDreamTeam.AnimalService.model.Image;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Slf4j
public class CatShelterReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long chatId;
    private LocalDateTime date;
    private String message;


    @OneToOne(fetch = FetchType.LAZY)
    private Image image;

}
