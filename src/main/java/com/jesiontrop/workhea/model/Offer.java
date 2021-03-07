package com.jesiontrop.workhea.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;


@Entity
@RestResource(rel = "offers", path = "offers")
@Data
@NoArgsConstructor
@AllArgsConstructor
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
    @RestResource(rel = "organization", path = "organization")
    private Organization organization;

    public Offer(@NotBlank(message = "Vacancy title is required") String vacancyTitle,
                 @NotNull(message = "Salary is required") Integer salary) {
        this.vacancyTitle = vacancyTitle;
        this.salary = salary;
    }

    public Offer(@NotBlank(message = "Vacancy title is required") String vacancyTitle,
                 @NotNull(message = "Salary is required") Integer salary,
                 Organization organization) {
        this.vacancyTitle = vacancyTitle;
        this.salary = salary;
        this.organization = organization;
    }
}
