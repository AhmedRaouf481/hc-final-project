package com.clinicare.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clinicare.server.domain.db.Role;


public interface RoleRepository extends JpaRepository<Role,Integer> {

}
