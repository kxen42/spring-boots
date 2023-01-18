package org.fotm.nullbeans.mvc.controller;

import lombok.RequiredArgsConstructor;
import org.fotm.nullbeans.mvc.domain.Ticket;
import org.fotm.nullbeans.mvc.model.TicketDto;
import org.fotm.nullbeans.mvc.repository.TicketRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/*
The @RestController isn't just a stereotype of @Component.
It also means that the response body is bound to the return value of any @RequestMapping
method.

For a REST service using a ResponseEntity allows you to do the status, headers, and body in one.
I haven't seen it used with webapps.
 */

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketRepository ticketRepository;

    @GetMapping("/{ticketId}")
    public ResponseEntity<TicketDto> getTicketById(@PathVariable("ticketId") Long ticketId) {
        Optional<Ticket> optionalTicket = ticketRepository.findById(ticketId);

        // not existing doesn't mean it's and error
        // use 200 with no content
        // or you just return a new'ed object - depends on where your mapping happens
        // save 204 for an empty list; so sayeth stackoverflow
        if (!optionalTicket.isPresent()) {
            return (ResponseEntity<TicketDto>) ResponseEntity.ok().contentLength(0);
        }

        Ticket ticket = optionalTicket.get();
        // TODO: use MapStruct or ModelMapper to do this
        TicketDto dto = new TicketDto(ticket.getId(), ticket.getTitle(), ticket.getDescription(), ticket.getStatus());
        return ResponseEntity.ok(dto);
    }

    @PostMapping()
    public ResponseEntity<TicketDto> addTicket(@RequestBody TicketDto ticketDto) {


    }

}
