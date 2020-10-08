package com.samuell.rhino.model.mapper;

import com.samuell.rhino.model.UserHasProject;
import com.samuell.rhino.model.dto.UserHasProjectDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper
public interface UserHasProjectMapper {
    UserHasProjectMapper INSTACE = Mappers.getMapper(UserHasProjectMapper.class);

    Set<UserHasProjectDto> UserHasProjectToUserHasProjectDto(Set<UserHasProject> userHasProjectSet);

    Set<UserHasProject> UserHasProjectDtoToUserHasProject(Set<UserHasProjectDto> userHasProjectDtoSet);
}
