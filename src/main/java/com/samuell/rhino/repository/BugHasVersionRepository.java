package com.samuell.rhino.repository;

import com.samuell.rhino.model.BugHasVersion;
import com.samuell.rhino.model.embedded_key.BugHasVersionKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BugHasVersionRepository extends JpaRepository<BugHasVersion, BugHasVersionKey> {
}
