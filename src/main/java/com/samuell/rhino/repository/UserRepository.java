package com.samuell.rhino.repository;

import com.samuell.rhino.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface UserRepository extends JpaRepository<User, Integer> {
}
