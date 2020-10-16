package com.samuell.rhino.service.impl;

import com.samuell.rhino.model.Attachment;
import com.samuell.rhino.model.dto.AttachmentDto;
import com.samuell.rhino.model.mapper.AttachmentMapper;
import com.samuell.rhino.repository.AttachmentRepository;
import com.samuell.rhino.repository.BugRepository;
import com.samuell.rhino.repository.UserRepository;
import com.samuell.rhino.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AttachmentServiceImpl implements AttachmentService {
    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    BugRepository bugRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    AttachmentMapper attachmentMapper;

    @Override
    public List<AttachmentDto> getAllAttachmentsByBugId(Integer projectId, Integer bugId) {
        return attachmentRepository.findAll()
                .stream()
                .filter(attachment ->
                        !attachment.isWas_deleted() &&
                        attachment.getBug().getId().equals(bugId) &&
                        attachment.getBug().getProject().getId().equals(projectId))
                .map(attachment -> attachmentMapper.toAttachmentDto(attachment))
                .collect(Collectors.toList());
    }

    @Override
    public AttachmentDto getAttachmentById(Integer projectId, Integer bugId, Integer attachmentId) {
        return attachmentMapper.toAttachmentDto(attachmentRepository.findById(attachmentId)
                .filter(attachment ->
                        !attachment.isWas_deleted() &&
                        attachment.getBug().getId().equals(bugId) &&
                        attachment.getBug().getProject().getId().equals(projectId))
                .orElse(null));
    }

    @Override
    public Attachment addAttachment(Integer projectId, Integer bugId, MultipartFile file, AttachmentDto attachmentDto) throws IOException {
        Attachment attachment = attachmentMapper.toAttachment(attachmentDto);

        if(bugRepository.findById(bugId).filter(
                bug -> bug.getProject().getId().equals(projectId))
                .orElse(null) == null){
            return null;
        }
        else {
            attachment.setFile(file.getBytes());
            attachment.setType(file.getContentType());
            attachment.setSize((file.getSize()/1000) + " kB");

            attachment.setBug(bugRepository.findById(bugId).orElse(null));
            attachment.setUser(userRepository.findById(attachmentDto.getUser().getId()).orElse(null));
            attachment.setCreated_at(Timestamp.from(Instant.now()));

            return attachmentRepository.save(attachment);
        }
    }

    @Override
    public Attachment deleteAttachment(Integer attachmentId) {
        Attachment attachment = attachmentRepository.findById(attachmentId).orElse(null);

        attachment.setWas_deleted(true);

        return attachmentRepository.save(attachment);
    }
}
