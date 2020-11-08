package com.jesiontrop.workhea.repository;

import com.jesiontrop.workhea.model.Offer;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OfferRepository extends CrudRepository<Offer, Long> {

    @Modifying
    @Query( value = "SELECT * FROM offer WHERE MATCH (vacancy_title) AGAINST (:q)",
            nativeQuery = true)
    List<Offer> findAllByVacancyTitleContains(@Param("q") String q);
}
