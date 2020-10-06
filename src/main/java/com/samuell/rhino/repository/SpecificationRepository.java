package com.samuell.rhino.repository;

import com.samuell.rhino.model.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecificationRepository extends JpaRepository<Specification,Integer> {
}
