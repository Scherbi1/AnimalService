package sky.pro.SkyDreamTeam.AnimalService.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long chatId;
    private String name;
    private String phone;
    private String address;
    //private Enum lastMenu;Если пользователь уже обращался к боту ранее, то новое обращение начинается с выбора запроса, с которым пришел пользователь.
    private boolean isVolunteer;
}
