package com.mesakh.firststartspringboot.repository;

import com.mesakh.firststartspringboot.models.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionRepository extends JpaRepository<Position,Integer> {
}
