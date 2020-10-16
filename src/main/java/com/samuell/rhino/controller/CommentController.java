package com.samuell.rhino.controller;

import com.samuell.rhino.model.Comment;
import com.samuell.rhino.model.Log;
import com.samuell.rhino.model.dto.CommentDto;
import com.samuell.rhino.model.status_enum.LogStatus;
import com.samuell.rhino.repository.BugRepository;
import com.samuell.rhino.repository.CommentRepository;
import com.samuell.rhino.repository.LogRepository;
import com.samuell.rhino.service.CommentService;
import com.samuell.rhino.service.LogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/bug")
public class CommentController {

    private final CommentService commentService;
    private final LogService logService;
    private final CommentRepository commentRepository;
    private final LogRepository logRepository;
    private final BugRepository bugRepository;

    public CommentController(CommentService commentService, LogService logService, CommentRepository commentRepository, LogRepository logRepository, BugRepository bugRepository) {
        this.commentService = commentService;
        this.logService = logService;
        this.commentRepository = commentRepository;
        this.logRepository = logRepository;
        this.bugRepository = bugRepository;
    }

    @GetMapping("/{bugId}/comment")
    public ResponseEntity<?> getAllCommentsByBugId(@PathVariable("bugId") Integer bugId) {
        List<CommentDto> commentDtoList = commentService.getAllCommentsByBugId(bugId);

        return new ResponseEntity<>(commentDtoList, HttpStatus.OK);
    }

    @GetMapping("/{bugId}/comment/{commentId}")
    public ResponseEntity<?> getCommentById(@PathVariable("bugId") Integer bugId, @PathVariable("commentId") Integer commentId) {
        CommentDto commentDto = commentService.getCommentById(bugId, commentId);

        if(commentDto == null){
            return new ResponseEntity<>("Comment not found",HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(commentDto, HttpStatus.OK);
        }
    }

    @PostMapping("/{bugId}/comment")
    public ResponseEntity<?> addComment(@PathVariable("bugId") Integer bugId, @RequestBody CommentDto commentDto) {
        Comment comment = commentService.addComment(bugId, commentDto);

        if(comment == null){
            return new ResponseEntity<>("Error while creating comment",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        else {
            String logMessage = "Comment with id: " + comment.getId();

            logService.addLog(bugId,comment.getUser().getId(),logMessage, LogStatus.COMMENT_CREATE);
            return ResponseEntity.status(HttpStatus.CREATED).body("Comment created with ID: "+ comment.getId());
        }
    }

    @PostMapping("/{bugId}/comment/{commentId}")
    public ResponseEntity<?> updateComment(@PathVariable("bugId") Integer bugId, @PathVariable("commentId") Integer commentId, @RequestBody CommentDto commentDto) {
        if(commentService.getCommentById(bugId, commentId) == null){
            return new ResponseEntity<>("Comment not found",HttpStatus.PRECONDITION_FAILED);
        }
        else {
            Comment comment = commentService.updateComment(bugId, commentId ,commentDto);
            String logMessage = "Comment with id: " + comment.getId();

            logService.addLog(bugId,comment.getUser().getId(),logMessage, LogStatus.COMMENT_EDIT);

            return ResponseEntity.status(HttpStatus.OK).body("Comment with ID: "+ comment.getId() + " was updated");
        }
    }

    @DeleteMapping("/{bugId}/comment/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable("bugId") Integer bugId, @PathVariable("commentId") Integer commentId) {
        if(commentService.getCommentById(bugId, commentId) == null){
            return new ResponseEntity<>("Comment not found",HttpStatus.NOT_FOUND);
        }
        else {
            Comment comment = commentService.deleteComment(commentId);
            String logMessage = "Comment with id: " + comment.getId();

            logService.addLog(bugId,comment.getUser().getId(),logMessage, LogStatus.COMMENT_DELETE);
            return ResponseEntity.status(HttpStatus.OK).body("Comment with ID: "+ comment.getId() + " was deleted");
        }
    }
}
