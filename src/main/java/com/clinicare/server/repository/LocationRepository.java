package com.clinicare.server.repository;

import com.clinicare.server.domain.db.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LocationRepository extends JpaRepository<Location, Long>{
}
