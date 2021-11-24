package rikkeisoft.com.itemsale.service.impl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import rikkeisoft.com.itemsale.constant.PathConstant;
import rikkeisoft.com.itemsale.dto.ChangePasswordDTO;
import rikkeisoft.com.itemsale.dto.UserDTO;
import rikkeisoft.com.itemsale.exception.InvalidFileExtensionException;
import rikkeisoft.com.itemsale.exception.InvalidPassword;
import rikkeisoft.com.itemsale.exception.UserNotFoundException;
import rikkeisoft.com.itemsale.helper.FileHelper;
import rikkeisoft.com.itemsale.helper.Helper;
import rikkeisoft.com.itemsale.model.User;
import rikkeisoft.com.itemsale.repository.UserRepository;
import rikkeisoft.com.itemsale.service.UserService;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
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
    public File getUserProfileImage(Integer id) throws UserNotFoundException, FileNotFoundException {
        Optional<User> userOpt = userRepository.findByIdAndIsDeleted(id, 0);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (user.getImageUrl() != null) {
                String fileName = user.getImageUrl();
                Boolean isExist = FileHelper.checkExist(fileName, PathConstant.PATH_TO_PROFILE_IMG);
                if (isExist) {
                    File file = new File(PathConstant.PATH_TO_PROFILE_IMG + fileName);
                    return file;
                }
                throw new FileNotFoundException();
            }
            throw new FileNotFoundException();
        }
        throw new UserNotFoundException();
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
