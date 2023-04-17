package sky.pro.SkyDreamTeam.AnimalService.controllers.CatShelter;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sky.pro.SkyDreamTeam.AnimalService.model.CatShelter.CatShelterPet;
import sky.pro.SkyDreamTeam.AnimalService.model.CatShelter.CatShelterReport;
import sky.pro.SkyDreamTeam.AnimalService.service.CatShelter.CatShelterReportService;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping(path = "catShelter/report")
public class CatShelterReportController {

    public final CatShelterReportService reportService;

    public CatShelterReportController(CatShelterReportService reportService) {
        this.reportService = reportService;
    }

    @Operation(summary = "Создание отчета",
            responses = {@ApiResponse(
                    responseCode = "200",
                    description = "Отчет успешно создан",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = CatShelterReport.class)
                    )
            )}
    )
    @PostMapping
    public ResponseEntity<CatShelterReport> createReport(@Parameter(description = "Данные отчета в формате JSON")
                                                         @RequestBody CatShelterReport report) {
        CatShelterReport createdReport = reportService.creatReport(report);
        return ResponseEntity.ok(createdReport);
    }

    @Operation(summary = "Поиск отчетов в базе по ChatId пользователя",
            responses = {@ApiResponse(
                    responseCode = "200",
                    description = "Отчеты найдены",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = CatShelterReport.class)
                    )
            ), @ApiResponse(
                    responseCode = "404",
                    description = "Отчеты не найдены в базе",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE)
            )}
    )
    @GetMapping("{chatId}")
    public ResponseEntity<List<CatShelterReport>> getReportByChatId(@Parameter(description = "Имя животного")
                                                            @PathVariable Long chatId) {
        List<CatShelterReport> report = reportService.findReportByChatId(chatId);
        return ResponseEntity.ok(report);
    }
    @Operation(summary = "Поиск отчетов",
            responses = {@ApiResponse(
                    responseCode = "200",
                    description = "Отчеты найдены",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = CatShelterReport.class)
                    )
            ), @ApiResponse(
                    responseCode = "404",
                    description = "Отчеты не найдены в базе",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE)
            )}
    )

    @GetMapping
    public Collection<CatShelterReport> getReport() {
        return reportService.findReportAll();
    }
    @Operation(summary = "Редактирование отчета",
            responses = {@ApiResponse(
                    responseCode = "200",
                    description = "Отчет отредактирован",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = CatShelterReport.class)
                    )
            )}
    )
    @PutMapping()

    public ResponseEntity<CatShelterReport> updateReport(@Parameter(description = "Данные отчета в формате JSON")
                                                         @RequestBody CatShelterReport report) {
        CatShelterReport updatedPet = reportService.editReport(report);
        if (updatedPet == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedPet);
    }

    @Operation(summary = "Удаление отчетов",
            responses = {@ApiResponse(
                    responseCode = "200",
                    description = "Отчеты удалены",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = CatShelterPet.class)
                    )
            )}
    )
    @DeleteMapping
    public ResponseEntity deleteReport(@Parameter(description = "id отчета")
                                       @RequestParam(required = false) Long id,
                                       @Parameter(description = "ChatId пользователя")
                                       @RequestParam(required = false) Long chatId) {
        if (id != null) {
            reportService.deleteReport(id);
        }
        if (chatId != null) {
            reportService.deleteReportsByChatId(chatId);
        }
        return ResponseEntity.ok().build();
    }
}

