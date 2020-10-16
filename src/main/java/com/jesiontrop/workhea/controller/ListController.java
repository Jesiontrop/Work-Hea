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
@RequestMapping("/list")
public class ListController {

    OrganizationRepository organizationRepository;
    OfferRepository offerRepository;

    @Autowired
    public ListController(OrganizationRepository organizationRepository, OfferRepository offerRepository) {
        this.organizationRepository = organizationRepository;
        this.offerRepository = offerRepository;
    }

    @GetMapping("/org")
    public String showOrgList(@RequestParam(defaultValue = "1") int page, Model model) {
        List<Organization> orgs = new ArrayList<>();
        organizationRepository.findAll().forEach(orgs::add);

        model.addAttribute("orgList", orgs);

        return "/list/org";
    }

    @GetMapping("/org/add")
    public String addOrganization(Model model) {
        return "/list/addOrgForm";
    }

    @PostMapping("/org/add")
    public String processAddOrganization(@ModelAttribute Organization org) {
        organizationRepository.save(org);

        return "redirect:/list/org";
    }

    @GetMapping("/org/{id}/offers")
    public String showOrgOffers(@PathVariable("id") Long id, Model model) {
        Organization org = organizationRepository.findById(id).orElse(null);
        if (org == null)
            return "redirect:/list/org";

        List<Offer> offers = org.getOffers();
        model.addAttribute("idOrg", org.getId());
        model.addAttribute("offers", offers);

        return "/list/orgOffers";
    }

    @GetMapping("/org/{id}/offers/add")
    public String addOrgOffer(@PathVariable("id") Long id, Model model) {
        Organization org = organizationRepository.findById(id).orElse(null);
        if (org == null)
            return "redirect:/list/org";

        model.addAttribute("idOrg", org.getId());

        return "/list/addOrgOffersForm";
    }

    @PostMapping("/org/{id}/offers/add")
    public String processAddOrgOffer(@PathVariable("id") Long id, @ModelAttribute Offer offer) {
        Organization org = organizationRepository.findById(id).orElse(null);
        if (org == null)
            return "redirect:/list/org";

        Offer save =  offerRepository.save(offer);
        org.addOffer(save);

        return "redirect:/list" + id.toString() + "/offers";
    }
}
