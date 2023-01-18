package org.fotm.nullbeans.mvc.repository;

import org.fotm.nullbeans.mvc.domain.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
