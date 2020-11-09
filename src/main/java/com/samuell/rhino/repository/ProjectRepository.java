package com.samuell.rhino.repository;

import com.samuell.rhino.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ProjectRepository extends JpaRepository<Project,Integer> {
    @Transactional
    @Modifying
    @Query("delete from user_has_project where project_id = ?1")
    void deleteUsers(Integer id);
}
