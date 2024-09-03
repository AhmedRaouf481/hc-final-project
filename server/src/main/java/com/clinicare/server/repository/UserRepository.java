package com.clinicare.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clinicare.server.domain.db.User;

public interface UserRepository extends JpaRepository<User,Long> {
}
