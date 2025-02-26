package com.mesakh.firststartspringboot.repository;

import com.mesakh.firststartspringboot.models.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group,Integer> {
}
