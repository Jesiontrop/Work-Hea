package com.jesiontrop.workhea.repository;

import com.jesiontrop.workhea.model.Organization;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.Id;
import java.util.List;

public interface OrganizationRepository extends CrudRepository<Organization, Long> {


}
