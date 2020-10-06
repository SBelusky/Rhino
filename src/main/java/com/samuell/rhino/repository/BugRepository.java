package com.samuell.rhino.repository;

import com.samuell.rhino.model.Bug;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BugRepository extends JpaRepository<Bug,Integer> {
}
