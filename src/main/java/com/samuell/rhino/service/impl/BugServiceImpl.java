package com.samuell.rhino.service.impl;

import com.samuell.rhino.model.Bug;
import com.samuell.rhino.repository.BugRepository;
import com.samuell.rhino.service.BugService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BugServiceImpl implements BugService {
    @Autowired
    BugRepository bugRepository;

    @Override
    public List<Bug> getAllBugs() {
        return bugRepository.findAll();
    }

    @Override
    public Bug getBugById(int id) {
        return bugRepository.findById(id).orElse(null);
    }

    @Override
    public Bug addBug(Bug bug) {
        return bugRepository.save(bug);
    }
}
