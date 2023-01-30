package sky.pro.SkyDreamTeam.AnimalService.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.File;
import com.pengrad.telegrambot.model.PhotoSize;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.GetFile;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SendPhoto;
import com.pengrad.telegrambot.response.GetFileResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sky.pro.SkyDreamTeam.AnimalService.initialization.InformationLoader;
import sky.pro.SkyDreamTeam.AnimalService.initialization.ImageLoader;
import sky.pro.SkyDreamTeam.AnimalService.model.BotMenu;
import sky.pro.SkyDreamTeam.AnimalService.model.Person;
import sky.pro.SkyDreamTeam.AnimalService.model.Report;
import sky.pro.SkyDreamTeam.AnimalService.repository.InformationRepository;

import javax.annotation.PostConstruct;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


import static sky.pro.SkyDreamTeam.AnimalService.model.BotMenu.*;
import static sky.pro.SkyDreamTeam.AnimalService.utils.FileUtil.getExtension;
import static sky.pro.SkyDreamTeam.AnimalService.utils.FileUtil.saveImageToDisk;


@Service
public class TelegramBotService implements UpdatesListener {

    private Logger logger = LoggerFactory.getLogger(TelegramBotService.class);


    private InformationRepository informationRepository;
    private InformationLoader informationLoader;
    private final ImageLoader photoLoader;

    private final InformationService informationService;
    private final PersonService personService;
    private final ImageService imageService;
    private  final ReportService reportService;

    public TelegramBotService(InformationRepository informationRepository,
                              InformationLoader informationLoader,
                              ImageLoader photoLoader, InformationService informationService, PersonService personService,
                              ImageService imageService, ReportService reportService) {
        this.informationRepository = informationRepository;
        this.informationLoader = informationLoader;
        this.photoLoader = photoLoader;
        this.informationService = informationService;
        this.personService = personService;
        this.imageService = imageService;
        this.reportService = reportService;
    }

    private BotMenu botMenu = START;
    @Autowired
    private TelegramBot telegramBot;

    private List<File> fileArchive = new ArrayList<>();


    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @PostConstruct
    public void loadInfo() throws IOException {
        informationLoader.loadInfo();
    }

    @PostConstruct
    public void loadPhoto() throws IOException {
        photoLoader.loadPhoto();
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            long chatId = update.message().chat().id();

            if (personService.findPersonByChatId(chatId) == null) {
                String name = update.message().chat().firstName() + " @"
                        + update.message().chat().username();
                String phone = "none";
                String addres = "none";
                BotMenu botMenu = START;
                boolean isAdmin = true;
                Person newPerson = new Person(
                        chatId
                        , name
                        , phone
                        , addres
                        , botMenu
                        , isAdmin);
                personService.creatPerson(newPerson);
                String message = "Этот телеграм бот обслуживает сервис Приюта для Животных";
                telegramBot.execute(new SendMessage(chatId, message));
                logger.info("Processing add new Person: {}", newPerson);
            }

            botMenu = personService.getBotMenuByChatId(chatId);
            switch (botMenu) {
                case START:
                    startMenu(update);
                    break;
                case INFO:
                    infoMenu(update);
                    break;
                case REPORT:
                    reportMenu(update);
                    break;
                case SENDFOTO:
                    sendFotoMenu(update);
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
        String updateMessage = update.message().text();
        if (updateMessage != null) {
            if (updateMessage.equals(START.getMenuDescription())||updateMessage.equals("/start")) {
                sendStartMassage(chatId);
            }

            if (updateMessage.equals(INFO.getMenuDescription())) {
                sendInfoMassage(chatId);
            }
            if (updateMessage.equals(REPORT.getMenuDescription())) {
                sendReportMassage(chatId);
            }

        }

        logger.info("Processing startMenu: {}", updateMessage);
    }

    private void sendStartMassage(long chatId) {
        String message = "Главное меню.";
        String[] button1 = {START.getMenuDescription()};
        String[] button2 = {INFO.getMenuDescription()};
        String[] button3 = {REPORT.getMenuDescription()};

        telegramBot.execute(new SendMessage(chatId, message)
                .replyMarkup(new ReplyKeyboardMarkup(button1, button2, button3)));

        personService.setBotMenuByChatId(chatId, START);
        logger.info("sendStartMassagee to chatId: {}", chatId);
    }

    private void infoMenu(Update update) {
        long chatId = (update.message().chat().id());
        String updateMessage = update.message().text();
        if (updateMessage != null) {
            if (updateMessage.equals(START.getMenuDescription())||updateMessage.equals("/start")) {
                sendStartMassage(chatId);
            }
            if (updateMessage.equals(CONTACT.getMenuDescription())) {
                sendContactMassage(chatId);
            }
            if (updateMessage.equals(SHELTERINFO.getMenuDescription())) {
                sendShelterInfoMassage(chatId);
            }
            if (updateMessage.equals(CONNECTTOADMIN.getMenuDescription())) {
                sendConnectToAdminMassage(chatId);
            }

        }

        logger.info("Processing infoMenu: {}", updateMessage);
    }


    private void sendInfoMassage(long chatId) {
        String message = "Информационное Меню";

        String[] button1 = {START.getMenuDescription()};
        String[] button2 = {CONTACT.getMenuDescription()};
        String[] button3 = {SHELTERINFO.getMenuDescription()};
        String[] button4 = {CONNECTTOADMIN.getMenuDescription()};

        telegramBot.execute(new SendMessage(chatId, message)
                .replyMarkup(new ReplyKeyboardMarkup(button1, button2, button3, button4)));

        personService.setBotMenuByChatId(chatId, INFO);
        logger.info("sendInfoMassage to chatId: {}", chatId);
    }

    private void sendContactMassage(long chatId) {
        String massage = informationRepository.findLastByQuestion("contact").getAnswer();
        telegramBot.execute(new SendMessage(chatId, massage));

        try {
            byte[] photoData = imageService.findImageByDiscription("location").getData();
            SendPhoto sendPhoto = new SendPhoto(chatId, photoData);
            telegramBot.execute(sendPhoto);
        } catch (NullPointerException e) {
            telegramBot.execute(new SendMessage(chatId, "Схему проезда уточните по телефону."));
        }

        personService.setBotMenuByChatId(chatId, INFO);
        logger.info("sendContactMassage to chatId: {}", chatId);
    }

    private void sendShelterInfoMassage(long chatId) {
        String massage = informationRepository.findLastByQuestion("shelterinfo").getAnswer();
        SendMessage message = new SendMessage(chatId, massage);
        telegramBot.execute(message);
        personService.setBotMenuByChatId(chatId, INFO);
        logger.info("sendShelterInfoMassage to chatId: {}", chatId);
    }

    private void sendConnectToAdminMassage(long chatId) {
        String message = "Волонтер с вами свяжется в ближайшее времяю";
        telegramBot.execute(new SendMessage(chatId, message));
        personService.setBotMenuByChatId(chatId, INFO);
        logger.info("sendConnectToAdminMassage to chatId: {}", chatId);
    }

    private void reportMenu(Update update) {
        long chatId = (update.message().chat().id());
        String updateMessage = update.message().text();
        if (updateMessage != null) {
            if (updateMessage.equals(START.getMenuDescription())||updateMessage.equals("/start")) {
                sendStartMassage(chatId);
            }else {
                informationService.creatInformation(chatId + "", updateMessage);
                personService.setBotMenuByChatId(chatId, SENDFOTO);
                telegramBot.execute(new SendMessage(chatId, "Прикрепите фотографию питомца."));
            }

        }
        logger.info("Processing reportMenu: {}", updateMessage);

    }


    private void sendReportMassage(long chatId) {
        String message = "Меню отчета. Напишите подробный отчет про питомца.";

        String[] button1 = {START.getMenuDescription()};


        telegramBot.execute(new SendMessage(chatId, message)
                .replyMarkup(new ReplyKeyboardMarkup(button1)));

        personService.setBotMenuByChatId(chatId, REPORT);
        logger.info("sendReportMassage to chatId: {}", chatId);
    }



    private void sendFotoMenu(Update update) {
        long chatId = (update.message().chat().id());
        String updateMessage = update.message().text();
        if (updateMessage != null) {
            if (updateMessage.equals(START.getMenuDescription())||updateMessage.equals("/start")) {
                sendStartMassage(chatId);
                return;
            }
        }
        if (update.message().photo() != null) {
            String discripton = chatId + "_" + System.currentTimeMillis();
            saveImageToBDAndDisc(update, discripton);
            Report report = new Report();
            report.setId(0L);
            report.setChatId(chatId);
            LocalDateTime localDateTime = LocalDateTime.now();
            report.setDate(localDateTime);
            report.setMessage(informationService.getMessageByKey(chatId + ""));
            report.setImage(imageService.findImageByDiscription(discripton));
            reportService.creatReport(report);
            telegramBot.execute(new SendMessage(chatId, "Отчет успешно отправлен!"));
            sendStartMassage(chatId);
        }
        else {
            telegramBot.execute(new SendMessage(chatId, "Это не фотография. Прикрепите фоторафию."));
        }

    }



    private void saveImageToBDAndDisc(Update update,String discripton){
        logger.info("Processing photo load from telegram");
        PhotoSize[] photo = update.message().photo();
        String fileId = photo[photo.length - 1].fileId();
        GetFile request = new GetFile(fileId);
        GetFileResponse getFileResponse = telegramBot.execute(request);
        File file = getFileResponse.file();
        file.fileId();
        file.filePath();
        file.fileSize();
        String telegramFullPath = telegramBot.getFullFilePath(file);
        boolean saveImageToDisk = saveImageToDisk(discripton,telegramFullPath);
        logger.info("saveImageToDisk={}", saveImageToDisk);
        String filePathName = "src/main/photos/" + discripton+"."+getExtension(telegramFullPath);
        boolean uploadImageFromTelegram=imageService.uploadImageFromDisc(discripton, filePathName);
        logger.info("uploadImageFromDisc={}", uploadImageFromTelegram);

    }


}
