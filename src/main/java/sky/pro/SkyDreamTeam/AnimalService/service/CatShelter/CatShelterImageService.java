package sky.pro.SkyDreamTeam.AnimalService.service.CatShelter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import sky.pro.SkyDreamTeam.AnimalService.exceptions.ImageNotFoundException;
import sky.pro.SkyDreamTeam.AnimalService.model.CatShelter.CatShelterImage;
import sky.pro.SkyDreamTeam.AnimalService.model.DogShelter.DogShelterImage;
import sky.pro.SkyDreamTeam.AnimalService.repository.CatShelter.CatShelterImageRepository;
import sky.pro.SkyDreamTeam.AnimalService.utils.JavaFileToMultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;


import static java.nio.file.StandardOpenOption.CREATE_NEW;
import static sky.pro.SkyDreamTeam.AnimalService.utils.FileUtil.removeFileExtension;


@Service
@Transactional
public class CatShelterImageService {
    @Value("${path.to.imageData.folder}")
    private String imageDir;

    private final CatShelterImageRepository imageRepository;

    Logger logger = LoggerFactory.getLogger(CatShelterImageService.class);

    private CatShelterImageService(CatShelterImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }
   public void uploadImage(String description, MultipartFile imageFile) throws IOException {
        logger.info("Was invoked method for uploadPhoto");

        Path filePath = Path.of(imageDir, description + "." + getExtensions(imageFile.getOriginalFilename()));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);
        try (
                InputStream is = imageFile.getInputStream();
                OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
                BufferedInputStream bis = new BufferedInputStream(is, 1024);
                BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
        ) {

            bis.transferTo(bos);
        }
        CatShelterImage image = new CatShelterImage();
        image.setDescription(description);
        image.setFileSize(imageFile.getSize());
        image.setMediaType(imageFile.getContentType());
        image.setData(imageFile.getBytes());
        imageRepository.save(image);
    }

    public boolean uploadImageFromDisc(File file) {
        logger.info("Was invoked method for uploadImageFromDisc");
        String description = removeFileExtension(file.getName()).toLowerCase();
        try {
            uploadImage(description, new JavaFileToMultipartFile(file));
        } catch (IOException e) {
            return false;
        }
        return  true;
    }

    public boolean uploadImageFromDisc(String description, String filePath) {
        logger.info("Was invoked method for uploadImageFromDisc {}",filePath);
        File file = new File(filePath);
        {
            try {
                uploadImage(description, new JavaFileToMultipartFile(file));
            } catch (IOException e) {
                return false;
            }

            return true;
        }
    }
    public CatShelterImage findImageByDescription(String description) {
        logger.info("Was invoked method for findImage");
        return imageRepository.findByDescription(description).orElseThrow(ImageNotFoundException::new);
    }
    private String getExtensions(String fileName) {
        logger.info("Was invoked method for getExtensions");
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
}
