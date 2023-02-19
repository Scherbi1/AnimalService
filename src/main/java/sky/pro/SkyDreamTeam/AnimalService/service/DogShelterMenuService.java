package sky.pro.SkyDreamTeam.AnimalService.service;

import com.pengrad.telegrambot.TelegramBot;
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
import sky.pro.SkyDreamTeam.AnimalService.model.DogShelter.DogShelterMenu;
import sky.pro.SkyDreamTeam.AnimalService.model.Person;

import sky.pro.SkyDreamTeam.AnimalService.model.Report;
import sky.pro.SkyDreamTeam.AnimalService.repository.InformationRepository;


import java.time.LocalDateTime;
import java.util.List;

import static sky.pro.SkyDreamTeam.AnimalService.model.DogShelter.DogShelterMenu.*;
import static sky.pro.SkyDreamTeam.AnimalService.utils.FileUtil.getExtension;
import static sky.pro.SkyDreamTeam.AnimalService.utils.FileUtil.saveImageToDisk;

@Service
public class DogShelterMenuService {

    @Autowired
    private TelegramBot telegramBot;

    private final InformationRepository informationRepository;
    private final InformationService informationService;
    private final PersonService personService;
    private final ImageService imageService;
    private final ReportService reportService;
    private DogShelterMenu dogShelterMenu = START;

    private Logger logger = LoggerFactory.getLogger(TelegramBotService.class);

    public DogShelterMenuService(InformationRepository informationRepository,
                                 InformationService informationService,
                                 PersonService personService,
                                 ImageService imageService,
                                 ReportService reportService)  {
        this.informationRepository = informationRepository;
        this.informationService = informationService;
        this.personService = personService;
        this.imageService = imageService;
        this.reportService = reportService;

    }

    void selectMenu(Update update) {
        long chatId = (update.message().chat().id());
        dogShelterMenu = personService.getBotMenuByChatId(chatId);
        String updateMessage = update.message().text();

        if (updateMessage != null) {
            if (presentInMenuLink(dogShelterMenu, START) &&
                    updateMessage.equals(START.getMenuDescription()) ||
                    updateMessage.equals("/start")) {
                personService.setBotMenuByChatId(chatId, START);
                sendStartMassage(chatId);
                return;
            }

            if (presentInMenuLink(dogShelterMenu, INFO) &&
                    updateMessage.equals(INFO.getMenuDescription())) {
                personService.setBotMenuByChatId(chatId, INFO);
                sendInfoMassage(chatId);
                return;
            }

            if (presentInMenuLink(dogShelterMenu, REPORT) &&
                    updateMessage.equals(REPORT.getMenuDescription())) {
                personService.setBotMenuByChatId(chatId, REPORT);
                sendReportMassage(chatId);
                return;
            }

            if (presentInMenuLink(dogShelterMenu, CONTACT) &&
                    updateMessage.equals(CONTACT.getMenuDescription())) {
                personService.setBotMenuByChatId(chatId, INFO);
                sendContactMassage(chatId);
                return;
            }

            if (presentInMenuLink(dogShelterMenu, SHELTERINFO) &&
                    updateMessage.equals(SHELTERINFO.getMenuDescription())) {
                personService.setBotMenuByChatId(chatId, INFO);
                sendShelterInfoMassage(chatId);
                return;
            }

            if (presentInMenuLink(dogShelterMenu, CONNECTTOADMIN) &&
                    updateMessage.equals(CONNECTTOADMIN.getMenuDescription())) {
                personService.setBotMenuByChatId(chatId, INFO);
                sendConnectToAdminMassage(chatId);
                return;
            }
            if (dogShelterMenu.equals(REPORT)) {
                reportProcess(chatId, updateMessage);
                return;
            }

        }

        if (dogShelterMenu.equals(SENDFOTO)) {
            sendFotoProcess(chatId, update);
            return;
        }


    }

    public void creatNewPerson(Update update) {
        long chatId = update.message().chat().id();
        String name = update.message().chat().firstName() +
                " @" + update.message().chat().username();
        String phone = "none";
        String addres = "none";
        DogShelterMenu botMenu = START;
        boolean isAdmin = true;
        Person newPerson = new Person(
                update.message().chat().id(),
                name,
                phone,
                addres,
                botMenu,
                isAdmin);
        personService.creatPerson(newPerson);
        String message = "Добро пожаловать в Приют для Собак!";
        telegramBot.execute(new SendMessage(chatId, message));
        sendStartMassage(chatId);
        logger.info("Processing add new Person: {}", newPerson);
    }



    private void sendStartMassage(long chatId) {
        String message = "Главное меню";
        creatKeybordFromList(chatId, message, START);
        logger.info("sendStartMassagee to chatId: {}", chatId);
    }

    private void sendInfoMassage(long chatId) {
        String message = "Информационное Меню";
        creatKeybordFromList(chatId, message, INFO);
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

        logger.info("sendContactMassage to chatId: {}", chatId);
    }

    private void sendShelterInfoMassage(long chatId) {
        String massage = informationRepository.findLastByQuestion("shelterinfo").getAnswer();
        SendMessage message = new SendMessage(chatId, massage);
        telegramBot.execute(message);
        logger.info("sendShelterInfoMassage to chatId: {}", chatId);
    }

    private void sendConnectToAdminMassage(long chatId) {
        String message = "Волонтер с вами свяжется в ближайшее времяю";
        telegramBot.execute(new SendMessage(chatId, message));
        logger.info("sendConnectToAdminMassage to chatId: {}", chatId);
    }

    private void sendReportMassage(long chatId) {
        String message = "Меню отчета. Напишите подробный отчет про питомца.";
        creatKeybordFromList(chatId, message, REPORT);
        logger.info("sendReportMassage to chatId: {}", chatId);
    }

    private void reportProcess(long chatId, String updateMessage) {
        informationService.creatInformation(chatId + "", updateMessage);
        personService.setBotMenuByChatId(chatId, SENDFOTO);
        telegramBot.execute(new SendMessage(chatId, "Прикрепите фотографию питомца."));
        logger.info("reportProcess to chatId: {}", chatId);
    }

    private void sendFotoProcess(long chatId, Update update) {
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
            personService.setBotMenuByChatId(chatId, START);
            sendStartMassage(chatId);
        } else {
            telegramBot.execute(new SendMessage(chatId, "Это не фотография. Прикрепите фоторафию."));
            personService.setBotMenuByChatId(chatId, SENDFOTO);
        }
        logger.info("sendFotoProcess to chatId: {}", chatId);
    }


    private boolean presentInMenuLink(DogShelterMenu botMenu, DogShelterMenu findMenu) {
        List<String> menuLink = botMenu.getMenuLink();
        return menuLink.contains(findMenu.toString());
    }

    private void creatKeybordFromList(long chatId, String message, DogShelterMenu botMenu) {
        List<String> keybordList = botMenu.getMenuLink();
        int keybordArraySize = keybordList.size();
        String[][] keybord = new String[keybordArraySize][1];
        for (int i = 0; i < keybordArraySize; i++) {
            DogShelterMenu button = DogShelterMenu.valueOf(keybordList.get(i));
            keybord[i][0] = button.getMenuDescription();
        }
        telegramBot.execute(new SendMessage(chatId, message)
                .replyMarkup(new ReplyKeyboardMarkup(keybord)));
    }

    private void saveImageToBDAndDisc(Update update, String discripton) {
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
        boolean saveImageToDisk = saveImageToDisk(discripton, telegramFullPath);
        logger.info("saveImageToDisk={}", saveImageToDisk);
        String filePathName = "src/main/photos/" + discripton + "." + getExtension(telegramFullPath);
        boolean uploadImageFromTelegram = imageService.uploadImageFromDisc(discripton, filePathName);
        logger.info("uploadImageFromDisc={}", uploadImageFromTelegram);

    }
}
