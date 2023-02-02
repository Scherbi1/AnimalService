package sky.pro.SkyDreamTeam.AnimalService.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sky.pro.SkyDreamTeam.AnimalService.model.CatShelterPerson;
import sky.pro.SkyDreamTeam.AnimalService.model.menu.CatShelterMenu;
import sky.pro.SkyDreamTeam.AnimalService.model.menu.DogShelterMenu;

import java.util.List;

import static sky.pro.SkyDreamTeam.AnimalService.model.menu.CatShelterMenu.NONE_C;
import static sky.pro.SkyDreamTeam.AnimalService.model.menu.CatShelterMenu.START_C;
import static sky.pro.SkyDreamTeam.AnimalService.model.menu.DogShelterMenu.START;


@Service
public class CatShelterMenuService {

    @Autowired
    private TelegramBot telegramBot;
    private  final CatShelterPersonService catShelterPersonService;

    public CatShelterMenuService(CatShelterPersonService catShelterPersonService) {
        this.catShelterPersonService = catShelterPersonService;
    }

    private Logger logger = LoggerFactory.getLogger(TelegramBotService.class);
    public void selectMenu(Update update) {
    }

    public void creatNewPerson(Update update) {
        long chatId = update.message().chat().id();
        String name = update.message().chat().firstName() +
                " @" + update.message().chat().username();
        String phone = "none";
        String addres = "none";
        CatShelterMenu botMenu = START_C;
        CatShelterPerson newPerson = new CatShelterPerson(
                update.message().chat().id(),
                name,
                phone,
                addres,
                botMenu);
        catShelterPersonService.creatPerson(newPerson);
        String message = "Добро пожаловать в Приют для Кошек!";
        telegramBot.execute(new SendMessage(chatId, message));
        sendStartMassage(chatId);
        logger.info("Processing add new CatShelterPerson: {}", newPerson);
    }

    private void sendStartMassage(long chatId) {
        String message = "Меню в процессе разработки!";
        creatKeybordFromList(chatId, message, NONE_C);
        catShelterPersonService.deleteByChatId(chatId);
    }
    private void creatKeybordFromList(long chatId, String message, CatShelterMenu botMenu) {
        List<String> keybordList = botMenu.getMenuLink();
        int keybordArraySize = keybordList.size();
        String[][] keybord = new String[keybordArraySize][1];
        for (int i = 0; i < keybordArraySize; i++) {
            CatShelterMenu button = CatShelterMenu.valueOf(keybordList.get(i));
            keybord[i][0] = button.getMenuDescription();
        }
        telegramBot.execute(new SendMessage(chatId, message)
                .replyMarkup(new ReplyKeyboardMarkup(keybord)));
    }


}
