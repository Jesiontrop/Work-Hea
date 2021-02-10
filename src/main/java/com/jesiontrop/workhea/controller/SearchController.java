package com.jesiontrop.workhea.controller;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/search")
public class SearchController {

    private final MySQLOfferRepository offerRepository;
    private final MySQLOrganizationRepository organizationRepository;

    private final OfferProps offerProps;
    private final OrganizationProps organizationProps;

    @Autowired
    public SearchController(MySQLOfferRepository offerRepository,
                            MySQLOrganizationRepository organizationRepository,
                            OfferProps offerProps,
                            OrganizationProps organizationProps) {
        this.offerRepository = offerRepository;
        this.organizationRepository = organizationRepository;
        this.offerProps = offerProps;
        this.organizationProps = organizationProps;
    }

    @GetMapping("/vacancy")
    public String showVacancy(@RequestParam(defaultValue = "") String q,
                              @RequestParam(value = "page", defaultValue = "1") Integer page,
                              Model model) {
        final String searchError = "No results found for \"" + q + "\"";

        int pageSize = offerProps.getPageSize();
        long offersSize;

        Pageable offerPageRequest = PageRequest.of(0, pageSize);
        for (long i = 2; i <= page; i++)
            offerPageRequest = offerPageRequest.next();

        List<Offer> offers = new ArrayList<>();
        if (!q.equals("")) {
            offers = offerRepository.findAllByVacancyTitleContains(q, offerPageRequest);
            offersSize = offerRepository.findAllByVacancyTitleContains(q).size();
        }
        else {
            offerRepository.findAll(offerPageRequest).forEach(offers::add);
            offersSize = offerRepository.count();
        }

        boolean hasSearchError = false;
        if (offersSize == 0)
            hasSearchError = true;

        long pagesCount = (offersSize + pageSize - 1)/ pageSize;

        List<String> pagesArray = new ArrayList<>();
        for (long i = 1; i <= pagesCount; i++)
            pagesArray.add(Long.toString(i));

        model.addAttribute("searchError", searchError);
        model.addAttribute("hasSearchError", hasSearchError);
        model.addAttribute("offers", offers);
        model.addAttribute("offersSize", offersSize);
        model.addAttribute("pagesCount", pagesCount);
        model.addAttribute("pagesArray", pagesArray);
        model.addAttribute("page", page);
        model.addAttribute("q", q);

        return "/search/vacancies";
    }

    @GetMapping("/organization")
    public String showOrganization(@RequestParam(defaultValue = "") String q,
                                   @RequestParam(value = "page", defaultValue = "1") Integer page,
                                   Model model) {
        final String searchError = "No results found for \"" + q + "\"";

        int pageSize = organizationProps.getPageSize();

        long organizationListSize;

        Pageable organizationPageRequest = PageRequest.of(0, pageSize);
        for (long i = 2; i <= page; i++)
            organizationPageRequest = organizationPageRequest.next();

        List<Organization> organizationList = new ArrayList<>();
        if (!q.equals("")) {
            organizationList = organizationRepository.findAllByNameOfOrganizationContains(q, organizationPageRequest);
            organizationListSize = organizationRepository.findAllByNameOfOrganizationContains(q).size();
        }
        else {
            organizationRepository.findAll(organizationPageRequest).forEach(organizationList::add);
            organizationListSize = organizationRepository.count();
        }

        boolean hasSearchError = false;
        if (organizationList.size() == 0)
            hasSearchError = true;

        long pagesCount = (organizationListSize + pageSize - 1)/ pageSize;

        List<String> pagesArray = new ArrayList<>();
        for (long i = 1; i <= pagesCount; i++)
            pagesArray.add(Long.toString(i));

        model.addAttribute("searchError", searchError);
        model.addAttribute("hasSearchError", hasSearchError);
        model.addAttribute("organizationList", organizationList);
        model.addAttribute("organizationListSize", organizationListSize);
        model.addAttribute("pagesCount", pagesCount);
        model.addAttribute("pagesArray", pagesArray);
        model.addAttribute("page", page);
        model.addAttribute("q", q);

        return "/search/organizationList";
    }
}
