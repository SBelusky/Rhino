package com.samuell.rhino.service;

import com.samuell.rhino.model.Bug;
import com.samuell.rhino.model.BugHasVersion;
import com.samuell.rhino.model.Comment;
import com.samuell.rhino.model.dto.BugDto;
import com.samuell.rhino.model.dto.CommentDto;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.Set;

public interface BugService {
    @Nullable
    List<BugDto> getAllBugsByProjectId(Integer projectId);

    @Nullable
    BugDto getBugById(Integer projectId, Integer bugId);

    @Nullable
    Bug addBug(Integer projectId, BugDto bugDto);

    @Nullable
    Bug updateBug(Integer projectId, Integer bugId, BugDto bugDto/*, Set<BugHasVersion> newbugHasVersionSet*/);

    @Nullable
    Bug deleteBug(Integer projectId, Integer bugId);
}
