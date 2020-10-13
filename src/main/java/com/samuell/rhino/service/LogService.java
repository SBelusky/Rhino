package com.samuell.rhino.service;

import com.samuell.rhino.model.Log;
import com.samuell.rhino.model.dto.LogDto;
import com.samuell.rhino.model.status_enum.LogStatus;
import org.springframework.lang.Nullable;

import java.util.List;

public interface LogService {
    @Nullable
    List<LogDto> getAllLogsByBugId(Integer projectId, Integer bugId);

    @Nullable
    Log addLog(Integer bugId, Integer userId, String message, LogStatus status);
}
