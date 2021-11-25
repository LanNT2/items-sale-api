package rikkeisoft.com.itemsale.service;
import org.springframework.web.multipart.MultipartFile;
import rikkeisoft.com.itemsale.dto.ChangePasswordDTO;
import rikkeisoft.com.itemsale.dto.UserDTO;
import rikkeisoft.com.itemsale.exception.*;
import rikkeisoft.com.itemsale.model.Item;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface UserService {
    UserDTO getUserInfo(Integer userId) throws UserNotFoundException;
    UserDTO updateUserInfo(Integer userId, UserDTO userDTO) throws UserNotFoundException;
    String saveProfileImage(Integer userId, MultipartFile image) throws UserNotFoundException, IOException, InvalidFileExtensionException;
    String changePassword(Integer id, ChangePasswordDTO changePasswordDTO) throws UserNotFoundException, InvalidPassword;
    byte[] getUserProfileImage(Integer id) throws UserNotFoundException, IOException;
    String addItemToCart(Integer itemId, Integer userId) throws ItemNotFoundException;
    List<Item> getItemListInCart(Integer userId) throws CartNotFoundException;
}
