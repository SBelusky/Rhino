package com.samuell.rhino.model.mapper;

import com.samuell.rhino.model.Log;
import com.samuell.rhino.model.User;
import com.samuell.rhino.model.dto.LogDto;
import com.samuell.rhino.model.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LogMapper {
    LogMapper INSTANCE = Mappers.getMapper(LogMapper.class);


    @Mapping(target = "bug", ignore=true)
    LogDto toLogDto(Log log);

    @Mappings({
            @Mapping(target = "email", ignore=true),
            @Mapping(target = "telephone_number", ignore=true),
            @Mapping(target = "username", ignore=true),
            @Mapping(target = "password", ignore=true),
            @Mapping(target = "role", ignore=true),
            @Mapping(target = "created_at", ignore=true)
    })
    UserDto toUserDto(User user);

    User toUser(UserDto userDto);
}
