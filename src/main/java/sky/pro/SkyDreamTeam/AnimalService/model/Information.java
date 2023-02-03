package sky.pro.SkyDreamTeam.AnimalService.model;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class Information {
    @Id
    private String key;
    private String information;

    public Information(String key, String information) {
        this.key = key;
        this.information = information;
    }

    public Information() {
    }

    public String getKey() {
        return key;
    }

    public String getInformation() {
        return information;
    }
}
