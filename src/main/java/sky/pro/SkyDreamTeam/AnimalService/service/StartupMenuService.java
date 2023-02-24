package sky.pro.SkyDreamTeam.AnimalService.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sky.pro.SkyDreamTeam.AnimalService.model.StartupMenu;
import sky.pro.SkyDreamTeam.AnimalService.service.CatShelter.CatShelterMenuService;
import sky.pro.SkyDreamTeam.AnimalService.service.DogShelter.DogShelterMenuService;

import java.util.List;
import static sky.pro.SkyDreamTeam.AnimalService.model.StartupMenu.*;

@Service
public class StartupMenuService {
    @Autowired
    private TelegramBot telegramBot;

    private  final DogShelterMenuService dogShelterMenuService;
    private final CatShelterMenuService catShelterMenuService;

    private Logger logger = LoggerFactory.getLogger(TelegramBotService.class);
    private StartupMenu startupMenu = STARTUP;


    public StartupMenuService(DogShelterMenuService dogShelterMenuService, CatShelterMenuService catShelterMenuService) {
        this.dogShelterMenuService = dogShelterMenuService;
        this.catShelterMenuService = catShelterMenuService;
    }


    void selectMenu(Update update) {
        logger.info("selectMenu process {}", update);
        long chatId = (update.message().chat().id());
        String updateMessage = update.message().text();

        if (updateMessage != null) {

            if (presentInMenuLink(startupMenu, CATSHELTER) &&
                    updateMessage.equals(CATSHELTER.getMenuDescription())) {
                sendCatShelterMassage(update);
                return;
            }

            if (presentInMenuLink(startupMenu, DOGSHELTER) &&
                    updateMessage.equals(DOGSHELTER.getMenuDescription())) {
                sendDogShelterMassage(update);
                return;
            }
            if (startupMenu.equals(STARTUP)) {
                sendStartupMassage(chatId);
                return;
            }

        }

    }

    private void sendStartupMassage(long chatId) {
        String message = "Выберите приют";
        creatKeybordFromList(chatId, message, STARTUP);
        logger.info("sendStartupMassage to chatId: {}", chatId);
    }

    private void sendDogShelterMassage(Update update) {
        dogShelterMenuService.createNewPerson(update);
        logger.info("sendDogShelterMassage to chatId: {}", update);
    }

    private void sendCatShelterMassage(Update update) {
        catShelterMenuService.createNewPerson(update);
        logger.info("sendCatShelterMassage to chatId: {}", update);
    }


    private boolean presentInMenuLink(StartupMenu botMenu, StartupMenu findMenu) {
        List<String> menuLink = botMenu.getMenuLink();
        return menuLink.contains(findMenu.toString());
    }
    private void creatKeybordFromList(long chatId, String message, StartupMenu botMenu) {
        List<String> keybordList = botMenu.getMenuLink();
        int keybordArraySize = keybordList.size();
        String[][] keybord = new String[keybordArraySize][1];
        for (int i = 0; i < keybordArraySize; i++) {
            StartupMenu button = StartupMenu.valueOf(keybordList.get(i));
            keybord[i][0] = button.getMenuDescription();
        }
        telegramBot.execute(new SendMessage(chatId, message)
                .replyMarkup(new ReplyKeyboardMarkup(keybord)));
    }
}