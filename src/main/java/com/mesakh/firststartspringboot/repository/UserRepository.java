package com.mesakh.firststartspringboot.repository;

import com.mesakh.firststartspringboot.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findAllByStatus(String status);
    List<User> findAllByStatusOrderByIdDesc(String status);
    List<User> findAllByStatusInOrderByIdDesc(List<String> statusList);
//    List<User> findAllByStatusInOrderByIdAsc(List<String> statusList);
}
