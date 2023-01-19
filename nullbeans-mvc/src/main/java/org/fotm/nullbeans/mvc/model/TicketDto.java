package org.fotm.nullbeans.mvc.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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


    // Using validation group
    // this should have Min =1, but I won't just for demonstration sake
    private Long id;

    @NotNull
    @Size(min = 10)
    private String title;

    @NotNull
    @Size(min = 20)
    private String description;

    // Using validation group
    @NotNull(groups = {OnUpdate.class})
    private String status;

    public interface OnUpdate {

    }

    public interface ExistingTicket {

    }
}
