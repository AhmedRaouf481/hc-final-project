package com.clinicare.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clinicare.server.domain.db.Slot;

public interface SlotRepository extends JpaRepository<Slot,Long>{

}
