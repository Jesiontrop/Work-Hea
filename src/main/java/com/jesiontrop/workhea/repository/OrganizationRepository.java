package com.jesiontrop.workhea.repository;

import com.jesiontrop.workhea.model.Organization;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface OrganizationRepository extends PagingAndSortingRepository<Organization, Long> {

    List<Organization> findAllByNameOfOrganizationContains(String q);
    
}
