package com.samuell.rhino.service.impl;

import com.samuell.rhino.model.Log;
import com.samuell.rhino.model.Version;
import com.samuell.rhino.model.dto.LogDto;
import com.samuell.rhino.model.mapper.LogMapper;
import com.samuell.rhino.model.mapper.VersionMapper;
import com.samuell.rhino.model.status_enum.LogStatus;
import com.samuell.rhino.repository.*;
import com.samuell.rhino.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LogServiceImpl implements LogService {
    @Autowired
    LogRepository logRepository;
    @Autowired
    BugRepository bugRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    LogMapper logMapper;

    @Override
    public List<LogDto> getAllLogsByBugId(Integer projectId, Integer bugId) {
        return logRepository.findAll()
                .stream()
                .filter(log ->
                        log.getBug().getProject().getId().equals(projectId) &&
                        log.getBug().getId().equals(bugId))
                .map(log -> logMapper.toLogDto(log))
                .collect(Collectors.toList());
    }

    @Override
    public Log addLog(Integer bugId, Integer userId, String message, LogStatus status) {
        Log log = new Log();

        log.setMessage(message);
        log.setStatus(status.label);
        log.setUser(userRepository.findById(userId).orElse(null));
        log.setBug(bugRepository.findById(bugId).orElse(null));
        log.setCreated_at(Timestamp.from(Instant.now()));

        return logRepository.save(log);
    }
}
