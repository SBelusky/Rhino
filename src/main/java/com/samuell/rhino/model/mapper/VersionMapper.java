package com.samuell.rhino.model.mapper;

import com.samuell.rhino.model.Project;
import com.samuell.rhino.model.Version;
import com.samuell.rhino.model.dto.ProjectDto;
import com.samuell.rhino.model.dto.VersionDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VersionMapper {
    VersionMapper INSTANCE = Mappers.getMapper(VersionMapper.class);

    VersionDto toVersionDto(Version version);

    Version toVersion(VersionDto versionDto);

    @Mappings({
            @Mapping(target = "name", ignore=true),
            @Mapping(target = "description", ignore=true),
            @Mapping(target = "user_has_projects", ignore=true),
            @Mapping(target = "created_at", ignore=true),
    })
    ProjectDto toProjectDto(Project project);

    Project toProject(ProjectDto projectDto);
}
