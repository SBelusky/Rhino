package com.samuell.rhino.controller;

import com.samuell.rhino.model.Attachment;
import com.samuell.rhino.model.dto.AttachmentDto;
import com.samuell.rhino.model.status_enum.LogStatus;
import com.samuell.rhino.service.AttachmentService;
import com.samuell.rhino.service.LogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/project/")
public class AttachmentController{
    private final AttachmentService attachmentService;
    private final LogService logService;

    public AttachmentController(AttachmentService attachmentService, LogService logService) {
        this.attachmentService = attachmentService;
        this.logService = logService;
    }

    @GetMapping("/{projectId}/bug/{bugId}/attachment")
    public ResponseEntity<?> getAllAttachmentsByBugId(@PathVariable("projectId") Integer projectId, @PathVariable("bugId") Integer bugId) {
        List<AttachmentDto> attachmentDtoList = attachmentService.getAllAttachmentsByBugId(projectId, bugId);

        return new ResponseEntity<>(attachmentDtoList, HttpStatus.OK);
    }

    @GetMapping("/{projectId}/bug/{bugId}/attachment/{attachmentId}")
    public ResponseEntity<?> getAttachmentById(@PathVariable("projectId") Integer projectId, @PathVariable("bugId") Integer bugId, @PathVariable("attachmentId") Integer attachmentId) {
        AttachmentDto attachmentDto = attachmentService.getAttachmentById(projectId, bugId, attachmentId);

        if(attachmentDto == null){
            return new ResponseEntity<>("Attachment not found",HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(attachmentDto, HttpStatus.OK);
        }
    }

    @RequestMapping(path = "/{projectId}/bug/{bugId}/upload/attachment", method = RequestMethod.POST, consumes = {"multipart/form-data"})
    public ResponseEntity<?> addAttachment(@PathVariable("projectId") Integer projectId, @PathVariable("bugId") Integer bugId, @RequestParam("file_") MultipartFile file, @ModelAttribute AttachmentDto attachmentDto) throws IOException {

        Attachment attachment = attachmentService.addAttachment(projectId, bugId, file, attachmentDto);

        if(attachment == null){
            return new ResponseEntity<>("Error while creating attachment",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        else {
            String logMessage = "Attachment with id: " + attachment.getId();

            logService.addLog(bugId,attachment.getUser().getId(),logMessage, LogStatus.ATTACHMENT_UPLOAD);
            return ResponseEntity.status(HttpStatus.CREATED).body("Attachment uploaded with ID: "+ attachment.getId());
        }
    }

    @DeleteMapping("/{projectId}/bug/{bugId}/attachment/{attachmentId}")
    public ResponseEntity<?> deleteAttachment(@PathVariable("projectId") Integer projectId, @PathVariable("bugId") Integer bugId, @PathVariable("attachmentId") Integer attachmentId, @RequestBody AttachmentDto attachmentDto) {
        if(attachmentService.getAttachmentById(projectId, bugId, attachmentId) == null){
            return new ResponseEntity<>("Attachment not found",HttpStatus.NOT_FOUND);
        }
        else {
            Attachment attachment = attachmentService.deleteAttachment(attachmentId);
            String logMessage = "Attachment with id: " + attachment.getId();

            logService.addLog(bugId,attachmentDto.getIdOfLastEditingUser(),logMessage, LogStatus.ATTACHMENT_DELETE);

            return ResponseEntity.status(HttpStatus.OK).body("Attachment with ID: "+ attachment.getId() + " was deleted");
        }
    }
}
