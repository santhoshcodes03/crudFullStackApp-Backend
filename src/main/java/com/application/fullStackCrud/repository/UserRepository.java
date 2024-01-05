package com.application.fullStackCrud.repository;

import com.application.fullStackCrud.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
