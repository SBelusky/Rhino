package com.samuell.rhino.model.mapper;

import com.samuell.rhino.model.Category;
import com.samuell.rhino.model.Project;
import com.samuell.rhino.model.dto.CategoryDto;
import com.samuell.rhino.model.dto.ProjectDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryDto toCategoryDto(Category category);

    Category toCategory(CategoryDto categoryDto);

    @Mappings({
            @Mapping(target = "name", ignore=true),
            @Mapping(target = "description", ignore=true),
            @Mapping(target = "user_has_projects", ignore=true),
            @Mapping(target = "created_at", ignore=true),
    })
    ProjectDto toProjectDto(Project project);

    Project toProject(ProjectDto projectDto);
}
