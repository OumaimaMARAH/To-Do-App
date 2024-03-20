package com.example.User.Utils;

import com.example.User.Entity.DTO.UserRequestDto;
import com.example.User.Entity.DTO.UserResponseDto;
import com.example.User.Entity.User;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;

public class MappingProfile {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static <T> T map(Object source,Class<T> destinationType){
        return modelMapper.map(source,destinationType);
    }


    public static User mapToUserEntity(UserRequestDto userDto) {
        return modelMapper.map(userDto, User.class);
    }

    public static UserResponseDto mapToUserDto(User user) {
        return modelMapper.map(user, UserResponseDto.class);
    }

    static {
        Converter<User, UserResponseDto> userToUserResponseDtoConverter = new Converter<User, UserResponseDto>() {
            public UserResponseDto convert(MappingContext<User, UserResponseDto> context) {
                User source = context.getSource();
                UserResponseDto destination = new UserResponseDto();
                destination.setId(source.getId());
                destination.setEmail(source.getEmail());
                destination.setFullName(source.getFirstName() + " " + source.getLastName());
                return destination;
            }
        };
        modelMapper.addConverter(userToUserResponseDtoConverter);
    }
}
