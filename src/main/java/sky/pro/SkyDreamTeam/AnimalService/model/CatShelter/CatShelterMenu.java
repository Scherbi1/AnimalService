package sky.pro.SkyDreamTeam.AnimalService.model.CatShelter;

import java.util.Arrays;
import java.util.List;

public enum CatShelterMenu {
    START_C("Главное меню",
            Arrays.asList("START_C",
                    "INFO_C",
                    "GET_PET_C",
                    "REPORT_C",
                    "CONNECTTOADMIN_C")),
    INFO_C("Информация",
            Arrays.asList("START_C",
                    "SHELTERINFO_C",
                    "CONTACT_C",
                    "SECURITY_C",
                    "GET_CLIENTCONTACT_C",
                    "CONNECTTOADMIN_C")),
    SHELTERINFO_C("О Нашем приюте",
            Arrays.asList()),
    SECURITY_C("Безопастность",
            Arrays.asList()),
    CONTACT_C("Наши контакты",
            Arrays.asList()),
    GET_CLIENTCONTACT_C("Напишите Ваш Телефон",
            Arrays.asList("START_C")),
    CONNECTTOADMIN_C("Связаться с волонтером",
            Arrays.asList()),
    GET_PET_C("Взять Кошку",
            Arrays.asList("START_C",
                    "GET_PET_RULES_C",
                    "GET_PET_DOCS_C",
                    "GET_PET_RECOM_C",
                    "GET_PET_REJECT_C",
                    "GET_CLIENTCONTACT_C",
                    "CONNECTTOADMIN_C")),
    GET_PET_RULES_C("Правила знакомства с кошкой",
            Arrays.asList()),
    GET_PET_DOCS_C("Список документов",
            Arrays.asList()),
    GET_PET_RECOM_C("Рекомендации",
            Arrays.asList("START_C",
                    "GET_PET_RECOM_TRANSPORT_C",
                    "GET_PET_RECOM_HOUSE_C",
                    "GET_PET_RECOM_HOUSE_INVALIDCAT_C")),
    GET_PET_RECOM_TRANSPORT_C("Транспортировка кошек",
            Arrays.asList()),
    GET_PET_RECOM_HOUSE_C("Дом для кошки",
            Arrays.asList()),
    GET_PET_RECOM_HOUSE_INVALIDCAT_C("Дом для кошки с инвалидностью",
            Arrays.asList()),
    GET_PET_REJECT_C("Причины отказа",
            Arrays.asList()),
    REPORT_C("Отправить отчет",
            Arrays.asList("START_C")),
    SENDFOTO_C("Прикрепить фото",
            Arrays.asList("START_C"));


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

