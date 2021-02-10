package com.samuell.rhino.controller;

import com.samuell.rhino.model.Comment;
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
import java.util.Map;

@RestController
@RequestMapping("api/project/{projectId}/bug/{bugId}")
public class CommentController {

    private final CommentService commentService;
    private final LogService logService;

    public CommentController(CommentService commentService, LogService logService) {
        this.commentService = commentService;
        this.logService = logService;
    }

    @GetMapping("/comment")
    @CrossOrigin(origins = "http://localhost:8081")
    public ResponseEntity<?> getAllCommentsByBugId(@PathVariable("bugId") Integer bugId) {
        List<CommentDto> commentDtoList = commentService.getAllCommentsByBugId(bugId);

        return new ResponseEntity<>(commentDtoList, HttpStatus.OK);
    }

    @GetMapping("/detail/comment/{commentId}")
    @CrossOrigin(origins = "http://localhost:8081")
    public ResponseEntity<?> getCommentById(@PathVariable("bugId") Integer bugId, @PathVariable("commentId") Integer commentId) {
        CommentDto commentDto = commentService.getCommentById(bugId, commentId);

        if(commentDto == null){
            return new ResponseEntity<>("Comment not found",HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(commentDto, HttpStatus.OK);
        }
    }

    @PostMapping("/add/comment")
    @CrossOrigin(origins = "http://localhost:8081")
    public ResponseEntity<?> addComment(@PathVariable("bugId") Integer bugId, @RequestBody CommentDto commentDto) {
        Map<String,String> errors = commentService.validateComment(commentDto);

        if(errors.size() != 0){
            return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
        }
        else {
            Comment comment = commentService.addComment(bugId, commentDto);
            String logMessage = "Komentár s ID: " + comment.getId();

            logService.addLog(bugId,comment.getUser().getId(),logMessage, LogStatus.COMMENT_CREATE);

            return ResponseEntity.status(HttpStatus.CREATED).body("Comment created with ID: "+ comment.getId());
        }
    }

    @PostMapping("/edit/comment/{commentId}")
    @CrossOrigin(origins = "http://localhost:8081")
    public ResponseEntity<?> updateComment(@PathVariable("bugId") Integer bugId, @PathVariable("commentId") Integer commentId, @RequestBody CommentDto commentDto) {

        if(commentService.getCommentById(bugId, commentId) == null){
            return new ResponseEntity<>("Comment not found",HttpStatus.PRECONDITION_FAILED);
        }
        else {
            Map<String,String> errors = commentService.validateComment(commentDto);

            if(errors.size() != 0){
                return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
            }
            else {
                Comment comment = commentService.updateComment(bugId, commentId ,commentDto);
                String logMessage = "Komentár s ID: " + comment.getId();

                logService.addLog(bugId,1,logMessage, LogStatus.COMMENT_EDIT);

                return ResponseEntity.status(HttpStatus.OK).body("Comment with ID: "+ comment.getId() + " was updated");
            }
        }
    }

    @DeleteMapping("/delete/comment/{commentId}")
    @CrossOrigin(origins = "http://localhost:8081")
    public ResponseEntity<?> deleteComment(@PathVariable("bugId") Integer bugId, @PathVariable("commentId") Integer commentId) {
        if(commentService.getCommentById(bugId, commentId) == null){
            return new ResponseEntity<>("Comment not found",HttpStatus.NOT_FOUND);
        }
        else {
            Comment comment = commentService.deleteComment(commentId);
            String logMessage = "Komentár s ID: " + comment.getId();

//            logService.addLog(bugId,commentDto.getIdOfLastEditingUser(),logMessage, LogStatus.COMMENT_DELETE);
            logService.addLog(bugId,1,logMessage, LogStatus.COMMENT_DELETE);
            return ResponseEntity.status(HttpStatus.OK).body("Comment with ID: "+ comment.getId() + " was deleted");
        }
    }
}
