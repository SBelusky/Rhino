package com.samuell.rhino.service;

import com.samuell.rhino.model.Bug;
import org.springframework.lang.Nullable;

import java.util.List;

public interface BugService {
    @Nullable
    List<Bug> getAllBugs();

    @Nullable
    Bug getBugById(int id);

    @Nullable
    Bug addBug(Bug bug);
}
