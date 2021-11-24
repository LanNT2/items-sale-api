package rikkeisoft.com.itemsale.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import rikkeisoft.com.itemsale.dto.ChangePasswordDTO;
import rikkeisoft.com.itemsale.dto.UserDTO;
import rikkeisoft.com.itemsale.exception.InvalidFileExtensionException;
import rikkeisoft.com.itemsale.exception.InvalidPassword;
import rikkeisoft.com.itemsale.exception.UserNotFoundException;
import rikkeisoft.com.itemsale.service.UserService;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
@RestController
@CrossOrigin("*")
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<UserDTO> getUserInfo(@PathVariable("id") Integer id) throws UserNotFoundException {
        return new ResponseEntity<>(userService.getUserInfo(id), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<UserDTO> updateProfile(@PathVariable("id") Integer id, @RequestBody UserDTO user) throws UserNotFoundException {
        return new ResponseEntity<>(userService.updateUserInfo(id, user), HttpStatus.OK);
    }

    @PostMapping("/profile-image/add/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<String> addProfileImage(@PathVariable("id") Integer id, @RequestParam("image") MultipartFile image) throws UserNotFoundException, IOException, InvalidFileExtensionException {
        return new ResponseEntity<>(userService.saveProfileImage(id, image), HttpStatus.OK);
    }

    @PutMapping("/password/change/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<String> changPassword(@PathVariable("id") Integer id, @RequestBody ChangePasswordDTO changePasswordDTO) throws UserNotFoundException, InvalidPassword {
        return new ResponseEntity<>(userService.changePassword(id, changePasswordDTO), HttpStatus.OK);
    }

    @GetMapping("profile-image/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<File> getUserImage(@PathVariable("id") Integer id) throws UserNotFoundException, FileNotFoundException {
        return new ResponseEntity<>(userService.getUserProfileImage(id), HttpStatus.OK);
    }
}
