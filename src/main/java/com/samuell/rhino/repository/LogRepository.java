package com.samuell.rhino.repository;

import com.samuell.rhino.model.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<Log,Integer> {
}
