package sky.pro.SkyDreamTeam.AnimalService.service.DogShelter;

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
import sky.pro.SkyDreamTeam.AnimalService.model.DogShelter.DogShelterPerson;


import sky.pro.SkyDreamTeam.AnimalService.model.DogShelter.DogShelterPet;
import sky.pro.SkyDreamTeam.AnimalService.model.DogShelter.DogShelterReport;
import sky.pro.SkyDreamTeam.AnimalService.repository.InformationRepository;
import sky.pro.SkyDreamTeam.AnimalService.service.*;


import java.time.LocalDateTime;
import java.util.List;


import static sky.pro.SkyDreamTeam.AnimalService.model.DogShelter.DogShelterMenu.*;
import static sky.pro.SkyDreamTeam.AnimalService.utils.FileUtil.getExtension;
import static sky.pro.SkyDreamTeam.AnimalService.utils.FileUtil.saveImageToDisk;
import static sky.pro.SkyDreamTeam.AnimalService.utils.StringUtils.isPhoneNumber;

@Service
public class DogShelterMenuService {

    @Autowired
    private TelegramBot telegramBot;

    private final InformationRepository informationRepository;
    private final InformationService informationService;
    private final DogShelterPersonService dogShelterPersonService;
    private final ImageService imageService;
    private final DogShelterReportService dogShelterReportService;
    private DogShelterMenu dogShelterMenu = START_D;

    private Logger logger = LoggerFactory.getLogger(TelegramBotService.class);

    public DogShelterMenuService(InformationRepository informationRepository,
                                 InformationService informationService,
                                 DogShelterPersonService dogShelterPersonService,
                                 ImageService imageService,
                                 DogShelterReportService dogShelterReportService) {
        this.informationRepository = informationRepository;
        this.informationService = informationService;
        this.dogShelterPersonService = dogShelterPersonService;
        this.imageService = imageService;
        this.dogShelterReportService = dogShelterReportService;

    }

    public void selectMenu(Update update) {
        long chatId = (update.message().chat().id());
        dogShelterMenu = dogShelterPersonService.getBotMenuByChatId(chatId);
        String updateMessage = update.message().text();

        if (updateMessage != null) {
            if (presentInMenuLink(dogShelterMenu, START_D) &&
                    updateMessage.equals(START_D.getMenuDescription()) ||
                    updateMessage.equals("/start")) {
                dogShelterPersonService.setBotMenuByChatId(chatId, START_D);
                sendStartMassage(chatId);
                return;
            }

            if (presentInMenuLink(dogShelterMenu, INFO_D) &&
                    updateMessage.equals(INFO_D.getMenuDescription())) {
                dogShelterPersonService.setBotMenuByChatId(chatId, INFO_D);
                sendInfoMassage(chatId);
                return;
            }

            if (presentInMenuLink(dogShelterMenu, REPORT_D) &&
                    updateMessage.equals(REPORT_D.getMenuDescription())) {
                dogShelterPersonService.setBotMenuByChatId(chatId, REPORT_D);
                sendReportMassage(chatId);
                return;
            }

            if (presentInMenuLink(dogShelterMenu, CONTACT_D) &&
                    updateMessage.equals(CONTACT_D.getMenuDescription())) {
                dogShelterPersonService.setBotMenuByChatId(chatId, INFO_D);
                sendContactMassage(chatId);
                return;
            }

            if (presentInMenuLink(dogShelterMenu, SHELTERINFO_D) &&
                    updateMessage.equals(SHELTERINFO_D.getMenuDescription())) {
                dogShelterPersonService.setBotMenuByChatId(chatId, INFO_D);
                sendShelterInfoMassage(chatId);
                return;
            }

            if (presentInMenuLink(dogShelterMenu, SECURITY_D) &&
                    updateMessage.equals(SECURITY_D.getMenuDescription())) {
                dogShelterPersonService.setBotMenuByChatId(chatId, INFO_D);
                sendSecurityMassage(chatId);
                return;
            }
            if (presentInMenuLink(dogShelterMenu, GET_PET_D) &&
                    updateMessage.equals(GET_PET_D.getMenuDescription())) {
                dogShelterPersonService.setBotMenuByChatId(chatId, GET_PET_D);
                sendGetPetMassage(chatId);
                return;
            }

            if (presentInMenuLink(dogShelterMenu, GET_PET_RULES_D) &&
                    updateMessage.equals(GET_PET_RULES_D.getMenuDescription())) {
                dogShelterPersonService.setBotMenuByChatId(chatId, GET_PET_D);
                sendGetPetRulesMassage(chatId);
                return;
            }

            if (presentInMenuLink(dogShelterMenu, GET_PET_DOCS_D) &&
                    updateMessage.equals(GET_PET_DOCS_D.getMenuDescription())) {
                dogShelterPersonService.setBotMenuByChatId(chatId, GET_PET_D);
                sendGetPetDocsMassage(chatId);
                return;
            }

            if (presentInMenuLink(dogShelterMenu, GET_PET_REJECT_D) &&
                    updateMessage.equals(GET_PET_REJECT_D.getMenuDescription())) {
                dogShelterPersonService.setBotMenuByChatId(chatId, GET_PET_D);
                sendGetPetRejectMassage(chatId);
                return;
            }

            if (presentInMenuLink(dogShelterMenu, GET_PET_RECOM_D) &&
                    updateMessage.equals(GET_PET_RECOM_D.getMenuDescription())) {
                dogShelterPersonService.setBotMenuByChatId(chatId, GET_PET_RECOM_D);
                sendGetPetRecomMassage(chatId);
                return;
            }

            if (presentInMenuLink(dogShelterMenu, GET_PET_RECOM_TRANSPORT_D) &&
                    updateMessage.equals(GET_PET_RECOM_TRANSPORT_D.getMenuDescription())) {
                dogShelterPersonService.setBotMenuByChatId(chatId, GET_PET_RECOM_D);
                sendGetPetRecomTransportMassage(chatId);
                return;
            }

            if (presentInMenuLink(dogShelterMenu, GET_PET_RECOM_HOUSE_D) &&
                    updateMessage.equals(GET_PET_RECOM_HOUSE_D.getMenuDescription())) {
                dogShelterPersonService.setBotMenuByChatId(chatId, GET_PET_RECOM_D);
                sendGetPetRecomHouseMassage(chatId);
                return;
            }
            if (presentInMenuLink(dogShelterMenu, GET_PET_RECOM_HOUSE_PUPPY_D) &&
                    updateMessage.equals(GET_PET_RECOM_HOUSE_PUPPY_D.getMenuDescription())) {
                dogShelterPersonService.setBotMenuByChatId(chatId, GET_PET_RECOM_D);
                sendGetPetRecomHousePuppyMassage(chatId);
                return;
            }

            if (presentInMenuLink(dogShelterMenu, GET_PET_RECOM_HOUSE_INVALIDCAT_D) &&
                    updateMessage.equals(GET_PET_RECOM_HOUSE_INVALIDCAT_D.getMenuDescription())) {
                dogShelterPersonService.setBotMenuByChatId(chatId, GET_PET_RECOM_D);
                sendGetPetRecomHouseInvalidCatMassage(chatId);
                return;
            }
            if (presentInMenuLink(dogShelterMenu, GET_PET_RECOM_KINOLOG_D) &&
                    updateMessage.equals(GET_PET_RECOM_KINOLOG_D.getMenuDescription())) {
                dogShelterPersonService.setBotMenuByChatId(chatId, GET_PET_RECOM_D);
                sendGetPetRecomKinologMassage(chatId);
                return;
            }

            if (presentInMenuLink(dogShelterMenu, REPORT_D) &&
                    updateMessage.equals(REPORT_D.getMenuDescription())) {
                dogShelterPersonService.setBotMenuByChatId(chatId, REPORT_D);
                sendReportMassage(chatId);
                return;
            }
            if (presentInMenuLink(dogShelterMenu, GET_CLIENTCONTACT_D) &&
                    updateMessage.equals(GET_CLIENTCONTACT_D.getMenuDescription())) {
                dogShelterPersonService.setBotMenuByChatId(chatId, GET_CLIENTCONTACT_D);
                sendGetClientContactMassage(chatId);
                return;
            }
            if (presentInMenuLink(dogShelterMenu, CONNECTTOADMIN_D) &&
                    updateMessage.equals(CONNECTTOADMIN_D.getMenuDescription())) {
                dogShelterPersonService.setBotMenuByChatId(chatId, INFO_D);
                sendConnectToAdminMassage(chatId);
                return;
            }
            if (dogShelterMenu.equals(GET_CLIENTCONTACT_D)) {
                sendGetClientContactProcess(chatId, updateMessage);
                return;
            }
            if (presentInMenuLink(dogShelterMenu, CONNECTTOADMIN_D) &&
                    updateMessage.equals(CONNECTTOADMIN_D.getMenuDescription())) {
                dogShelterPersonService.setBotMenuByChatId(chatId, INFO_D);
                sendConnectToAdminMassage(chatId);
                return;
            }
            if (dogShelterMenu.equals(REPORT_D)) {
                reportProcess(chatId, updateMessage);
                return;
            }

        }

        if (dogShelterMenu.equals(SENDFOTO_D)) {
            sendFotoProcess(chatId, update);
            return;
        }


    }

    public void createNewPerson(Update update) {
        long chatId = update.message().chat().id();
        String fullName = update.message().chat().firstName();
        String phone = "none";
        String telegram = " @" + update.message().chat().username();
        DogShelterMenu botMenu = START_D;
        DogShelterPet dogShelterPet=null;
        DogShelterPerson newPerson = new DogShelterPerson(
                update.message().chat().id(),
                fullName,
                phone,
                telegram,
                botMenu,
                dogShelterPet);
        dogShelterPersonService.createPerson(newPerson);
        String message = "Добро пожаловать в Приют для Собак!";
        telegramBot.execute(new SendMessage(chatId, message));
        sendStartMassage(chatId);
        logger.info("Dog Shelter Processing add new Person: {}", newPerson);
    }


    private void sendStartMassage(long chatId) {
        String message = "Главное меню";
        creatKeybordFromList(chatId, message, START_D);
        logger.info("Dog Shelter sendStartMassagee to chatId: {}", chatId);
    }

    private void sendInfoMassage(long chatId) {
        String message = "Информационное Меню";
        creatKeybordFromList(chatId, message, INFO_D);
        logger.info("Dog Shelter sendInfoMassage to chatId: {}", chatId);
    }

    private void sendContactMassage(long chatId) {
        String massage = informationRepository.findByQuestion("dogshelter_contact").getAnswer();
        telegramBot.execute(new SendMessage(chatId, massage));

        try {
            byte[] photoData = imageService.findImageByDiscription("dogshelter_location").getData();
            SendPhoto sendPhoto = new SendPhoto(chatId, photoData);
            telegramBot.execute(sendPhoto);
        } catch (NullPointerException e) {
            telegramBot.execute(new SendMessage(chatId, "Схему проезда уточните по телефону."));
        }

        logger.info("Dog Shelter sendContactMassage to chatId: {}", chatId);
    }

    private void sendShelterInfoMassage(long chatId) {
        String massage = informationRepository.findByQuestion("dogshelter_info").getAnswer();
        SendMessage message = new SendMessage(chatId, massage);
        telegramBot.execute(message);
        logger.info("Dog Shelter sendShelterInfoMassage to chatId: {}", chatId);
    }

    private void sendSecurityMassage(long chatId) {
        String message = "None";
        try {
            message = informationRepository.findByQuestion("dogshelter_security").getAnswer();
        } catch (NullPointerException e) {
            message = "Свяжитесь с администрацией что бы уточнить правила безопастности";
        }

        telegramBot.execute(new SendMessage(chatId, message));
        logger.info("Dog shelter  sendShelterInfoMassage to chatId: {}", chatId);
    }

    private void sendGetPetMassage(long chatId) {
        String message = "Взять Собаку.";
        creatKeybordFromList(chatId, message, GET_PET_D);
        logger.info("Dog shelter sendGetPetMassage to chatId: {}", chatId);
    }


    private void sendGetPetRulesMassage(long chatId) {
        String message = "None";
        try {
            message = informationRepository.findByQuestion("dogshelter_rules").getAnswer();
        } catch (NullPointerException e) {
            message = "Свяжитесь с администрацией что бы узнать правила знакомства с собакой";
        }

        telegramBot.execute(new SendMessage(chatId, message));
        logger.info("Dog shelter sendGetPetRulesMassage to chatId: {}", chatId);
    }

    private void sendGetPetDocsMassage(long chatId) {
        String message = "None";
        try {
            message = informationRepository.findByQuestion("dogshelter_docs").getAnswer();
        } catch (NullPointerException e) {
            message = "Свяжитесь с администрацией что бы узнать про документы";
        }

        telegramBot.execute(new SendMessage(chatId, message));
        logger.info("Dog shelter sendGetPetDocsMassage to chatId: {}", chatId);
    }

    private void sendGetPetRejectMassage(long chatId) {
        String message = "None";
        try {
            message = informationRepository.findByQuestion("dogshelter_reject").getAnswer();
        } catch (NullPointerException e) {
            message = "Свяжитесь с администрацией что бы узнать причины отказа";
        }

        telegramBot.execute(new SendMessage(chatId, message));
        logger.info("Dog shelter sendGetPetRejectMassage to chatId: {}", chatId);
    }

    private void sendGetPetRecomMassage(long chatId) {
        String message = "Меню рекомендаций";
        creatKeybordFromList(chatId, message, GET_PET_RECOM_D);
        logger.info("Dog shelter sendGetPetRecomMassage to chatId: {}", chatId);
    }

    private void sendGetPetRecomTransportMassage(long chatId) {
        String message = "None";
        try {
            message = informationRepository.findByQuestion("dogshelter_transport").getAnswer();
        } catch (NullPointerException e) {
            message = "Свяжитесь с администрацией что бы узнать про транспортировку кошки";
        }

        telegramBot.execute(new SendMessage(chatId, message));
        logger.info("Dog shelter sendGetPetRecomTransportMassage to chatId: {}", chatId);
    }

    private void sendGetPetRecomHouseMassage(long chatId) {
        String message = "None";
        try {
            message = informationRepository.findByQuestion("dogshelter_house").getAnswer();
        } catch (NullPointerException e) {
            message = "Свяжитесь с администрацией что бы узнать про дом для собаки";
        }

        telegramBot.execute(new SendMessage(chatId, message));
        logger.info("Dog shelter sendGetPetRecomHouseMassage to chatId: {}", chatId);
    }

    private void sendGetPetRecomHousePuppyMassage(long chatId) {
        String message = "None";
        try {
            message = informationRepository.findByQuestion("dogshelter_house_puppy").getAnswer();
        } catch (NullPointerException e) {
            message = "Свяжитесь с администрацией что бы узнать про дом для щенка";
        }

        telegramBot.execute(new SendMessage(chatId, message));
        logger.info("Dog shelter sendGetPetRecomHouseMassage to chatId: {}", chatId);
    }

    private void sendGetPetRecomHouseInvalidCatMassage(long chatId) {
        String message = "None";
        try {
            message = informationRepository.findByQuestion("dogshelter_house_invaliddog").getAnswer();
        } catch (NullPointerException e) {
            message = "Свяжитесь с администрацией что бы узнать про дом для собаки с инвлидностью";
        }

        telegramBot.execute(new SendMessage(chatId, message));
        logger.info("Dog shelter sendGetPetRecomHouseInvalidCatMassage to chatId: {}", chatId);
    }
    private void sendGetPetRecomKinologMassage(long chatId) {
        String message = "None";
        try {
            message = informationRepository.findByQuestion("dogshelter_kinolog").getAnswer();
        } catch (NullPointerException e) {
            message = "Свяжитесь с администрацией что бы узнать про рекомендации кинолога";
        }

        telegramBot.execute(new SendMessage(chatId, message));
        logger.info("Dog shelter sendGetPetRecomHouseMassage to chatId: {}", chatId);
    }
    private void sendGetClientContactMassage(long chatId) {
        String message = "Напишите свой номер телефона в формате +79998887766.";
        creatKeybordFromList(chatId, message, GET_CLIENTCONTACT_D);
        logger.info("Dog shelter sendReportMassage to chatId: {}", chatId);
    }
    private void sendGetClientContactProcess(long chatId, String updateMessage) {
        if (isPhoneNumber(updateMessage)) {
            dogShelterPersonService.setPhoneByChatId(chatId, updateMessage);
            dogShelterPersonService.setBotMenuByChatId(chatId, START_D);
            creatKeybordFromList(chatId, "Телефон записан.", START_D);
        } else {
            telegramBot.execute(new SendMessage(chatId, "Это не похоже на телефон! Напишите в формате +79998887766"));
        }

        logger.info("Dog shelter sendGetClientContactProcess to chatId: {}", chatId);
    }
    private void sendConnectToAdminMassage(long chatId) {
        String message = "Волонтер с вами свяжется в ближайшее времяю";
        telegramBot.execute(new SendMessage(chatId, message));
        logger.info("Dog Shelter sendConnectToAdminMassage to chatId: {}", chatId);
    }

    private void sendReportMassage(long chatId) {
        if (dogShelterPersonService.getPetByChatId(chatId) == null) {
            telegramBot.execute(new SendMessage(chatId, "У вас пока нет собаки."));
            return;
        }
        String message = "Меню отчета. Напишите подробный отчет про питомца.";
        creatKeybordFromList(chatId, message, REPORT_D);
        logger.info("Dog Shelter sendReportMassage to chatId: {}", chatId);
    }

    private void reportProcess(long chatId, String updateMessage) {
        informationService.createInformation(chatId + "", updateMessage);
        dogShelterPersonService.setBotMenuByChatId(chatId, SENDFOTO_D);
        telegramBot.execute(new SendMessage(chatId, "Прикрепите фотографию питомца."));
        logger.info("Dog Shelter reportProcess to chatId: {}", chatId);
    }

    private void sendFotoProcess(long chatId, Update update) {
        if (update.message().photo() != null) {
            String discripton = chatId + "_" + System.currentTimeMillis();
            saveImageToBDAndDisc(update, discripton);
            DogShelterReport report = new DogShelterReport();
            report.setId(0L);
            report.setChatId(chatId);
            LocalDateTime localDateTime = LocalDateTime.now();
            report.setDate(localDateTime);
            report.setMessage(informationService.getMessageByKey(chatId + ""));
            report.setImage(imageService.findImageByDiscription(discripton));
            dogShelterReportService.createReport(report);
            telegramBot.execute(new SendMessage(chatId, "Отчет успешно отправлен!"));
            dogShelterPersonService.setBotMenuByChatId(chatId, START_D);
            sendStartMassage(chatId);
        } else {
            telegramBot.execute(new SendMessage(chatId, "Это не фотография. Прикрепите фоторафию."));
            dogShelterPersonService.setBotMenuByChatId(chatId, SENDFOTO_D);
        }
        logger.info("Dog Shelter sendFotoProcess to chatId: {}", chatId);
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
        logger.info("Dog Shelter Processing photo load from telegram");
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
        logger.info("Dog Shelter saveImageToDisk={}", saveImageToDisk);
        String filePathName = "src/main/photos/" + discripton + "." + getExtension(telegramFullPath);
        boolean uploadImageFromTelegram = imageService.uploadImageFromDisc(discripton, filePathName);
        logger.info("Dog Shelter uploadImageFromDisc={}", uploadImageFromTelegram);

    }
}
