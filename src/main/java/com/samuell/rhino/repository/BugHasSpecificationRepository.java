package com.samuell.rhino.repository;

import com.samuell.rhino.model.BugHasSpecification;
import com.samuell.rhino.model.embedded_key.BugHasSpecificationKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BugHasSpecificationRepository extends JpaRepository<BugHasSpecification, BugHasSpecificationKey> {
}
