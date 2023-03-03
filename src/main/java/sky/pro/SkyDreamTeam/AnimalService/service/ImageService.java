package sky.pro.SkyDreamTeam.AnimalService.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import sky.pro.SkyDreamTeam.AnimalService.model.Image;
import sky.pro.SkyDreamTeam.AnimalService.repository.ImageRepository;
import sky.pro.SkyDreamTeam.AnimalService.utils.JavaFileToMultipartFile;
import static sky.pro.SkyDreamTeam.AnimalService.utils.FileUtil.getExtension;


;import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.CREATE_NEW;
import static sky.pro.SkyDreamTeam.AnimalService.utils.FileUtil.removeFileExtension;

@Service
@Transactional
public class ImageService {
    @Value("${path.to.imageData.folder}")
    private String imageDir;

    private final ImageRepository imageRepository;
    Logger logger = LoggerFactory.getLogger(ImageService.class);

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }
    public void uploadImage(String discription, MultipartFile imageFile) throws IOException {
        logger.info("Was invoked method for uploadPhoto");

        Path filePath = Path.of(imageDir, discription + "." + getExtension(imageFile.getOriginalFilename()));
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
        Image image = new Image();
        image.setDiscription(discription);
        image.setFileSize(imageFile.getSize());
        image.setMediaType(imageFile.getContentType());
        image.setData(imageFile.getBytes());
        imageRepository.save(image);
    }

    public boolean uploadImageFromDisc(File file) {
        logger.info("Was invoked method for uploadImageFromDisc");
        String discription = removeFileExtension(file.getName()).toLowerCase();
        try {
            uploadImage(discription, new JavaFileToMultipartFile(file));
        } catch (IOException e) {
            return false;
        }
        return  true;
    }

    public boolean uploadImageFromDisc(String discription, String filePath) {
        logger.info("Was invoked method for uploadImageFromDisc {}",filePath);
        File file = new File(filePath);
        {
            try {
                uploadImage(discription, new JavaFileToMultipartFile(file));
            } catch (IOException e) {
                return false;
            }

            return true;
        }
    }
    public Image findImageByDiscription(String discription) {
        logger.info("Was invoked method for findImage");
        return imageRepository.findByDiscription(discription);
    }

}
