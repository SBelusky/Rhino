package com.samuell.rhino.service;

import com.samuell.rhino.model.Version;
import org.springframework.lang.Nullable;

import java.util.List;

public interface VersionService {
    @Nullable
    List<Version> getAllVersions();

    @Nullable
    Version getVersionById(int id);

    @Nullable
    Version addVersion(Version version);
}
