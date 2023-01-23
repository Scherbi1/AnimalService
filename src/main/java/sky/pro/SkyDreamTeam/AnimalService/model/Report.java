package sky.pro.SkyDreamTeam.AnimalService.model;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long chatId;
    private LocalDateTime date;
    private String diet;
    private String health;
    private String habits;
    //переменные для загрузки фото:
    private String filePath;
    private long fileSize;
    private String mediaType;
    private byte[] data;
}
