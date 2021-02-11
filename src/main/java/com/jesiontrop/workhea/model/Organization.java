package com.jesiontrop.workhea.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "organization_sequence")
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

    public Organization(@NotBlank(message = "Name of organization is required") String nameOfOrganization,
                        @NotBlank(message = "Organization Number is required") String organizationNumber) {
        this.nameOfOrganization = nameOfOrganization;
        this.organizationNumber = organizationNumber;
    }
}