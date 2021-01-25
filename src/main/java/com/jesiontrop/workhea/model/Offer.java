package com.jesiontrop.workhea.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Entity
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Vacancy title is required")
    private String vacancyTitle;
    @NotNull(message = "Salary is required")
    private Integer salary;

    private Date placedAt;

    @PrePersist
    void placedAt() {
        this.placedAt = new Date();
    }

    @ManyToOne(targetEntity = Organization.class)
    private Organization organization;
}
