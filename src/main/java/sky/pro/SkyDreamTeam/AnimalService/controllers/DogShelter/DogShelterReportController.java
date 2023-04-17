package sky.pro.SkyDreamTeam.AnimalService.controllers.DogShelter;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sky.pro.SkyDreamTeam.AnimalService.model.DogShelter.DogShelterPet;
import sky.pro.SkyDreamTeam.AnimalService.model.DogShelter.DogShelterReport;
import sky.pro.SkyDreamTeam.AnimalService.service.DogShelter.DogShelterReportService;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping(path = "dogShelter/report")
public class DogShelterReportController {

    private final DogShelterReportService dogShelterReportService;

    public DogShelterReportController(DogShelterReportService dogShelterReportService) {
        this.dogShelterReportService = dogShelterReportService;
    }

    @Operation(summary = "Создать отчет",
            responses = {@ApiResponse(
                    responseCode = "200",
                    description = "Животное найдено",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = DogShelterReport.class)
                    )
            ), @ApiResponse(
                    responseCode = "404",
                    description = "Животное не найдено в базе",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE)
            )}
    )
    @PostMapping
    public ResponseEntity<DogShelterReport> createReport(@Parameter(description = "Данные отчета в формате JSON")
            @RequestBody DogShelterReport report) {
        DogShelterReport createReport = dogShelterReportService.createReport(report);
        return ResponseEntity.ok(createReport);
    }
    @Operation(summary = "Получить все отчеты",
            responses = {@ApiResponse(
                    responseCode = "200",
                    description = "Животное найдено",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = DogShelterReport.class)
                    )
            ), @ApiResponse(
                    responseCode = "404",
                    description = "Животное не найдено в базе",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE)
            )}
    )
    @GetMapping
    public Collection<DogShelterReport> getDogShelterReportAll () {
        return ResponseEntity.ok(dogShelterReportService.findReportAll()).getBody();
    }
    @Operation(summary = "Получить отет по ChatID",
            responses = {@ApiResponse(
                    responseCode = "200",
                    description = "Животное найдено",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = DogShelterReport.class)
                    )
            ), @ApiResponse(
                    responseCode = "404",
                    description = "Животное не найдено в базе",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE)
            )}
    )
    @GetMapping("{chatId}")
    public ResponseEntity<List<DogShelterReport>> getDogShelterReportById(@PathVariable Long chatId) {
      //  DogShelterReport report = (DogShelterReport) dogShelterReportService.findReportByChatId(chatId);
        List<DogShelterReport> report = dogShelterReportService.findReportByChatId(chatId);
        return ResponseEntity.ok(report);
    }
    @Operation(summary = "Редактировать отчет",
            responses = {@ApiResponse(
                    responseCode = "200",
                    description = "Животное найдено",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = DogShelterReport.class)
                    )
            ), @ApiResponse(
                    responseCode = "404",
                    description = "Животное не найдено в базе",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE)
            )}
    )
    @PutMapping
    public ResponseEntity<DogShelterReport> updateDogShelterReport(@RequestBody DogShelterReport report) {
        DogShelterReport updateReport = dogShelterReportService.editReport(report);
        if (updateReport == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updateReport);
    }
    @Operation(summary = "Удалить отчет",
            responses = {@ApiResponse(
                    responseCode = "200",
                    description = "Животное найдено",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = DogShelterReport.class)
                    )
            ), @ApiResponse(
                    responseCode = "404",
                    description = "Животное не найдено в базе",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE)
            )}
    )
    @DeleteMapping
    public void deleteReport(@Parameter(description = "id отчета")
                                 @RequestParam(required = false) Long id,
                             @Parameter(description = "ChatId пользователя")
                                 @RequestParam(required = false) Long chatId) {
        if (chatId != null) {
            dogShelterReportService.deleteReportByChatId(chatId);
        }
        if (id != null) {
            dogShelterReportService.deleteReport(id);
        }
    }
}
