package sky.pro.SkyDreamTeam.AnimalService.model.DogShelter;

import sky.pro.SkyDreamTeam.AnimalService.model.CatShelter.CatShelterPet;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.util.Objects;

@Entity
public class DogShelterPerson {
    @Id
    private Long chatId;
    private String name;
    private String phone;
    private String address;
    private DogShelterMenu botMenu;

    @OneToOne
    @JoinColumn(name = "dog_id")
    private DogShelterPet dogShelterPet;


    public DogShelterPerson() {

    }


    public DogShelterPerson(Long chatId, String name, String phone, String address, DogShelterMenu botMenu, DogShelterPet dogShelterPet) {
        this.chatId = chatId;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.botMenu = botMenu;
        this.dogShelterPet = dogShelterPet;
    }

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

    public DogShelterMenu getBotMenu() {
        return botMenu;
    }

    public void setBotMenu(DogShelterMenu botMenu) {
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

    public DogShelterPet getDogShelterPet() {
        return dogShelterPet;
    }

    public void setDogShelterPet(DogShelterPet dogShelterPet) {
        this.dogShelterPet = dogShelterPet;
    }
}
