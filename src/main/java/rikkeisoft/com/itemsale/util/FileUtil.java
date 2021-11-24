package rikkeisoft.com.itemsale.util;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Objects;
public class FileUtil {
    private FileUtil() {
    }
    static File convertMultiPartToFile(MultipartFile multipartFile) throws IOException {
        File convertFile = new File(Objects.requireNonNull(String.format("%s_%s", new Date().getTime(), multipartFile.getOriginalFilename())));
        try (FileOutputStream fos = new FileOutputStream(convertFile)) {
            fos.write(multipartFile.getBytes());
        }
        return convertFile;
    }

}
