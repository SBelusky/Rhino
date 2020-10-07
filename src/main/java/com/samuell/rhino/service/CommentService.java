package com.samuell.rhino.service;

import com.samuell.rhino.model.Comment;
import org.springframework.lang.Nullable;

import java.util.List;

public interface CommentService {
    @Nullable
    List<Comment> getAllComments();

    @Nullable
    Comment getCommentById(int id);

    @Nullable
    Comment addComment(Comment comment);
}
