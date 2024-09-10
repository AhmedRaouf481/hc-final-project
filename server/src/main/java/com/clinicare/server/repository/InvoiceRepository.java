package com.clinicare.server.repository;


import com.clinicare.server.domain.db.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    Optional<Invoice> findByAppointmentId(Long Id);
    Optional<Invoice> findByPatientId(Long Id);

}