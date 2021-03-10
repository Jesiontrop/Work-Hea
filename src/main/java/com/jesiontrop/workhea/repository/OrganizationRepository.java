package com.jesiontrop.workhea.repository;

import com.jesiontrop.workhea.model.Organization;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "organizations", path = "organizations")
public interface OrganizationRepository extends CrudRepository<Organization, Long> {

    List<Organization> findAllByNameOfOrganizationContains(String q);
}
