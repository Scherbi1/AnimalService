package sky.pro.SkyDreamTeam.AnimalService.model;

import java.time.LocalDateTime;
import javax.persistence.*;

@Entity
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long chatId;
    private LocalDateTime date;
    private String message;

    public Report(Long id, Long chatId, LocalDateTime date, String message, Image image) {
        this.id = id;
        this.chatId = chatId;
        this.date = date;
        this.message = message;
        this.image = image;
    }

    public Report() {

    }

    @OneToOne
    @JoinColumn(name = "discription")
    private Image image;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
