package com.samuell.rhino.service;

import com.samuell.rhino.model.Attachment;
import com.samuell.rhino.model.dto.AttachmentDto;
import org.springframework.lang.Nullable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface AttachmentService {
    @Nullable
    List<AttachmentDto> getAllAttachmentsByBugId(Integer projectId, Integer bugId);

    @Nullable
    AttachmentDto getAttachmentById(Integer projectId, Integer bugId, Integer attachmentId);

    @Nullable
    Attachment addAttachment(Integer projectId, Integer bugId, MultipartFile file, AttachmentDto attachmentDto) throws IOException;

    @Nullable
    Attachment deleteAttachment(Integer attachmentId);
}
