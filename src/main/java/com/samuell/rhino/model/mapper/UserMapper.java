package com.samuell.rhino.model.mapper;

import com.samuell.rhino.model.User;
import com.samuell.rhino.model.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTACE = Mappers.getMapper(UserMapper.class);

    UserDto userToUserDto(User movie);

    User userDtoToUser(UserDto movieDto);
}