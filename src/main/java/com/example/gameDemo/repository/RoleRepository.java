package com.example.gameDemo.repository;


import com.example.gameDemo.model.ERole;
import com.example.gameDemo.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;




@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(ERole roleUser);
}
