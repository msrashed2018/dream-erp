package com.dream.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dream.models.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
