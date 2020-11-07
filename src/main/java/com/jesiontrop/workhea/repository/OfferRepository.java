package com.jesiontrop.workhea.repository;

import com.jesiontrop.workhea.model.Offer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OfferRepository extends CrudRepository<Offer, Long> {

    List<Offer> findAllByVacancyTitleContains(String Query);
}
