package sky.pro.SkyDreamTeam.AnimalService.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import sky.pro.SkyDreamTeam.AnimalService.initialization.InitData;
import sky.pro.SkyDreamTeam.AnimalService.model.BotMenu;

import javax.annotation.PostConstruct;
import java.util.List;

import static sky.pro.SkyDreamTeam.AnimalService.model.BotMenu.*;


@Service
public class BotUpdatesListener implements UpdatesListener {

    private Logger logger = LoggerFactory.getLogger(BotUpdatesListener.class);

    private BotMenu botMenu = START;
    @Autowired
    private TelegramBot telegramBot;


    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            long chatId = (update.message().chat().id());
            logger.info("Processing update: {}", update);


            switch (botMenu) {
                case START:
                    startMenu(update);
                    break;
                case INFO:
                    infoMenu(update);
                    break;
                default:
                    sendStartMassage(chatId);
                    break;
            }

        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }


    private void startMenu(Update update) {
        long chatId = (update.message().chat().id());
        String updateMassage = update.message().text();
        if (updateMassage != null) {
            if (updateMassage.equals("/start")) {
                sendStartMassage(chatId);
            }

            if (updateMassage.equals("/info")) {
                sendInfoMassage(chatId);
            }

        }

        logger.info("Processing startMenu: {}", updateMassage);
    }

    private void sendStartMassage(long chatId) {
        String massage = "Этот телеграм бот обслуживает сервис Приюта для Животных" + "\n" +
                "Команды:" + "\n" +
                "Главное меню: /start" + "\n" +
                "Информационное меню /info";
        SendMessage message = new SendMessage(chatId, massage);
        telegramBot.execute(message);
        botMenu = START;
        logger.info("Send Start massage to chatId: {}", chatId);
    }

    private void infoMenu(Update update) {
        long chatId = (update.message().chat().id());
        String updateMassage = update.message().text();
        if (updateMassage != null) {
            if (updateMassage.equals("/start")) {
                sendStartMassage(chatId);
            }
            if (updateMassage.equals("/contact")) {
                sendContactMassage(chatId);
            }

        }

        logger.info("Processing infoMenu: {}", updateMassage);
    }

    private void sendInfoMassage(long chatId) {
        String massage = "Информационное Меню" + "\n" +
                "Команды:" + "\n" +
                "Главное меню: /start" + "\n" +
                "Контакты: /contact";
        SendMessage message = new SendMessage(chatId, massage);
        telegramBot.execute(message);
        botMenu = INFO;
        logger.info("Send Info massage to chatId: {}", chatId);
    }

    private void sendContactMassage(long chatId) {
        String massage = "Контактная информация возьмется из БД " +
                "в которую Инфа загрузиться из файла";
        SendMessage message = new SendMessage(chatId, massage);
        telegramBot.execute(message);
        botMenu = INFO;
        logger.info("Send Contact massage to chatId: {}", chatId);
    }


    @Bean(initMethod = "runAfterObjectCreated")
    public InitData runAfterObjectCreated() {
        return new InitData();
    }


}
