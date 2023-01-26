package sky.pro.SkyDreamTeam.AnimalService.service;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.File;
import com.pengrad.telegrambot.model.PhotoSize;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.GetFile;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SendPhoto;
import com.pengrad.telegrambot.response.GetFileResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import sky.pro.SkyDreamTeam.AnimalService.initialization.InitData;
import sky.pro.SkyDreamTeam.AnimalService.model.BotMenu;

import javax.annotation.PostConstruct;

import java.io.BufferedInputStream;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


import static sky.pro.SkyDreamTeam.AnimalService.model.BotMenu.*;


@Service
public class BotUpdatesListener implements UpdatesListener {

    private Logger logger = LoggerFactory.getLogger(BotUpdatesListener.class);

    private BotMenu botMenu = REPORT;
    @Autowired
    private TelegramBot telegramBot;

    private List<File> fileArchive=new ArrayList<>();

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
                case REPORT:
                    try {
                        reportMenu(update);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                default:
                    sendReportMassage(chatId);
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
            if (updateMassage.equals("/report")) {
                sendReportMassage(chatId);
            }

        }

        logger.info("Processing startMenu: {}", updateMassage);
    }

    private void sendStartMassage(long chatId) {
        String massage = "Этот телеграм бот обслуживает сервис Приюта для Животных" + "\n" +
                "Команды:" + "\n" +
                "Главное меню /start" + "\n" +
                "Информационное меню /info"+ "\n" +
                "Отчет /report";
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

    private void reportMenu(Update update) throws IOException {
        long chatId = (update.message().chat().id());
        String updateMassage = update.message().text();
        if (updateMassage != null) {
            if (updateMassage.equals("/start")) {
                sendStartMassage(chatId);
            }
        }
        if (update.message().photo() != null) {
            System.out.println("Foto load");

            PhotoSize[] photo = update.message().photo();

            String fileId =photo[photo.length-1].fileId();
            GetFile request = new GetFile(fileId);
            GetFileResponse getFileResponse = telegramBot.execute(request);

            File file = getFileResponse.file();
            file.fileId();
            file.filePath();
            file.fileSize();

            fileArchive.add(file);
            System.out.println(file.filePath());
            fileArchive.stream().forEach(f->{
                SendPhoto sendPhoto = new SendPhoto(chatId,f.fileId());
                telegramBot.execute(sendPhoto);
            });

            String fullPath = telegramBot.getFullFilePath(file);

            String filePathName="src/main/"+file.filePath();

            try (BufferedInputStream in = new BufferedInputStream(new URL(fullPath).openStream());
                 FileOutputStream fileOutputStream = new FileOutputStream(filePathName)) {
                byte dataBuffer[] = new byte[1024];
                int bytesRead;
                while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                    fileOutputStream.write(dataBuffer, 0, bytesRead);
                }
            } catch (IOException e) {
                System.out.println("Не получилось");
            }
        }

        logger.info("Processing reportMenu: {}", fileArchive);
    }

    private void sendReportMassage(long chatId) {
        String massage = "Отчет" + "\n" +
                "Команды:" + "\n" +
                "Главное меню /start" + "\n"+
                "Прикрепите Фотографию.";
        SendMessage message = new SendMessage(chatId, massage);
        telegramBot.execute(message);
        botMenu = REPORT;
        logger.info("Send Start massage to chatId: {}", chatId);
    }

    @Bean(initMethod = "runAfterObjectCreated")
    public InitData runAfterObjectCreated() {
        return new InitData();
    }


}
