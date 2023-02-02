package sky.pro.SkyDreamTeam.AnimalService.model.menu;

import java.util.Arrays;
import java.util.List;

public enum CatShelterMenu {
    START_C("Главное меню",
            Arrays.asList("START_C", "INFO_C", "REPORT_C")),
    INFO_C("Информация",
            Arrays.asList("START_C", "CONTACT_C", "SHELTERINFO_C", "CONNECTTOADMIN_C")),
    SHELTERINFO_C("О Нашем приюте",
            Arrays.asList()),
    CONSULT_C("Консультация",
            Arrays.asList()),
    CONTACT_C("Наши контакты",
            Arrays.asList()),
    REPORT_C("Отправить отчет",
            Arrays.asList("START_C")),
    CONNECTTOADMIN_C("Связаться с волонтером",
            Arrays.asList("START_C")),
    SENDFOTO_C("Прикрепить фото",
            Arrays.asList("START_C")),
    ADMIN_C("ADMIN",
            Arrays.asList()),
    PETS_C("Животные",
            Arrays.asList()),
    CLIENTS_C("Клиенты",
            Arrays.asList()),
    NONE_C("Заглушка",
              Arrays.asList("NONE_C","NONE_C"));


    private final String menuDescription;
    private final List<String> menuLink;


    CatShelterMenu(String menuDescription, List<String> menuLink) {
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

