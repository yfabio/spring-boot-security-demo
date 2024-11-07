package com.tech.pro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tech.pro.model.Role;

public interface RoleRepository extends JpaRepository<Role,Long> {
}
