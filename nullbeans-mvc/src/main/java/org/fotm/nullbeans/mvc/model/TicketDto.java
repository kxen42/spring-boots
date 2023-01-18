package org.fotm.nullbeans.mvc.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketDto {
    /*
    REST model for Ticket entity
    Why have this and the Ticket @Entity?
    - decouple frontend from backend
    - don't want to use @Entity because you need to know if it is transient, detached, etc.
    */



    private Long id;
    private String title;
    private String description;
    private String status;
}
