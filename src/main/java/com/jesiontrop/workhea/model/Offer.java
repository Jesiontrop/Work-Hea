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
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "offer_sequence")
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

    public Offer() {
    }

    public Offer(@NotBlank(message = "Vacancy title is required") String vacancyTitle, @NotNull(message = "Salary is required") Integer salary) {
        this.vacancyTitle = vacancyTitle;
        this.salary = salary;
    }
}
