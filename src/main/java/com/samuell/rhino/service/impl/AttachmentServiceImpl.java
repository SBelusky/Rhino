package com.samuell.rhino.service.impl;

import com.samuell.rhino.model.Attachment;
import com.samuell.rhino.repository.AttachmentRepository;
import com.samuell.rhino.repository.UserRepository;
import com.samuell.rhino.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AttachmentServiceImpl implements AttachmentService {
    @Autowired
    AttachmentRepository attachmentRepository;

    @Override
    public List<Attachment> getAllAttachments() {
        return attachmentRepository.findAll();
    }

    @Override
    public Attachment getAttachmentById(int id) {
        return attachmentRepository.findById(id).orElse(null);
    }

    @Override
    public Attachment addAttachment(Attachment attachment) {
        return attachmentRepository.save(attachment);
    }
}
