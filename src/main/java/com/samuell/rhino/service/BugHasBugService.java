package com.samuell.rhino.service;

import com.samuell.rhino.model.Bug;
import com.samuell.rhino.model.dto.BugHasBugDto;
import org.springframework.lang.Nullable;

import java.util.List;

public interface BugHasBugService {
    @Nullable
    List<BugHasBugDto> getAllIncludedBugsByBugId(Integer projectId, Integer bugId);

    @Nullable
    Bug addIncludedBug(Integer projectId, Integer bugId, BugHasBugDto bugHasBugDto);

    @Nullable
    Bug deleteIncludedBug(Integer projectId, Integer bugId, BugHasBugDto bugHasBugDto);
}
