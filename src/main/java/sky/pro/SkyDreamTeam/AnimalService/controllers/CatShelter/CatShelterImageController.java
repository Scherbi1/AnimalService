package sky.pro.SkyDreamTeam.AnimalService.controllers.CatShelter;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sky.pro.SkyDreamTeam.AnimalService.model.CatShelter.CatShelterImage;
import sky.pro.SkyDreamTeam.AnimalService.model.CatShelter.CatShelterPerson;
import sky.pro.SkyDreamTeam.AnimalService.model.menu.CatShelterMenu;
import sky.pro.SkyDreamTeam.AnimalService.service.CatShelter.CatShelterImageService;
import sky.pro.SkyDreamTeam.AnimalService.service.CatShelter.CatShelterPersonService;


import java.io.IOException;

@RestController
@RequestMapping(path = "catShelter/image")
public class CatShelterImageController {

    private final CatShelterImageService imageService;

    public CatShelterImageController(CatShelterImageService imageService) {
        this.imageService = imageService;
    }

    @Operation(summary = "Загрузка файла картинки",
            responses = {@ApiResponse(
                    responseCode = "200",
                    description = "файл успешно загружен",
                    content = @Content(
                            mediaType = MediaType.MULTIPART_FORM_DATA_VALUE,
                            schema = @Schema(implementation = CatShelterImage.class)
                    )
            )})

    @PostMapping(value = "/upload/{description}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadImage(@Parameter(description = "Название файла")
                                              @PathVariable String description,
                                              @Parameter(description = "Файл картинки")
                                              @RequestParam MultipartFile photoFile) throws IOException {
        imageService.uploadImage(description, photoFile);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Поиск картинки по названию",
            responses = {@ApiResponse(
                    responseCode = "200",
                    description = "Файл картинки найден",
                    content = @Content(
                            mediaType = MediaType.MULTIPART_FORM_DATA_VALUE)
            ), @ApiResponse(
                    responseCode = "404",
                    description = "Файл картинки не найден",
                    content = @Content(
                            mediaType = MediaType.MULTIPART_FORM_DATA_VALUE)
            )
            }
    )
    @GetMapping(value = "/findImageByDescription/{description}")
    public ResponseEntity<byte[]> findImageByDescription(@Parameter(description = "Название файла")
                                                         @PathVariable String description) {
       CatShelterImage image = imageService.findImageByDescription(description);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(image.getMediaType()));
        headers.setContentLength(image.getData().length);
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(image.getData());
    }
}