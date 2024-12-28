package com.mesakh.firststartspringboot.repository;

import com.mesakh.firststartspringboot.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findAllByStatus(String status);
}
