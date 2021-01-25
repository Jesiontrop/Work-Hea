package com.jesiontrop.workhea.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Name of organization is required")
    private String nameOfOrganization;
    @NotBlank(message = "Organization Number is required")
    private String organizationNumber;

    @OneToMany(targetEntity = Offer.class, cascade = CascadeType.ALL)
    private List<Offer> offers = new ArrayList<>();

    public void addOffer(Offer offer) {
        this.offers.add(offer);
    }
}