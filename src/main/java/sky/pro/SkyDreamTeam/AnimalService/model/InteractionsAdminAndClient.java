package sky.pro.SkyDreamTeam.AnimalService.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class InteractionsAdminAndClient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long chatId;
    private boolean notification;
    private String text;
    private String login;
    private Date timeStamp;



    public InteractionsAdminAndClient(Long id,Date timeStamp, Long chatId, boolean notification, String text, String login) {
        this.id = id;
        this.chatId = chatId;
        this.notification = notification;
        this.text = text;
        this.login = login;
        this.timeStamp = timeStamp;

    }
    public InteractionsAdminAndClient() {

    }

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

    public boolean isNotification() {
        return notification;
    }

    public void setNotification(boolean notification) {
        this.notification = notification;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }
}
