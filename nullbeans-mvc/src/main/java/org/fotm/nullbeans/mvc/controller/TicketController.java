package org.fotm.nullbeans.mvc.controller;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.fotm.nullbeans.mvc.domain.Ticket;
import org.fotm.nullbeans.mvc.model.TicketDto;
import org.fotm.nullbeans.mvc.repository.TicketRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Map;
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
        // however - common practice seems to use 404
        if (!optionalTicket.isPresent()) {
            return ResponseEntity.ok()
                                 .contentLength(0)
                                 .build();
        }

        Ticket ticket = optionalTicket.get();
        // TODO: use MapStruct or ModelMapper to do this
        TicketDto dto = new TicketDto(ticket.getId(), ticket.getTitle(), ticket.getDescription(), ticket.getStatus());
        return ResponseEntity.ok(dto);

    }

    // be default JSON @RequestBody will be deserialized
    @SneakyThrows
    @PostMapping()
    public ResponseEntity<TicketDto> addTicket(@RequestBody TicketDto ticketDto) {
        Ticket domainTicket = new Ticket();
        domainTicket.setDescription(ticketDto.getDescription());
        domainTicket.setTitle(ticketDto.getTitle());
        domainTicket.setStatus("NEW");

        Ticket saved = ticketRepository.save(domainTicket);

        // SpringFramework Guru recommends using the returned value of save
        ticketDto.setId(saved.getId());
        ticketDto.setStatus(saved.getStatus());

        // this sends 201 and sets Location header
        return ResponseEntity.created(new URI(String.valueOf(domainTicket.getId())))
                             .body(ticketDto);

    }

    @PutMapping()
    public ResponseEntity<Void> updateTicket(@RequestBody TicketDto ticketDto) {
        // the nullbeans people use the ID in a path parameter, but the RFC
        // and stackoverflow recommends using it in the DTO
        Optional<Ticket> optionalTicket = ticketRepository.findById(ticketDto.getId());

        // this seems like a legit time for 404
        if (!optionalTicket.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Ticket currentTicket = optionalTicket.get();
        currentTicket.setDescription(ticketDto.getDescription());
        currentTicket.setStatus(ticketDto.getStatus());
        currentTicket.setTitle(ticketDto.getTitle());

        ticketRepository.save(currentTicket);

        // could return 204 NO CONTENT, see Evernote
        return ResponseEntity.ok()
                             .build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable("id") Long id) {
        // This works whether it exists or not - idempotent baby!
        Optional<Ticket> optionalTicket = ticketRepository.findById(id);

        if (optionalTicket.isPresent())
            ticketRepository.delete(optionalTicket.get());

        // use 204 NO CONTENT if it is done right away, see Evernote
        return ResponseEntity.noContent()
                             .build();

    }

    /*
    Unlike PUT and POST, PATCH only expects a subset of the document being modified, and not the whole document.
    If the REST model is used in a PATCH method, and some fields of the model are set to null, it will not be possible
    to differentiate between fields that were missing from the PATCH request and fields that we actually want
    to set to null.
    Since a patch indicates a collection of instructions that are used to modify a document, a Map object seems more
    appropriate here.

    Note that parts of our implementation can be replaced by something such as JsonPatch or any other framework which
    can handle PATCH requests. However, our intention was to provide a starting point for those who would like to take
    advantage of the HTTP PATCH verb.
     */

    @PatchMapping("/{id}")
    public ResponseEntity<Void> partialUpdate(@PathVariable("id") Long id, @RequestBody Map<String, Object> changes) {
        Optional<Ticket> optionalTicket = ticketRepository.findById(id);

        if (!optionalTicket.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Ticket ticket = optionalTicket.get();

        TicketDto dto = new TicketDto();
        dto.setId(ticket.getId());
        dto.setDescription(ticket.getDescription());
        dto.setStatus(ticket.getStatus());
        dto.setTitle(ticket.getTitle());

        changes.forEach(
            (change, value) -> {
                switch (change) {
                    case "description": dto.setDescription((String) value);
                    break;
                    case "status": dto.setStatus((String) value);
                    break;
                    case "title": dto.setTitle((String) value);
                    break;
                }
            }
        );

        // TODO: use validation before save

        // we need ModelMapper or MapStruct
        Ticket updatedTicket = new Ticket();
        updatedTicket.setId(dto.getId());
        updatedTicket.setDescription(dto.getDescription());
        updatedTicket.setStatus(dto.getStatus());
        updatedTicket.setTitle(dto.getTitle());
        ticketRepository.save(updatedTicket);

        return ResponseEntity.ok().build();

    }

}