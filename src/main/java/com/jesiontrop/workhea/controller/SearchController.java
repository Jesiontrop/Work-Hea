package com.jesiontrop.workhea.controller;

import com.jesiontrop.workhea.model.Offer;
import com.jesiontrop.workhea.model.Organization;
import com.jesiontrop.workhea.props.OfferProps;
import com.jesiontrop.workhea.props.OrganizationProps;
import com.jesiontrop.workhea.repository.OfferRepository;
import com.jesiontrop.workhea.repository.OrganizationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/search")
public class SearchController {

    private final OfferRepository offerRepository;
    private final OrganizationRepository organizationRepository;

    private final OfferProps offerProps;
    private final OrganizationProps organizationProps;

    @Autowired
    public SearchController(OfferRepository offerRepository, OrganizationRepository organizationRepository, OfferProps offerProps, OrganizationProps organizationProps) {
        this.offerRepository = offerRepository;
        this.organizationRepository = organizationRepository;
        this.offerProps = offerProps;
        this.organizationProps = organizationProps;
    }

    @GetMapping("/vacancy")
    public String showVacancy(@RequestParam(defaultValue = "") String q, Model model) {
        Pageable offerPageRequest = PageRequest.of(0, offerProps.getPageSize());

        List<Offer> offers = new ArrayList<>();
        if (!q.equals(""))
            offers = offerRepository.findAllByVacancyTitleContains(q, offerPageRequest);
        else
            offerRepository.findAll(offerPageRequest).forEach(offers::add);

        final String searchError = "No results found for \"" + q + "\"";

        boolean hasSearchError = false;
        if (offers.size() == 0)
            hasSearchError = true;

        model.addAttribute("searchError", searchError);
        model.addAttribute("hasSearchError", hasSearchError);
        model.addAttribute("offers", offers);

        return "/search/vacancies";
    }

    @GetMapping("/organization")
    public String showOrganization(@RequestParam(defaultValue = "") String q, Model model) {
        Pageable organizationPageRequest = PageRequest.of(0, organizationProps.getPageSize());

        List<Organization> organizationList = new ArrayList<>();
        if (!q.equals(""))
            organizationList = organizationRepository.findAllByNameOfOrganizationContains(q, organizationPageRequest);
        else
            organizationRepository.findAll(organizationPageRequest).forEach(organizationList::add);

        final String searchError = "No results found for \"" + q + "\"";

        boolean hasSearchError = false;
        if (organizationList.size() == 0)
            hasSearchError = true;

        model.addAttribute("searchError", searchError);
        model.addAttribute("hasSearchError", hasSearchError);
        model.addAttribute("organizationList", organizationList);

        return "/search/organizationList";
    }
}
