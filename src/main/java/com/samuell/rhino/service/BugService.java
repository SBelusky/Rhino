package com.samuell.rhino.service;

import com.samuell.rhino.model.*;
import com.samuell.rhino.model.dto.BugDto;
import com.samuell.rhino.model.dto.CommentDto;
import com.samuell.rhino.model.dto.UserDto;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface BugService {
    @Nullable
    List<BugDto> getAllBugsByProjectId(Integer projectId);

    @Nullable
    BugDto getBugById(Integer projectId, Integer bugId);

    @Nullable
    Bug addBug(Integer projectId, BugDto bugDto);

    @Nullable
    Bug updateBug(Integer projectId, Integer bugId, BugDto bugDto, Set<BugHasUser> oldBugHasUserSet, Set<BugHasVersion> oldBugHasVersionSet, Set<BugHasSpecification> oldBugHasSpecificationSet, Set<BugHasBug> oldBugHasBugSet);

    @Nullable
    Bug deleteBug(Integer projectId, Integer bugId);

    @Nullable
    Map<String,String> validateBug(BugDto bugDto);
}
