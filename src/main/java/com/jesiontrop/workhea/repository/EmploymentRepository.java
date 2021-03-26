package com.jesiontrop.workhea.repository;

import com.jesiontrop.workhea.model.Employment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EmploymentRepository extends PagingAndSortingRepository<Employment, Long> {
}
