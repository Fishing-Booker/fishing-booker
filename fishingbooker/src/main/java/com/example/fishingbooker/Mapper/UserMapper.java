package com.example.fishingbooker.Mapper;

import com.example.fishingbooker.DTO.UserDTO;
import com.example.fishingbooker.Model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class UserMapper {

    @Autowired
    private static ModelMapper modelMapper;

    public static UserDTO convertToDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    public static User convertToUser(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }
}
