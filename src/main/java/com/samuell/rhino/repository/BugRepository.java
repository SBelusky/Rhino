package com.samuell.rhino.repository;

import com.samuell.rhino.model.Bug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface BugRepository extends JpaRepository<Bug,Integer> {
    @Transactional
    @Modifying
    @Query("delete from bug_has_specification where bug_id = ?1")
    void deleteSpecifications(Integer bugId);

    @Transactional
    @Modifying
    @Query(value = "delete from bug_has_version where bug_id = ?1", nativeQuery = true)
    void deleteVersions(Integer bugId);

    @Transactional
    @Modifying
    @Query(value = "delete from bug_has_bug where contains = ?1", nativeQuery = true)
    void deleteBugHasBug(Integer bugId);
}
