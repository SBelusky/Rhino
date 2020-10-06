package com.samuell.rhino.repository;

import com.samuell.rhino.model.Version;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VersionRepository extends JpaRepository<Version,Integer> {
}
