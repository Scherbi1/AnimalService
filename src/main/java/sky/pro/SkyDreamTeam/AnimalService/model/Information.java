package sky.pro.SkyDreamTeam.AnimalService.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Information {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
    private String question;
    private String answer;

    public Information(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public Information() {
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }
}
