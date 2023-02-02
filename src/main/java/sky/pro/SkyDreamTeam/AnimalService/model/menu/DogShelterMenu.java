package sky.pro.SkyDreamTeam.AnimalService.model.menu;

import java.util.Arrays;
import java.util.List;

public enum DogShelterMenu {
    START("Главное меню",
            Arrays.asList("START", "INFO", "REPORT")),
    INFO("Информация",
            Arrays.asList("START", "CONTACT", "SHELTERINFO", "CONNECTTOADMIN")),
    SHELTERINFO("О Нашем приюте",
            Arrays.asList()),
    CONSULT("Консультация",
            Arrays.asList()),
    CONTACT("Наши контакты",
            Arrays.asList()),
    REPORT("Отправить отчет",
            Arrays.asList("START")),
    CONNECTTOADMIN("Связаться с волонтером",
            Arrays.asList("START")),
    SENDFOTO("Прикрепить фото",
            Arrays.asList("START")),
    ADMIN("ADMIN",
            Arrays.asList()),
    PETS("Животные",
            Arrays.asList()),
    CLIENTS("Клиенты",
            Arrays.asList());


    private final String menuDescription;
    private final List<String> menuLink;


    DogShelterMenu(String menuDescription, List<String> menuLink) {
        this.menuDescription = menuDescription;
        this.menuLink = menuLink;
    }


    public String getMenuDescription() {
        return menuDescription;
    }

    public List<String> getMenuLink() {
        return menuLink;
    }
}

