package sky.pro.SkyDreamTeam.AnimalService.model.CatShelter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.util.Objects;

@Entity
public class CatShelterPerson {
    @Id
    private Long chatId;
    private String name;
    private String phone;
    private String address;
    private CatShelterMenu botMenu;
    @OneToOne
    @JoinColumn(name = "cat_id")
    private CatShelterPet catShelterPet;

    public CatShelterPerson() {

    }


    public CatShelterPerson(Long chatId, String name, String phone, String address, CatShelterMenu botMenu, CatShelterPet catShelterPet) {
        this.chatId = chatId;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.botMenu = botMenu;
        this.catShelterPet = catShelterPet;
    }

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

    public CatShelterMenu getBotMenu() {
        return botMenu;
    }

    public void setBotMenu(CatShelterMenu botMenu) {
        this.botMenu = botMenu;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public CatShelterPet getCatShelterPet() {
        return catShelterPet;
    }

    public void setCatShelterPet(CatShelterPet catShelterPet) {
        this.catShelterPet = catShelterPet;
    }
}
