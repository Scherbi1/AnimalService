package sky.pro.SkyDreamTeam.AnimalService.model.DogShelter;

import java.util.Arrays;
import java.util.List;

public enum DogShelterMenu {
    START_D("Главное меню",
            Arrays.asList("START_D",
                    "INFO_D",
                    "GET_PET_D",
                    "REPORT_D",
                    "CONNECTTOADMIN_D")),
    INFO_D("Информация",
            Arrays.asList("START_D",
                    "SHELTERINFO_D",
                    "CONTACT_D",
                    "SECURITY_D",
                    "GET_CLIENTCONTACT_D",
                    "CONNECTTOADMIN_D")),
    SHELTERINFO_D("О Нашем приюте",
            Arrays.asList()),
    SECURITY_D("Безопастность",
            Arrays.asList()),
    CONTACT_D("Наши контакты",
            Arrays.asList()),
    GET_CLIENTCONTACT_D("Напишите Ваш Телефон",
            Arrays.asList("START_D")),
    CONNECTTOADMIN_D("Связаться с волонтером",
            Arrays.asList("START_D")),
    GET_PET_D("Взять Собаку",
            Arrays.asList("START_D",
                    "GET_PET_RULES_D",
                    "GET_PET_DOCS_D",
                    "GET_PET_RECOM_D",
                    "GET_PET_REJECT_D",
                    "GET_CLIENTCONTACT_D",
                    "CONNECTTOADMIN_D")),
    GET_PET_RULES_D("Правила знакомства с собакой",
            Arrays.asList()),
    GET_PET_DOCS_D("Список документов",
            Arrays.asList()),
    GET_PET_RECOM_D("Рекомендации",
            Arrays.asList("START_D","GET_PET_RECOM_TRANSPORT_D",
                    "GET_PET_RECOM_HOUSE_D",
                    "GET_PET_RECOM_HOUSE_PUPPY_D",
                    "GET_PET_RECOM_HOUSE_INVALIDCAT_D",
                    "GET_PET_RECOM_KINOLOG_D")),
    GET_PET_RECOM_TRANSPORT_D("Транспортировка собак",
            Arrays.asList()),
    GET_PET_RECOM_HOUSE_D("Дом для собаки",
            Arrays.asList()),
    GET_PET_RECOM_HOUSE_PUPPY_D("Дом для щенка",
            Arrays.asList()),
    GET_PET_RECOM_HOUSE_INVALIDCAT_D("Дом для собаки с инвалидностью",
            Arrays.asList()),
    GET_PET_RECOM_KINOLOG_D("Рекоммендации кинолога",
            Arrays.asList()),
    GET_PET_REJECT_D("Причины отказа",
            Arrays.asList()),
    REPORT_D("Отправить отчет",
            Arrays.asList("START_D")),
    SENDFOTO_D("Прикрепить фото",
            Arrays.asList("START_D"));



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

