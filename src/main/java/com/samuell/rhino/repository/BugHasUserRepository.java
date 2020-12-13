package com.samuell.rhino.repository;

import com.samuell.rhino.model.BugHasUser;
import com.samuell.rhino.model.embedded_key.BugHasUserKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BugHasUserRepository extends JpaRepository<BugHasUser, BugHasUserKey> {
}
