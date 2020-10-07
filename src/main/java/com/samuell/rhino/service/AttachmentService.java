package com.samuell.rhino.service;

import com.samuell.rhino.model.Attachment;
import org.springframework.lang.Nullable;

import java.util.List;

public interface AttachmentService {
    @Nullable
    List<Attachment> getAllAttachments();

    @Nullable
    Attachment getAttachmentById(int id);

    @Nullable
    Attachment addAttachment(Attachment attachment);
}
