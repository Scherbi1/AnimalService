package sky.pro.SkyDreamTeam.AnimalService.service;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
;

import javax.annotation.PostConstruct;
import java.util.List;



@Service
public class BotUpdatesListener implements UpdatesListener {

    private Logger logger = LoggerFactory.getLogger(BotUpdatesListener.class);

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
            String updateMassage = update.message().text();
            if (updateMassage != null) {
                processingRequest(chatId, updateMassage);

            }
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    private void processingRequest(long chatId, String updateMassage) {
        if (updateMassage.equals("/start")) {
            sendStartMasage(chatId);
        }
        logger.info("Processing request: {}", updateMassage);
    }



    private void sendStartMasage(long chatId) {
        String massage = "Этот телеграм бот обслуживает сервис Приюта для Животных";
        SendMessage message = new SendMessage(chatId, massage);
        telegramBot.execute(message);
        logger.info("Send Start massage to chatId: {}", chatId);
    }


}
