package org.fotm.nullbeans.mvc.service;

import org.fotm.nullbeans.mvc.domain.UserAccount;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Validated
public interface TicketManagementService {

    void cancelTicket(@Min(1) long ticketId,
                      @NotNull @Size(min = 10, max = 200) String reasonForCancellation);


    // example validating nested objects
    // be very careful with this
    // this is my own example
    void addPaymentMethod(@Valid UserAccount userAccount);
}
