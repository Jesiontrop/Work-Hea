package com.jesiontrop.workhea.repository;

import com.jesiontrop.workhea.model.Resume;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ResumeRepository extends PagingAndSortingRepository<Resume, Long> {
}
