package com.jesiontrop.workhea.repository;

import com.jesiontrop.workhea.model.Specialization;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SpecializationRepository extends PagingAndSortingRepository<Specialization, Long> {
}
