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
    @Query( value = "SELECT * FROM offer WHERE organization_id = :organizationId",
            nativeQuery = true)
    List<Offer> findOfferByOrOrganizationIdEquals(Long organizationId);

    @Query( value = "SELECT * FROM offer WHERE organization_id = :organizationId",
            countQuery = "SELECT count(*) FROM offer WHERE organization_id = :organizationId",
            nativeQuery = true)
    List<Offer> findOfferByOrOrganizationIdEquals(Long organizationId, Pageable pageable);

    //MySql request
    @Modifying
    @Query( value = "SELECT * FROM offer WHERE MATCH (vacancy_title) AGAINST (:q)",
            nativeQuery = true)
    List<Offer> findAllByVacancyTitleContains(@Param("q") String q);

    //MySql request
    @Query( value = "SELECT * FROM offer WHERE MATCH (vacancy_title) AGAINST (:q)",
            countQuery = "SELECT count(*) FROM offer WHERE MATCH (vacancy_title) AGAINST (:q)",
        nativeQuery = true)
    List<Offer> findAllByVacancyTitleContains(@Param("q") String q, Pageable pageable);
}
