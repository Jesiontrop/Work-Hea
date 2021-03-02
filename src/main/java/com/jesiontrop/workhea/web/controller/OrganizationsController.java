package com.jesiontrop.workhea.web.controller;

import com.jesiontrop.workhea.model.Offer;
import com.jesiontrop.workhea.model.Organization;
import com.jesiontrop.workhea.props.OfferProps;
import com.jesiontrop.workhea.props.OrganizationProps;
import com.jesiontrop.workhea.repository.MySQLOfferRepository;
import com.jesiontrop.workhea.repository.MySQLOrganizationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/organizations")
public class OrganizationsController {

    MySQLOrganizationRepository organizationRepository;
    MySQLOfferRepository offerRepository;

    OrganizationProps organizationProps;
    OfferProps offerProps;

    @Autowired
    public OrganizationsController(MySQLOrganizationRepository organizationRepository,
                                   MySQLOfferRepository offerRepository,
                                   OrganizationProps organizationProps,
                                   OfferProps offerProps) {
        this.organizationRepository = organizationRepository;
        this.offerRepository = offerRepository;
        this.organizationProps = organizationProps;
        this.offerProps = offerProps;
    }

    @GetMapping
    public String showOrganizations() {

        return "redirect:/search/organization";
    }

    @GetMapping("/add")
    public String addOrganization() {
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
    public String showOrganizationOffers(@PathVariable("id") Long id,
                                         @RequestParam(value = "page", defaultValue = "1") Integer page,
                                         Model model) {
        Organization organization = organizationRepository.findById(id).orElse(null);
        if (organization == null)
            return "redirect:/organizations/error/notfound";

        int pageSize = offerProps.getPageSize();

        Pageable offerPageRequest = PageRequest.of(0, pageSize);
        for (long i = 2; i <= page; i++)
            offerPageRequest = offerPageRequest.next();
        List<Offer> offers = offerRepository.findOfferByOrOrganizationIdEquals(id, offerPageRequest);

        long offersSize = offerRepository.count();
        long pagesCount = (offersSize + pageSize - 1)/ pageSize;

        List<String> pagesArray = new ArrayList<>();
        for (long i = 1; i <= pagesCount; i++)
            pagesArray.add(Long.toString(i));

        model.addAttribute("offers", offers);
        model.addAttribute("offersSize", offersSize);
        model.addAttribute("pagesCount", pagesCount);
        model.addAttribute("pagesArray", pagesArray);
        model.addAttribute("page", page);

        return "/organization/organizationOffers";
    }

    @GetMapping("/{id}/offers/add")
    public String addOrgOffer(@PathVariable("id") Long id) {
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
