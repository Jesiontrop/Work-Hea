package com.jesiontrop.workhea.controller;

import com.jesiontrop.workhea.model.Offer;
import com.jesiontrop.workhea.model.Organization;
import com.jesiontrop.workhea.repository.OfferRepository;
import com.jesiontrop.workhea.repository.OrganizationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/organizations")
public class OrganizationsController {

    OrganizationRepository organizationRepository;
    OfferRepository offerRepository;

    @Autowired
    public OrganizationsController(OrganizationRepository organizationRepository, OfferRepository offerRepository) {
        this.organizationRepository = organizationRepository;
        this.offerRepository = offerRepository;
    }

    @GetMapping
    public String showOrgList(@RequestParam(defaultValue = "1") int page, Model model) {
        List<Organization> orgs = new ArrayList<>();
        organizationRepository.findAll().forEach(orgs::add);

        model.addAttribute("organizationList", orgs);

        return "/list/organizationList";
    }

    @GetMapping("/add")
    public String addOrganization(Model model) {
        return "/list/addOrgForm";
    }

    @PostMapping("/add")
    public String processAddOrganization(@ModelAttribute Organization org) {
        organizationRepository.save(org);

        return "redirect:/organizations";
    }

    @GetMapping("/{id}/offers")
    public String showOrgOffers(@PathVariable("id") Long id, Model model) {
        Organization org = organizationRepository.findById(id).orElse(null);
        if (org == null)
            return "redirect:/organizations";

        List<Offer> offers = org.getOffers();
        model.addAttribute("offers", offers);

        return "/list/organizationOffers";
    }

    @GetMapping("/{id}/offers/add")
    public String addOrgOffer(@PathVariable("id") Long id, Model model) {
        Organization org = organizationRepository.findById(id).orElse(null);
        if (org == null)
            return "redirect:/organizations";

        return "/list/addOrgOffersForm";
    }

    @PostMapping("/{id}/offers/add")
    public String processAddOrgOffer(@PathVariable("id") Long id, @ModelAttribute Offer offer) {
        Organization org = organizationRepository.findById(id).orElse(null);
        if (org == null)
            return "redirect:/organizations";

        Offer save =  offerRepository.save(offer);
        org.addOffer(save);
        organizationRepository.save(org);

        return "redirect:/organizations/" + id.toString() + "/offers";
    }
}
