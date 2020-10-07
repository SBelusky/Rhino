package com.samuell.rhino.service;

import com.samuell.rhino.model.Log;
import org.springframework.lang.Nullable;

import java.util.List;

public interface LogService {
    @Nullable
    List<Log> getAllLogs();

    @Nullable
    Log getLogById(int id);

    @Nullable
    Log addLog(Log log);
}
