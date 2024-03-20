package com.example.User.Controller;


import com.example.User.Entity.DTO.UserRequestDto;
import com.example.User.Entity.DTO.UserResponseDto;
import com.example.User.Entity.User;
import com.example.User.Exception.DataNotValidException;
import com.example.User.Exception.UserInputNotValidException;
import com.example.User.Exception.UserNotFoundException;
import com.example.User.Service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/v1/users" /*, produces = "application/json", consumes = "application/json"*/)
public class UserController {
    private final UserService userService;

    @GetMapping(value = "/",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllUsers() {

        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
        } catch (ChangeSetPersister.NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to fetch user", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody UserRequestDto userDto) {
        try{
            UserResponseDto userResponseDto = userService.createUser(userDto);
            return ResponseEntity.ok(userResponseDto);
        }catch (UserInputNotValidException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserRequestDto userDto) {
        try {
            return new ResponseEntity<>(userService.updateUser(id, userDto), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (DataNotValidException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to update user", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok("User with ID " + id + " deleted successfully");
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to delete user", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //private final UserService userService;

   /* @PostMapping(value = "/authenticate")
    public ResponseEntity<String> authenticateUser(
            @Valid @RequestBody AuthenticationRequest request
    ) {

        User user = userService.getUserByEmail(request.getEmail());

        var password = CryptoUtils.decrypt(user.getPassword());

        if (user.getPassword().equals(password)) {
            // generate token
            var token = jwtTokenUtil.generate(user, "ACCESS");

            return ResponseEntity.ok(new AuthToken(token));
        }

        return ResponseEntity.badRequest().build();
    }*/
}
