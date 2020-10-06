package com.samuell.rhino.repository;

import com.samuell.rhino.model.UserHasProject;
import com.samuell.rhino.model.embedded_key.UserHasProjectKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserHasProjectRepository extends JpaRepository<UserHasProject, UserHasProjectKey> {
}
