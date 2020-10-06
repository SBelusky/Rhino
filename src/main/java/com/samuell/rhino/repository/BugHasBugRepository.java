package com.samuell.rhino.repository;

import com.samuell.rhino.model.BugHasBug;
import com.samuell.rhino.model.embedded_key.BugHasBugKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BugHasBugRepository extends JpaRepository<BugHasBug, BugHasBugKey> {
}
