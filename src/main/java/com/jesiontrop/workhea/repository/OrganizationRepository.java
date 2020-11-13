package com.jesiontrop.workhea.repository;

import com.jesiontrop.workhea.model.Organization;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrganizationRepository extends CrudRepository<Organization, Long> {

    @Modifying
    @Query( value = "SELECT * FROM organization WHERE MATCH (name_of_organization) AGAINST (:q)",
            nativeQuery = true)
    List<Organization> findAllByNameOfOrganizationContains(@Param("q") String q);
}
