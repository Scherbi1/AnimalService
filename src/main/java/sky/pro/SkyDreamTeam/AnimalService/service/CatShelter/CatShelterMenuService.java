package sky.pro.SkyDreamTeam.AnimalService.service.CatShelter;

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
import sky.pro.SkyDreamTeam.AnimalService.model.CatShelter.CatShelterPerson;
import sky.pro.SkyDreamTeam.AnimalService.model.CatShelter.CatShelterMenu;

import sky.pro.SkyDreamTeam.AnimalService.model.CatShelter.CatShelterPet;
import sky.pro.SkyDreamTeam.AnimalService.model.CatShelter.CatShelterReport;
import sky.pro.SkyDreamTeam.AnimalService.repository.InformationRepository;
import sky.pro.SkyDreamTeam.AnimalService.service.*;

import java.time.LocalDateTime;
import java.util.List;


import static sky.pro.SkyDreamTeam.AnimalService.model.CatShelter.CatShelterMenu.*;
import static sky.pro.SkyDreamTeam.AnimalService.utils.FileUtil.getExtension;
import static sky.pro.SkyDreamTeam.AnimalService.utils.FileUtil.saveImageToDisk;
import static sky.pro.SkyDreamTeam.AnimalService.utils.StringUtils.isPhoneNumber;


@Service
public class CatShelterMenuService {

    @Autowired
    private TelegramBot telegramBot;
    private final CatShelterPersonService catShelterPersonService;
    private final CatShelterReportService catShelterReportService;
    private final InformationRepository informationRepository;
    private final InformationService informationService;
    private final ImageService imageService;


    private CatShelterMenu catShelterMenu = START_C;

    public CatShelterMenuService(CatShelterPersonService catShelterPersonService, CatShelterReportService catShelterReportService, InformationRepository informationRepository, InformationService informationService, ImageService imageService) {
        this.catShelterPersonService = catShelterPersonService;
        this.catShelterReportService = catShelterReportService;
        this.informationRepository = informationRepository;
        this.informationService = informationService;
        this.imageService = imageService;
    }

    private Logger logger = LoggerFactory.getLogger(TelegramBotService.class);

    public void selectMenu(Update update) {
        long chatId = (update.message().chat().id());
        catShelterMenu = catShelterPersonService.getBotMenuByChatId(chatId);
        String updateMessage = update.message().text();

        if (updateMessage != null) {
            if (presentInMenuLink(catShelterMenu, START_C) &&
                    updateMessage.equals(START_C.getMenuDescription()) ||
                    updateMessage.equals("/start")) {
                catShelterPersonService.setBotMenuByChatId(chatId, START_C);
                sendStartMassage(chatId);
                return;
            }
            if (presentInMenuLink(catShelterMenu, INFO_C) &&
                    updateMessage.equals(INFO_C.getMenuDescription())) {
                catShelterPersonService.setBotMenuByChatId(chatId, INFO_C);
                sendInfoMassage(chatId);
                return;
            }

            if (presentInMenuLink(catShelterMenu, SHELTERINFO_C) &&
                    updateMessage.equals(SHELTERINFO_C.getMenuDescription())) {
                catShelterPersonService.setBotMenuByChatId(chatId, INFO_C);
                sendShelterInfoMessage(chatId);
                return;
            }

            if (presentInMenuLink(catShelterMenu, CONTACT_C) &&
                    updateMessage.equals(CONTACT_C.getMenuDescription())) {
                catShelterPersonService.setBotMenuByChatId(chatId, INFO_C);
                sendContactMassage(chatId);
                return;
            }

            if (presentInMenuLink(catShelterMenu, SECURITY_C) &&
                    updateMessage.equals(SECURITY_C.getMenuDescription())) {
                catShelterPersonService.setBotMenuByChatId(chatId, INFO_C);
                sendSecurityMassage(chatId);
                return;
            }


            if (presentInMenuLink(catShelterMenu, GET_PET_C) &&
                    updateMessage.equals(GET_PET_C.getMenuDescription())) {
                catShelterPersonService.setBotMenuByChatId(chatId, GET_PET_C);
                sendGetPetMassage(chatId);
                return;
            }

            if (presentInMenuLink(catShelterMenu, GET_PET_RULES_C) &&
                    updateMessage.equals(GET_PET_RULES_C.getMenuDescription())) {
                catShelterPersonService.setBotMenuByChatId(chatId, GET_PET_C);
                sendGetPetRulesMassage(chatId);
                return;
            }

            if (presentInMenuLink(catShelterMenu, GET_PET_DOCS_C) &&
                    updateMessage.equals(GET_PET_DOCS_C.getMenuDescription())) {
                catShelterPersonService.setBotMenuByChatId(chatId, GET_PET_C);
                sendGetPetDocsMassage(chatId);
                return;
            }

            if (presentInMenuLink(catShelterMenu, GET_PET_REJECT_C) &&
                    updateMessage.equals(GET_PET_REJECT_C.getMenuDescription())) {
                catShelterPersonService.setBotMenuByChatId(chatId, GET_PET_C);
                sendGetPetRejectMassage(chatId);
                return;
            }

            if (presentInMenuLink(catShelterMenu, GET_PET_RECOM_C) &&
                    updateMessage.equals(GET_PET_RECOM_C.getMenuDescription())) {
                catShelterPersonService.setBotMenuByChatId(chatId, GET_PET_RECOM_C);
                sendGetPetRecomMassage(chatId);
                return;
            }

            if (presentInMenuLink(catShelterMenu, GET_PET_RECOM_TRANSPORT_C) &&
                    updateMessage.equals(GET_PET_RECOM_TRANSPORT_C.getMenuDescription())) {
                catShelterPersonService.setBotMenuByChatId(chatId, GET_PET_RECOM_C);
                sendGetPetRecomTransportMassage(chatId);
                return;
            }

            if (presentInMenuLink(catShelterMenu, GET_PET_RECOM_HOUSE_C) &&
                    updateMessage.equals(GET_PET_RECOM_HOUSE_C.getMenuDescription())) {
                catShelterPersonService.setBotMenuByChatId(chatId, GET_PET_RECOM_C);
                sendGetPetRecomHouseMassage(chatId);
                return;
            }

            if (presentInMenuLink(catShelterMenu, GET_PET_RECOM_HOUSE_INVALIDCAT_C) &&
                    updateMessage.equals(GET_PET_RECOM_HOUSE_INVALIDCAT_C.getMenuDescription())) {
                catShelterPersonService.setBotMenuByChatId(chatId, GET_PET_RECOM_C);
                sendGetPetRecomHouseInvalidCatMassage(chatId);
                return;
            }

            if (presentInMenuLink(catShelterMenu, REPORT_C) &&
                    updateMessage.equals(REPORT_C.getMenuDescription())) {
                catShelterPersonService.setBotMenuByChatId(chatId, REPORT_C);
                sendReportMassage(chatId);
                return;
            }
            if (presentInMenuLink(catShelterMenu, GET_CLIENTCONTACT_C) &&
                    updateMessage.equals(GET_CLIENTCONTACT_C.getMenuDescription())) {
                catShelterPersonService.setBotMenuByChatId(chatId, GET_CLIENTCONTACT_C);
                sendGetClientContactMassage(chatId);
                return;
            }
            if (presentInMenuLink(catShelterMenu, CONNECTTOADMIN_C) &&
                    updateMessage.equals(CONNECTTOADMIN_C.getMenuDescription())) {
                catShelterPersonService.setBotMenuByChatId(chatId, INFO_C);
                sendConnectToAdminMassage(chatId);
                return;
            }
            if (catShelterMenu.equals(GET_CLIENTCONTACT_C)) {
                sendGetClientContactProcess(chatId, updateMessage);
                return;
            }
            if (catShelterMenu.equals(REPORT_C)) {
                reportProcess(chatId, updateMessage);
                return;
            }

        }

        if (catShelterMenu.equals(SENDFOTO_C)) {
            sendFotoProcess(chatId, update);
            return;
        }
    }

    public void createNewPerson(Update update) {
        long chatId = update.message().chat().id();
        String name = update.message().chat().firstName() +
                " @" + update.message().chat().username();
        String phone = "none";
        String addres = "none";
        CatShelterMenu botMenu = START_C;
        CatShelterPet catShelterPet=null;
        CatShelterPerson newPerson = new CatShelterPerson(
                update.message().chat().id(),
                name,
                phone,
                addres,
                botMenu,
                catShelterPet);
        catShelterPersonService.createPerson(newPerson);
        String message = "Добро пожаловать в Приют для Кошек!";
        telegramBot.execute(new SendMessage(chatId, message));
        sendStartMassage(chatId);
        logger.info("Processing add new CatShelterPerson: {}", newPerson);
    }

    private void sendStartMassage(long chatId) {
        String message = "Главное меню";
        creatKeybordFromList(chatId, message, START_C);
        logger.info("Cat shelter sendStartMassagee to chatId: {}", chatId);
    }

    private void sendInfoMassage(long chatId) {
        String message = "Информационное Меню";
        creatKeybordFromList(chatId, message, INFO_C);
        logger.info("Cat shelter sendInfoMassage to chatId: {}", chatId);
    }

    private void sendContactMassage(long chatId) {
        String message = "None";
        try {
            message = informationRepository.findLastByQuestion("catshelter_contact").getAnswer();
        } catch (NullPointerException e) {
            message = "Свяжитесь с администрацией что бы уточнить контактные данные";
        }
        telegramBot.execute(new SendMessage(chatId, message));

        try {
            byte[] photoData = imageService.findImageByDiscription("catshelter_location").getData();
            SendPhoto sendPhoto = new SendPhoto(chatId, photoData);
            telegramBot.execute(sendPhoto);
        } catch (NullPointerException e) {
            telegramBot.execute(new SendMessage(chatId, "Свяжитесь с администрацией что бы уточнить схему проезда"));
        }

        logger.info("Cat shelter sendContactMassage to chatId: {}", chatId);
    }

    private void sendShelterInfoMessage(long chatId) {
        String message = "None";
        try {
            message = informationRepository.findLastByQuestion("catshelter_info").getAnswer();
        } catch (NullPointerException e) {
            message = "Свяжитесь с администрацией что бы узнать информацию о приюте";
        }

        telegramBot.execute(new SendMessage(chatId, message));
        logger.info("Cat shelter sendShelterInfoMassage to chatId: {}", chatId);
    }

    private void sendSecurityMassage(long chatId) {
        String message = "None";
        try {
            message = informationRepository.findLastByQuestion("catshelter_security").getAnswer();
        } catch (NullPointerException e) {
            message = "Свяжитесь с администрацией что бы уточнить правила безопастности";
        }

        telegramBot.execute(new SendMessage(chatId, message));
        logger.info("Cat shelter  sendShelterInfoMassage to chatId: {}", chatId);
    }

    private void sendGetPetMassage(long chatId) {
        String message = "Взять Кошку.";
        creatKeybordFromList(chatId, message, GET_PET_C);
        logger.info("Cat shelter sendGetPetMassage to chatId: {}", chatId);
    }


    private void sendGetPetRulesMassage(long chatId) {
        String message = "None";
        try {
            message = informationRepository.findLastByQuestion("catshelter_rules").getAnswer();
        } catch (NullPointerException e) {
            message = "Свяжитесь с администрацией что бы узнать правила знакомства с кошкой";
        }

        telegramBot.execute(new SendMessage(chatId, message));
        logger.info("Cat shelter sendGetPetRulesMassage to chatId: {}", chatId);
    }

    private void sendGetPetDocsMassage(long chatId) {
        String message = "None";
        try {
            message = informationRepository.findLastByQuestion("catshelter_docs").getAnswer();
        } catch (NullPointerException e) {
            message = "Свяжитесь с администрацией что бы узнать про документы";
        }

        telegramBot.execute(new SendMessage(chatId, message));
        logger.info("Cat shelter sendGetPetDocsMassage to chatId: {}", chatId);
    }

    private void sendGetPetRejectMassage(long chatId) {
        String message = "None";
        try {
            message = informationRepository.findLastByQuestion("catshelter_reject").getAnswer();
        } catch (NullPointerException e) {
            message = "Свяжитесь с администрацией что бы узнать причины отказа";
        }

        telegramBot.execute(new SendMessage(chatId, message));
        logger.info("Cat shelter sendGetPetRejectMassage to chatId: {}", chatId);
    }

    private void sendGetPetRecomMassage(long chatId) {
        String message = "Меню рекомендаций";
        creatKeybordFromList(chatId, message, GET_PET_RECOM_C);
        logger.info("Cat shelter sendGetPetRecomMassage to chatId: {}", chatId);
    }

    private void sendGetPetRecomTransportMassage(long chatId) {
        String message = "None";
        try {
            message = informationRepository.findLastByQuestion("catshelter_transport").getAnswer();
        } catch (NullPointerException e) {
            message = "Свяжитесь с администрацией что бы узнать про транспортировку кошки";
        }

        telegramBot.execute(new SendMessage(chatId, message));
        logger.info("Cat shelter sendGetPetRecomTransportMassage to chatId: {}", chatId);
    }

    private void sendGetPetRecomHouseMassage(long chatId) {
        String message = "None";
        try {
            message = informationRepository.findLastByQuestion("catshelter_house").getAnswer();
        } catch (NullPointerException e) {
            message = "Свяжитесь с администрацией что бы узнать про дом для кошки";
        }

        telegramBot.execute(new SendMessage(chatId, message));
        logger.info("Cat shelter sendGetPetRecomHouseMassage to chatId: {}", chatId);
    }

    private void sendGetPetRecomHouseInvalidCatMassage(long chatId) {
        String message = "None";
        try {
            message = informationRepository.findLastByQuestion("catshelter_house_invalidcat").getAnswer();
        } catch (NullPointerException e) {
            message = "Свяжитесь с администрацией что бы узнать про дом для кошки с инвлидностью";
        }

        telegramBot.execute(new SendMessage(chatId, message));
        logger.info("Cat shelter sendGetPetRecomHouseInvalidCatMassage to chatId: {}", chatId);
    }

    private void sendReportMassage(long chatId) {
        if (catShelterPersonService.getPetIdByChatId(chatId) == null) {
            telegramBot.execute(new SendMessage(chatId, "У вас пока нет собаки."));
            return;
        }
        String message = "Меню отчета. Напишите подробный отчет про питомца.";
        creatKeybordFromList(chatId, message, REPORT_C);
        logger.info("Cat shelter sendReportMassage to chatId: {}", chatId);
    }

    private void sendGetClientContactMassage(long chatId) {
        String message = "Напишите свой номер телефона в формате +79998887766.";
        creatKeybordFromList(chatId, message, GET_CLIENTCONTACT_C);
        logger.info("Cat shelter sendReportMassage to chatId: {}", chatId);
    }

    private void sendGetClientContactProcess(long chatId, String updateMessage) {
        if (isPhoneNumber(updateMessage)) {
            catShelterPersonService.setPhoneByChatId(chatId, updateMessage);
            catShelterPersonService.setBotMenuByChatId(chatId, START_C);
            creatKeybordFromList(chatId, "Телефон записан.", START_C);
        } else {
            telegramBot.execute(new SendMessage(chatId, "Это не похоже на телефон! Напишите в формате +79998887766"));
        }

        logger.info("Cat shelter sendGetClientContactProcess to chatId: {}", chatId);
    }

    private void sendConnectToAdminMassage(long chatId) {
        String message = "Волонтер с вами свяжется в ближайшее времяю";
        telegramBot.execute(new SendMessage(chatId, message));
        logger.info("Cat shelter sendConnectToAdminMassage to chatId: {}", chatId);
    }

    private void reportProcess(long chatId, String updateMessage) {
        informationService.creatInformation(chatId + "", updateMessage);
        catShelterPersonService.setBotMenuByChatId(chatId, SENDFOTO_C);
        telegramBot.execute(new SendMessage(chatId, "Прикрепите фотографию кошки."));
        logger.info("Cat shelter reportProcess to chatId: {}", chatId);
    }

    private void sendFotoProcess(long chatId, Update update) {
        if (update.message().photo() != null) {
            String discripton = chatId + "_" + System.currentTimeMillis();
            saveImageToBDAndDisc(update, discripton);
            CatShelterReport report = new CatShelterReport();
            report.setId(0L);
            report.setChatId(chatId);
            LocalDateTime localDateTime = LocalDateTime.now();
            report.setDate(localDateTime);
            report.setMessage(informationService.getMessageByKey(chatId + ""));
            report.setImage(imageService.findImageByDiscription(discripton));
            catShelterReportService.creatReport(report);
            telegramBot.execute(new SendMessage(chatId, "Отчет успешно отправлен!"));
            catShelterPersonService.setBotMenuByChatId(chatId, START_C);
            sendStartMassage(chatId);
        } else {
            telegramBot.execute(new SendMessage(chatId, "Это не фотография. Прикрепите фоторафию."));
            catShelterPersonService.setBotMenuByChatId(chatId, SENDFOTO_C);
        }
        logger.info("Cat shelter sendFotoProcess to chatId: {}", chatId);
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

    private boolean presentInMenuLink(CatShelterMenu botMenu, CatShelterMenu findMenu) {
        List<String> menuLink = botMenu.getMenuLink();
        return menuLink.contains(findMenu.toString());
    }

    private void saveImageToBDAndDisc(Update update, String discripton) {
        logger.info("Cat shelter Processing photo load from telegram");
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
