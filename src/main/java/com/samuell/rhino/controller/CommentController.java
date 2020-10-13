package com.samuell.rhino.controller;

import com.samuell.rhino.model.Comment;
import com.samuell.rhino.model.dto.CommentDto;
import com.samuell.rhino.repository.CommentRepository;
import com.samuell.rhino.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/bug")
public class CommentController {

    private final CommentService commentService;
    private final CommentRepository commentRepository;

    public CommentController(CommentService commentService, CommentRepository commentRepository) {
        this.commentService = commentService;
        this.commentRepository = commentRepository;
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

            return ResponseEntity.status(HttpStatus.OK).body("Comment with ID: "+ comment.getId() + " was deleted");
        }
    }
}
