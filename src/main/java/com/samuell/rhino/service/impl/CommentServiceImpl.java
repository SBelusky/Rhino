package com.samuell.rhino.service.impl;

import com.samuell.rhino.model.Comment;
import com.samuell.rhino.model.Log;
import com.samuell.rhino.model.dto.CommentDto;
import com.samuell.rhino.model.mapper.CommentMapper;
import com.samuell.rhino.repository.BugRepository;
import com.samuell.rhino.repository.CommentRepository;
import com.samuell.rhino.repository.UserRepository;
import com.samuell.rhino.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BugRepository bugRepository;
    @Autowired
    CommentMapper commentMapper;

    @Override
    public List<CommentDto> getAllCommentsByBugId(Integer bugId) {
        return commentRepository.findAll()
                .stream()
                .filter(comment ->
                        !comment.isWas_deleted() &&
                        comment.getBug().getId().equals(bugId))
                .map(comment -> commentMapper.toCommentDto(comment))
                .collect(Collectors.toList());
    }

    @Override
    public CommentDto getCommentById(Integer bugId, Integer commentId) {
        return commentMapper.toCommentDto(commentRepository.findById(commentId)
                .filter(bug -> bug.getBug().getId().equals(bugId))
                .orElse(null));
    }

    @Override
    public Comment addComment(Integer bugId, CommentDto commentDto) {
        Comment comment = commentMapper.toComment(commentDto);

        comment.setBug(bugRepository.findById(bugId).orElse(null));
        comment.setUser(userRepository.findById(commentDto.getUser().getId()).orElse(null));
        comment.setCreated_at(Timestamp.from(Instant.now()));

        return commentRepository.save(comment);
    }

    @Override
    public Comment updateComment(Integer bugId, Integer commentId, CommentDto commentDto) {
        Comment comment = commentRepository.findById(commentId).orElse(new Comment());

        comment.setDescription(commentDto.getDescription());
        comment.setSpend_time(commentDto.getSpend_time());
        comment.setBug(bugRepository.findById(bugId).orElse(null));
        comment.setEdited_at(Timestamp.from(Instant.now()));

        return commentRepository.save(comment);
    }

    @Override
    public Comment deleteComment(Integer id) {
        Comment comment = commentRepository.findById(id).orElse(new Comment());
        comment.setWas_deleted(true);

        return commentRepository.save(comment);
    }
}
