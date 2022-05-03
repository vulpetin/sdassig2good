package com.sd.assig23.repository;

import com.sd.assig23.dto.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
