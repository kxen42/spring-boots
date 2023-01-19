package org.fotm.nullbeans.mvc.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserAccount {

    @Min(1)
    @Id
    @GeneratedValue
    private Long id;

    // example validating nested objects
    // be very careful with this
    // this is my own example
    @Valid
    @OneToMany
    private List<PaymentMethod> paymentMethods = new ArrayList<>();

}
