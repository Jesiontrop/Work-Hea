package com.jesiontrop.workhea.web.controller;

import com.jesiontrop.workhea.model.Offer;
import com.jesiontrop.workhea.repository.MySQLOfferRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/offer")
public class OfferController {

    MySQLOfferRepository offerRepository;

    @Autowired
    public OfferController(MySQLOfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    @GetMapping("/{id}")
    public String showOffer(@PathVariable("id") Long id, Model model) {
        Offer offer = offerRepository.findById(id).orElse(null);
        if (offer == null)
            return "redirect:/offer/error/notfound";

        model.addAttribute("offer", offer);

        return "/offer";
    }

    @GetMapping("/error/notfound")
    public String errorNotFoundOffer() {

        return "error/errorNotFoundOffer";
    }
}
