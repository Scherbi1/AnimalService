package sky.pro.SkyDreamTeam.AnimalService.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sky.pro.SkyDreamTeam.AnimalService.initialization.InformationLoader;
import sky.pro.SkyDreamTeam.AnimalService.initialization.ImageLoader;
import sky.pro.SkyDreamTeam.AnimalService.service.CatShelter.CatShelterMenuService;
import sky.pro.SkyDreamTeam.AnimalService.service.CatShelter.CatShelterPersonService;
import sky.pro.SkyDreamTeam.AnimalService.service.DogShelter.DogShelterMenuService;
import sky.pro.SkyDreamTeam.AnimalService.service.DogShelter.DogShelterPersonService;

import javax.annotation.PostConstruct;

import java.io.IOException;
import java.util.List;


@Service
public class TelegramBotService implements UpdatesListener {

    private Logger logger = LoggerFactory.getLogger(TelegramBotService.class);


    private InformationLoader informationLoader;
    private final ImageLoader photoLoader;
    private final DogShelterPersonService dogShelterPersonService;

    private final StartupMenuService startupMenuService;
    private final CatShelterPersonService catShelterPersonService;

    private final DogShelterMenuService dogShelterMenuService;
    private final CatShelterMenuService catShelterMenuService;

    public TelegramBotService(InformationLoader informationLoader,
                              ImageLoader photoLoader, DogShelterPersonService dogShelterPersonService,
                              StartupMenuService startupMenuService, CatShelterPersonService catShelterPersonService,
                              DogShelterMenuService dogShelterMenuService, CatShelterMenuService catShelterMenuService) {
        this.informationLoader = informationLoader;
        this.photoLoader = photoLoader;
        this.dogShelterPersonService = dogShelterPersonService;
        this.startupMenuService = startupMenuService;
        this.catShelterPersonService = catShelterPersonService;
        this.dogShelterMenuService = dogShelterMenuService;
        this.catShelterMenuService = catShelterMenuService;
    }


    @Autowired
    private TelegramBot telegramBot;


    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @PostConstruct
    public void loadInfo() throws IOException {
        informationLoader.loadInfo();
    }

    @PostConstruct
    public void loadPhoto() {
        photoLoader.loadPhoto();
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            if (update.message() != null) {
                if (dogShelterPersonDB(update)) {
                    dogShelterMenuService.selectMenu(update);
                }
                if (catShelterPersonDB(update)) {
                    catShelterMenuService.selectMenu(update);
                }
                if (!dogShelterPersonDB(update) && !catShelterPersonDB(update)) {
                    startupMenuService.selectMenu(update);
                }
            }
        });
        logger.info("Update telegram chat process {}", updates);
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    private boolean dogShelterPersonDB(Update update) {
        if (dogShelterPersonService.findPersonByChatId(update.message().chat().id()) == null) {
            return false;
        }
        return true;
    }

    private boolean catShelterPersonDB(Update update) {
        if (catShelterPersonService.findPersonByChatId(update.message().chat().id()) == null) {
            return false;
        }
        return true;
    }


}
