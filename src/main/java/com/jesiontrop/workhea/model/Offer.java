package com.jesiontrop.workhea.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@Entity
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Vacancy title is required")
    private String vacancyTitle;
    @NotBlank(message = "Salary is required")
    private int salary;

    private Date placedAt;

    @PrePersist
    void placedAt() {
        this.placedAt = new Date();
    }
}
