package com.samuell.rhino.service.impl;

import com.samuell.rhino.model.Log;
import com.samuell.rhino.model.User;
import com.samuell.rhino.repository.LogRepository;
import com.samuell.rhino.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


public class LogServiceImpl implements LogService {
    @Autowired
    LogRepository logRepository;

//    public List<Log> getAllLogs(){
//        return logRepository.findAll();
//    }
}
