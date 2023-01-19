package org.fotm.nullbeans.mvc.service;

import org.fotm.nullbeans.mvc.domain.UserAccount;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Service
public class TicketManagementServiceImpl implements TicketManagementService {
    @Override
    public void cancelTicket(@Min(1) long ticketId, @NotNull @Size(min = 10, max = 200) String reasonForCancellation) {

    }

    @Override
    public void addPaymentMethod(UserAccount userAccount) {

    }
}
