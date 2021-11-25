package rikkeisoft.com.itemsale.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import rikkeisoft.com.itemsale.dto.ChangePasswordDTO;
import rikkeisoft.com.itemsale.dto.UserDTO;
import rikkeisoft.com.itemsale.exception.*;
import rikkeisoft.com.itemsale.model.Item;
import rikkeisoft.com.itemsale.service.UserService;

import java.io.IOException;
import java.util.List;

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

    @GetMapping("profile-image/view/{id}")
    public ResponseEntity<byte[]> getUserImage(@PathVariable("id") Integer id) throws UserNotFoundException, IOException {
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(userService.getUserProfileImage(id));
    }

    @PostMapping("/cart/add/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> addItemToCart(@PathVariable("id") Integer userId, @RequestParam(name = "itemId", required = true) Integer itemId) throws ItemNotFoundException {
        return new ResponseEntity<>(userService.addItemToCart(itemId, userId), HttpStatus.OK);
    }

    @GetMapping("cart/items/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<Item>> getItemsInCart(@PathVariable("id") Integer id) throws CartNotFoundException {
        return new ResponseEntity<>(userService.getItemListInCart(id), HttpStatus.OK);
    }


}
