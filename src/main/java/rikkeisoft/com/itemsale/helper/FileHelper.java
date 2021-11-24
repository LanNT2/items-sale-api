package rikkeisoft.com.itemsale.helper;
import org.springframework.web.multipart.MultipartFile;
import rikkeisoft.com.itemsale.constant.PathConstant;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
public class FileHelper {
    private final static int MAX_UPLOAD_FILE_SIZE = 25000000;

    public static void createFolderIfNotExists(String pathFolder) {
        if (Files.notExists(Paths.get(pathFolder))) {
            File file = new File(pathFolder);
            file.mkdirs();
        }
    }

    public static boolean saveFile(MultipartFile multipartFile, String folderPath, String fileName) throws IOException {
        String pathFolder = PathConstant.PATH_TO_PROJECT + folderPath;
        FileHelper.createFolderIfNotExists(pathFolder);
        byte[] bytes = multipartFile.getBytes();
        Path path = Paths.get(pathFolder + fileName);
        Files.write(path, bytes);
        return true;
    }

    public static boolean isValidFileExtension(String fileCVName) {
        int index = fileCVName.lastIndexOf('.');
        String extension = fileCVName.substring(index + 1);

        return (extension.equalsIgnoreCase("jpg") ||
                extension.equalsIgnoreCase("jpeg") ||
                extension.equalsIgnoreCase("png") ||
                extension.equalsIgnoreCase("tiff") ||
                extension.equalsIgnoreCase("gif"));
    }

    public static Boolean checkExist(String fileName, String folderPath) {
        String pathFolder = PathConstant.PATH_TO_PROJECT + folderPath;
        File file = new File(pathFolder + fileName);
        return file.exists();
    }

    public static boolean isValidMaxUploadFileSize(MultipartFile multipartFile) {
        if (multipartFile.getSize() <= MAX_UPLOAD_FILE_SIZE) {
            return true;
        } else {
            return false;
        }
    }

}
