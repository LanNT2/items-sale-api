package rikkeisoft.com.itemsale.service;
import org.springframework.web.multipart.MultipartFile;
import rikkeisoft.com.itemsale.dto.ChangePasswordDTO;
import rikkeisoft.com.itemsale.dto.UserDTO;
import rikkeisoft.com.itemsale.exception.InvalidFileExtensionException;
import rikkeisoft.com.itemsale.exception.InvalidPassword;
import rikkeisoft.com.itemsale.exception.UserNotFoundException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
public interface UserService {
    UserDTO getUserInfo(Integer userId) throws UserNotFoundException;
    UserDTO updateUserInfo(Integer userId, UserDTO userDTO) throws UserNotFoundException;
    String saveProfileImage(Integer userId, MultipartFile image) throws UserNotFoundException, IOException, InvalidFileExtensionException;
    String changePassword(Integer id, ChangePasswordDTO changePasswordDTO) throws UserNotFoundException, InvalidPassword;
    File getUserProfileImage(Integer id) throws UserNotFoundException, FileNotFoundException;
}
