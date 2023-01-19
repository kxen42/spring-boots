package org.fotm.nullbeans.mvc;

import org.fotm.nullbeans.mvc.domain.PaymentMethod;
import org.fotm.nullbeans.mvc.domain.UserAccount;
import org.fotm.nullbeans.mvc.service.TicketManagementService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class TicketManagementServiceTest {

    @Autowired
    private TicketManagementService service;

    @Test
    void cancelTicket_ticketIdIsZero() {
        Exception thrown = assertThrows(ConstraintViolationException.class, () -> {
            service.cancelTicket(0, "testing validation"
            );
        });

        assertEquals("cancelTicket.ticketId: must be greater than or equal to 1, cancelTicket.arg0: must be greater than or equal to 1", thrown.getMessage());
    }

    @Test
    void cancelTicket_reasonTooSmall() {
        Exception thrown = assertThrows(ConstraintViolationException.class, () -> {
            service.cancelTicket(1, "x".repeat(9)
            );
        });

        assertEquals("cancelTicket.arg1: size must be between 10 and 200, cancelTicket.reasonForCancellation: size must be between 10 and 200", thrown.getMessage());
    }

    @Test
    void cancelTicket_reasonTooBig() {
        Exception thrown = assertThrows(ConstraintViolationException.class, () -> {
            service.cancelTicket(1, "x".repeat(201)
            );
        });

        assertEquals("cancelTicket.reasonForCancellation: size must be between 10 and 200, cancelTicket.arg1: size must be between 10 and 200", thrown.getMessage());
    }

    @Test
    void addPaymentMethod_paymentMethodIdIsZero() {
        PaymentMethod paymentMethod = new PaymentMethod(0L);
        UserAccount userAccount = new UserAccount();
        userAccount.getPaymentMethods()
                   .add(paymentMethod);

        Exception thrown = assertThrows(ConstraintViolationException.class, () -> {
            service.addPaymentMethod(userAccount);
        });

        assertEquals("addPaymentMethod.arg0.paymentMethods[0].id: must be greater than or equal to 1", thrown.getMessage());

    }
}