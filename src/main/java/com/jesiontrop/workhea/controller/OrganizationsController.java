package com.jesiontrop.workhea.controller;

import com.jesiontrop.workhea.model.Offer;
import com.jesiontrop.workhea.model.Organization;
import com.jesiontrop.workhea.props.OfferProps;
import com.jesiontrop.workhea.props.OrganizationProps;
import com.jesiontrop.workhea.repository.OfferRepository;
import com.jesiontrop.workhea.repository.OrganizationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/organizations")
public class OrganizationsController {

    OrganizationRepository organizationRepository;
    OfferRepository offerRepository;

    OrganizationProps organizationProps;
    OfferProps offerProps;

    public OrganizationsController(OrganizationRepository organizationRepository, OfferRepository offerRepository, OrganizationProps organizationProps, OfferProps offerProps) {
        this.organizationRepository = organizationRepository;
        this.offerRepository = offerRepository;
        this.organizationProps = organizationProps;
        this.offerProps = offerProps;
    }

    @GetMapping("/add")
    public String addOrganization(Model model) {
        return "/organization/addOrgForm";
    }

    @PostMapping("/add")
    public String processAddOrganization(@ModelAttribute Organization org) {
        organizationRepository.save(org);

        return "redirect:/search/organization";
    }

    @GetMapping("/{id}")
    public String showOrganization(@PathVariable("id") Long id, Model model) {
        Organization organization = organizationRepository.findById(id).orElse(null);
        if (organization == null)
            return "redirect:/organizations/error/notfound";

        model.addAttribute("organization", organization);

        return "/organization";
    }

    @GetMapping("/{id}/offers")
    public String showOrganizationOffers(@PathVariable("id") Long id, Model model) {
        Organization organization = organizationRepository.findById(id).orElse(null);
        if (organization == null)
            return "redirect:/organizations/error/notfound";

        List<Offer> offers = organization.getOffers();
        model.addAttribute("offers", offers);

        return "/organization/organizationOffers";
    }

    @GetMapping("/{id}/offers/add")
    public String addOrgOffer(@PathVariable("id") Long id, Model model) {
        Organization organization = organizationRepository.findById(id).orElse(null);
        if (organization == null)
            return "redirect:/organizations/error/notfound";

        return "/organization/addOrgOffersForm";
    }

    @PostMapping("/{id}/offers/add")
    public String processAddOrgOffer(@PathVariable("id") Long id, @ModelAttribute Offer offer) {
        Organization organization = organizationRepository.findById(id).orElse(null);
        if (organization == null)
            return "redirect:/search/organization";

        offer.setOrganization(organization);
        Offer save =  offerRepository.save(offer);

        organization.addOffer(save);
        organizationRepository.save(organization);

        return "redirect:/organizations/" + id.toString() + "/offers";
    }

    @GetMapping("/error/notfound")
    public String errorNotFoundOffer() {

        return "error/errorNotFoundOffer";
    }
}
