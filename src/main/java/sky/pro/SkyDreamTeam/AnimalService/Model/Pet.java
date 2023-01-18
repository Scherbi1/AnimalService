package sky.pro.SkyDreamTeam.AnimalService.Model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String breed;
    //переменные для загрузки фото:
    private String filePath;
    private long fileSize;
    private String mediaType;
    private byte[] data;
}
