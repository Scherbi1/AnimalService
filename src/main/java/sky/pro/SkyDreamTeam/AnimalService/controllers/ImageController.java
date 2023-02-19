package sky.pro.SkyDreamTeam.AnimalService.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sky.pro.SkyDreamTeam.AnimalService.model.Image;
import sky.pro.SkyDreamTeam.AnimalService.service.ImageService;

import java.io.IOException;

@RestController
@RequestMapping(path = "image")
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping(value = "/upload/{discription}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadImage(@PathVariable String discription, @RequestParam MultipartFile photoFile) throws IOException {
        imageService.uploadImage(discription,photoFile);
        return ResponseEntity.ok().build();
    }
    @GetMapping(value = "/findImageByDiscription/{discription}")
    public ResponseEntity<Image> findImageByDiscription(@RequestParam String discription) {
        return ResponseEntity.ok(imageService.findImageByDiscription(discription));
    }
}
