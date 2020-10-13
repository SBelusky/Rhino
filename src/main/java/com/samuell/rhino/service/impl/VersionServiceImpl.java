package com.samuell.rhino.service.impl;

import com.samuell.rhino.model.Version;
import com.samuell.rhino.model.dto.VersionDto;
import com.samuell.rhino.model.mapper.VersionMapper;
import com.samuell.rhino.repository.*;
import com.samuell.rhino.service.VersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VersionServiceImpl implements VersionService {
    @Autowired
    VersionRepository versionRepository;
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    VersionMapper versionMapper;

    @Override
    public List<VersionDto> getAllVersionsByProjectId(Integer projectId) {
        return versionRepository.findAll()
                .stream()
                .filter(version ->
                        !version.isWas_deleted() &&
                        version.getProject().getId().equals(projectId))
                .map(version -> versionMapper.toVersionDto(version))
                .collect(Collectors.toList());
    }

    @Override
    public VersionDto getVersionById(Integer projectId, Integer versionId) {
        return versionMapper.toVersionDto(versionRepository.findById(versionId)
                .filter(project -> project.getProject().getId().equals(projectId))
                .orElse(null));
    }

    @Override
    public Version addVersion(Integer projectId, VersionDto versionDto) {
        Version version = versionMapper.toVersion(versionDto);

        version.setProject(projectRepository.findById(projectId).orElse(null));
        version.setCreated_at(Timestamp.from(Instant.now()));

        return versionRepository.save(version);
    }

    @Override
    public Version updateVersion(Integer projectId, Integer versionId, VersionDto versionDto) {
        Version version = versionRepository.findById(versionId).orElse(new Version());

        version.setName(versionDto.getName());
        version.setDescription(versionDto.getDescription());
        version.setProject(projectRepository.findById(projectId).orElse(null));
        version.setEdited_at(Timestamp.from(Instant.now()));

        return versionRepository.save(version);
    }

    @Override
    public Version deleteVersion(Integer projectId) {
        Version version = versionRepository.findById(projectId).orElse(new Version());
        version.setWas_deleted(true);

        return versionRepository.save(version);
    }
}
