package com.mesakh.firststartspringboot.repository;

import com.mesakh.firststartspringboot.models.Group;
import com.mesakh.firststartspringboot.models.GroupUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupUserRepository extends JpaRepository<GroupUser,Integer> {
    List<GroupUser> findAllByGroup_Id(int id);

}
