package com.example.User.Service;

import com.example.User.DPCombinator.UserInputValidator;
import com.example.User.Entity.DTO.UserRequestDto;
import com.example.User.Entity.DTO.UserResponseDto;
import com.example.User.Entity.User;
import com.example.User.Enumeration.ExceptionsMessage;
import com.example.User.Exception.UserInputNotValidException;
import com.example.User.Exception.UserNotFoundException;
import com.example.User.Repository.UserRepo;
import com.example.User.Utils.MappingProfile;
import lombok.AllArgsConstructor;
import org.modelmapper.ValidationException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImp implements UserService{
    private final UserRepo userRepository;
    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(MappingProfile::mapToUserDto).toList();
    }
    public UserResponseDto createUser(UserRequestDto userDto) {
        var validationErrors = UserInputValidator.validate(userDto);
        if(!validationErrors.isEmpty()){
            throw new ValidationException(validationErrors);
        }
        var userEntity = MappingProfile.map(userDto, User.class);
        return MappingProfile.map(userRepository.save(userEntity), UserResponseDto.class);
    }
    public Object getUserById(Long id) throws UserNotFoundException{
        var user = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException(ExceptionsMessage.USER_NOT_FOUND.getMessage()));
        return new Object() {
            public Long id = user.getId();
            public String fullName = user.getLastName().toUpperCase() + ", " + user.getFirstName();
            public String email = user.getEmail();

        };
    }

    public UserResponseDto addUser(UserRequestDto userDto) {
        var validationErrors = UserInputValidator.validate(userDto);
        if(!validationErrors.isEmpty()){
            throw new ValidationException(validationErrors);
        }
        var userEntity = MappingProfile.map(userDto, User.class);
        return MappingProfile.map(userRepository.save(userEntity), UserResponseDto.class);
    }

    public UserResponseDto updateUser(Long id, UserRequestDto userDto) throws UserNotFoundException {
        var user = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException(ExceptionsMessage.USER_INPUT_NOT_VALID.getMessage()));
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        return MappingProfile.mapToUserDto(userRepository.save(user));
    }

    @Override
    public void deleteUser(Long id) throws Exception {
        var user = userRepository.findById(id).orElseThrow(() -> new Exception("User not found"));
        userRepository.delete(user);
    }



    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void DeleteUser(Long userId) {
        // Delete user logic
        rabbitTemplate.convertAndSend("tasksExchange", "tasksRouting", userId);
    }



    private final UserRepo repository;

    @Override
    public User getUserByEmail(String email) {

        return Optional.ofNullable(repository.findByEmail(email))
                .orElseThrow(() -> new RuntimeException("User with email is not found."));

    }

}
