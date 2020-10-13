package com.samuell.rhino.service;

import com.samuell.rhino.model.Comment;
import com.samuell.rhino.model.Project;
import com.samuell.rhino.model.dto.CommentDto;
import com.samuell.rhino.model.dto.ProjectDto;
import org.springframework.lang.Nullable;

import java.util.List;

public interface CommentService {
    @Nullable
    List<CommentDto> getAllCommentsByBugId(Integer bugId);

    @Nullable
    CommentDto getCommentById(Integer bugId, Integer commentId);

    @Nullable
    Comment addComment(Integer bugId, CommentDto commentDto);

    @Nullable
    Comment updateComment(Integer bugId, Integer commentId, CommentDto commentDto);

    @Nullable
    Comment deleteComment(Integer id);
}
