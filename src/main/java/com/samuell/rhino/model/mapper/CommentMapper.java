package com.samuell.rhino.model.mapper;

import com.samuell.rhino.model.Comment;
import com.samuell.rhino.model.User;
import com.samuell.rhino.model.dto.CommentDto;
import com.samuell.rhino.model.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CommentMapper {
    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    CommentDto toCommentDto(Comment comment);

    Comment toComment(CommentDto commentDto);

    @Mappings({
            @Mapping(target = "email", ignore=true),
            @Mapping(target = "telephone_number", ignore=true),
            @Mapping(target = "login_name", ignore=true),
            @Mapping(target = "login_password", ignore=true),
            @Mapping(target = "role", ignore=true),
    })
    UserDto toUserDto(User user);

    User toUser(UserDto userDto);
}
