package org.fotm.nullbeans.mvc.domain;


import lombok.AllArgsConstructor;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

// I don't use @Data with @Entity because I don't want the equals & hashCode
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Ticket {

    @Id
    @Generated
    @Column(name = "ticket_id")
    private Long id;

    private String title;
    private String description;
    private String status;
}
