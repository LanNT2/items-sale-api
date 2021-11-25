package rikkeisoft.com.itemsale.service.impl;

import org.apache.commons.io.FileUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import rikkeisoft.com.itemsale.constant.PathConstant;
import rikkeisoft.com.itemsale.dto.ChangePasswordDTO;
import rikkeisoft.com.itemsale.dto.UserDTO;
import rikkeisoft.com.itemsale.exception.*;
import rikkeisoft.com.itemsale.helper.FileHelper;
import rikkeisoft.com.itemsale.helper.Helper;
import rikkeisoft.com.itemsale.model.Cart;
import rikkeisoft.com.itemsale.model.Item;
import rikkeisoft.com.itemsale.model.User;
import rikkeisoft.com.itemsale.repository.CartRepository;
import rikkeisoft.com.itemsale.repository.ItemRepository;
import rikkeisoft.com.itemsale.repository.UserRepository;
import rikkeisoft.com.itemsale.service.UserService;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;
    private final CartRepository cartRepository;
    private final PasswordEncoder passwordEncoder;
    public UserServiceImpl(UserRepository userRepository,ItemRepository itemRepository,CartRepository cartRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
        this.cartRepository= cartRepository;
        this.passwordEncoder = encoder;
    }
    @Override
    public UserDTO getUserInfo(Integer userId) throws UserNotFoundException {
        Optional<User> userOpt = userRepository.findByIdAndIsDeleted(userId, 0);
        if (userOpt.isPresent()) {
            return Helper.mapToUserDTO(userOpt.get());
        }
        throw new UserNotFoundException();
    }
    @Override
    public UserDTO updateUserInfo(Integer userId, UserDTO userDTO) throws UserNotFoundException {
        Optional<User> userOpt = userRepository.findByIdAndIsDeleted(userId, 0);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setEmail(userDTO.getEmail());
            user.setPhoneNumber(userDTO.getPhone());
            userRepository.save(user);
            return userDTO;
        }
        throw new UserNotFoundException();
    }
    @Override
    public String saveProfileImage(Integer userId, MultipartFile image) throws UserNotFoundException, IOException, InvalidFileExtensionException {
        Optional<User> userOpt = userRepository.findByIdAndIsDeleted(userId, 0);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (!image.isEmpty()) {
                boolean isValidFileExtension = FileHelper.isValidFileExtension(image.getOriginalFilename());
                if (!isValidFileExtension) {
                    throw new InvalidFileExtensionException();
                }
                String fileName = UUID.randomUUID() + "_" + image.getOriginalFilename();
                saveImage(image, fileName);
                user.setImageUrl(fileName);
                userRepository.save(user);
                return "Add Profile Image success!";
            }
        }
        throw new UserNotFoundException();
    }
    @Override
    public String changePassword(Integer id, ChangePasswordDTO changePasswordDTO) throws UserNotFoundException, InvalidPassword {
        Optional<User> userOpt = userRepository.findByIdAndIsDeleted(id, 0);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            String oldPassword = user.getPassword();
            Boolean isValid = isValidPassword(oldPassword, changePasswordDTO);
            if (isValid) {
                String newPassword = changePasswordDTO.getNewPassword();
                user.setPassword(passwordEncoder.encode(newPassword));
                userRepository.save(user);
                return "Change password success";
            }
            throw new InvalidPassword();
        }
        throw new UserNotFoundException();
    }
    @Override
    public byte[] getUserProfileImage(Integer id) throws UserNotFoundException, IOException {
        Optional<User> userOpt = userRepository.findByIdAndIsDeleted(id, 0);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (user.getImageUrl() != null) {
                String fileName = user.getImageUrl();
                Boolean isExist = FileHelper.checkExist(fileName, PathConstant.PATH_TO_PROFILE_IMG);
                if (isExist) {
                    byte[] image = new byte[0];
                    image = FileUtils.readFileToByteArray(new File(PathConstant.PATH_TO_PROJECT+PathConstant.PATH_TO_PROFILE_IMG+fileName));
                    return image;
                }
                throw new FileNotFoundException();
            }
            throw new FileNotFoundException();
        }
        throw new UserNotFoundException();
    }

    @Override
    public String addItemToCart(Integer itemId, Integer userId) throws ItemNotFoundException {
        Optional<Item> itemOpt = itemRepository.findAllByIdAndIsDeleted(itemId,0);
        if(itemOpt.isPresent()){
            Cart cart = cartRepository.findByUserId(userId).get();
            List<Item> items = cart.getItems();
            items.add(itemOpt.get());
            cart.setItems(items);
            cartRepository.save(cart);
            return "Add to cart success";
        }
        throw new ItemNotFoundException();
    }

    @Override
    public List<Item> getItemListInCart(Integer userId) throws CartNotFoundException {
        Optional<Cart> cartOpt = cartRepository.findByUserId(userId);
        if(cartOpt.isPresent()){
            Cart cart = cartOpt.get();
            List<Item> items = cart.getItems();
            return items;
        }
        throw new CartNotFoundException();
    }

    public boolean saveImage(MultipartFile multipartFile, String fileName) throws IOException {
        return FileHelper.saveFile(multipartFile, PathConstant.PATH_TO_PROFILE_IMG, fileName);
    }

    public Boolean isValidPassword(String oldPassword, ChangePasswordDTO changePasswordDTO) {
        String oldPasswordDTO = changePasswordDTO.getOldPassword();
        String newPassword = changePasswordDTO.getNewPassword();
        String confirmPassword = changePasswordDTO.getConfirmPassword();

        if (newPassword.equals(oldPasswordDTO) || newPassword.equals(oldPassword)) {
            return false;
        }
        if (!newPassword.equals(confirmPassword)) {
            return false;
        }
        return userPasswordEncoder().matches(oldPasswordDTO, oldPassword);

    }
    public PasswordEncoder userPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }



}
