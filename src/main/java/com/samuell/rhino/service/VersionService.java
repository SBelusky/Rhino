package com.samuell.rhino.service;

import com.samuell.rhino.model.Comment;
import com.samuell.rhino.model.Version;
import com.samuell.rhino.model.dto.CommentDto;
import com.samuell.rhino.model.dto.VersionDto;
import org.springframework.lang.Nullable;

import java.util.List;

public interface VersionService {
    @Nullable
    List<VersionDto> getAllVersionsByProjectId(Integer projectId);

    @Nullable
    VersionDto getVersionById(Integer projectId, Integer versionId);

    @Nullable
    Version addVersion(Integer projectId, VersionDto versionDto);

    @Nullable
    Version updateVersion(Integer projectId, Integer versionId, VersionDto versionDto);

    @Nullable
    Version deleteVersion(Integer versionId);
}
