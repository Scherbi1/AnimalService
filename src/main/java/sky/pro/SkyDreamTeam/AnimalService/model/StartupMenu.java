package sky.pro.SkyDreamTeam.AnimalService.model;

import java.util.Arrays;
import java.util.List;

public enum StartupMenu {
    STARTUP("Меню выбора приюта",
            Arrays.asList("DOGSHELTER", "CATSHELTER")),
    DOGSHELTER("Приют для собак.",
            Arrays.asList()),
    CATSHELTER("Приют для кошек",
            Arrays.asList());


    private final String menuDescription;
    private final List<String> menuLink;


    StartupMenu(String menuDescription, List<String> menuLink) {
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
