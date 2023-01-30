package sky.pro.SkyDreamTeam.AnimalService.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Person {
    @Id
    private Long chatId;
    private String name;
    private String phone;
    private String address;
    private BotMenu botMenu;
    private Boolean isAdmin;



    public Person() {

    }


    public Person(Long chatId, String name, String phone, String address, BotMenu botMenu, Boolean isAdmin) {
        this.chatId = chatId;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.botMenu = botMenu;
        this.isAdmin = isAdmin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person person)) return false;
        return getChatId().equals(person.getChatId());
    }


    @Override
    public int hashCode() {
        return Objects.hash(getChatId());
    }

    public BotMenu getBotMenu() {
        return botMenu;
    }

    public void setBotMenu(BotMenu botMenu) {
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

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }




}
