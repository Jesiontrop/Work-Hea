package com.jesiontrop.workhea.repository;

import com.jesiontrop.workhea.model.Offer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OfferRepository extends CrudRepository<Offer, Long> {

    Iterable<Offer> findAll(Pageable pageable);

    @Modifying
    @Query( value = "SELECT o FROM Offer o WHERE o.organization.id = :organizationId")
    List<Offer> findOfferByOrOrganizationIdEquals(Long organizationId);

    @Query( value = "SELECT o FROM Offer o WHERE o.organization.id = :organizationId")
    List<Offer> findOfferByOrOrganizationIdEquals(Long organizationId, Pageable pageable);

    /*
    MySql request
    "MATCH () AGAINST ()" only work if vacancy_title have FULLTEXT
    else need start the request -
    "
        ALTER TABLE offer
        ADD FULLTEXT(vacancy_title)
    "
     */
    @Modifying
    @Query( value = "SELECT * FROM offer WHERE MATCH (vacancy_title) AGAINST (:q)",
            nativeQuery = true)
    List<Offer> findAllByVacancyTitleContains(@Param("q") String q);

    /*
    MySql request
    "MATCH () AGAINST ()" only work if vacancy_title have FULLTEXT
    else need start the request -
    "
        ALTER TABLE offer
        ADD FULLTEXT(vacancy_title)
    "
     */
    @Query( value = "SELECT * FROM offer WHERE MATCH (vacancy_title) AGAINST (:q)",
            countQuery = "SELECT count(*) FROM offer WHERE MATCH (vacancy_title) AGAINST (:q)",
        nativeQuery = true)
    List<Offer> findAllByVacancyTitleContains(@Param("q") String q, Pageable pageable);
}
