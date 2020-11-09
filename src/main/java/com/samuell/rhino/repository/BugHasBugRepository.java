package com.samuell.rhino.repository;

import com.samuell.rhino.model.BugHasBug;
import com.samuell.rhino.model.embedded_key.BugHasBugKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface BugHasBugRepository extends JpaRepository<BugHasBug, BugHasBugKey> {
}
