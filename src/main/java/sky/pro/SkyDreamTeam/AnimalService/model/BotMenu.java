package sky.pro.SkyDreamTeam.AnimalService.model;

public enum BotMenu {
    START("Главное меню", 0),
    INFO("Информация", 1),
    SHELTERINFO("О Нашем приюте", 2),
    CONSULT("Консультация", 3),
    CONTACT("Наши контакты", 4),
    REPORT("Отправить отчет", 5),
    CONNECTTOADMIN("Связаться с волонтером", 6),
    SENDFOTO("Прикрепить фото", 7),
    SENDREPORT("Написать отчет", 8),
    ADMIN("ADMIN", 9),
    PETS("Животные", 10),
    CLIENTS("Клиенты", 11);


    private final String menuDescription;
    private final int menuNumber;

    BotMenu(String menuDescription, int menuNumber) {
        this.menuDescription = menuDescription;
        this.menuNumber = menuNumber;
    }

    public String getMenuDescription() {
        return menuDescription;
    }

    public int getMenuNumber() {
        return menuNumber;
    }
}
//    private final int biomTypeCode;
//    BiomType(int biomTypeCode) {
//        this.biomTypeCode = biomTypeCode;
//    }
//    public int getBiomTypeCode() {
//        return this.biomTypeCode;
//    }
