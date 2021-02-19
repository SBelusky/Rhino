package com.samuell.rhino.model.mapper;

import com.samuell.rhino.model.Attachment;
import com.samuell.rhino.model.User;
import com.samuell.rhino.model.dto.AttachmentDto;
import com.samuell.rhino.model.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AttachmentMapper {
    AttachmentMapper INSTANCE = Mappers.getMapper(AttachmentMapper.class);


    @Mapping(target = "bug", ignore=true)
    AttachmentDto toAttachmentDto(Attachment attachment);

    Attachment toAttachment(AttachmentDto attachmentDto);

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
