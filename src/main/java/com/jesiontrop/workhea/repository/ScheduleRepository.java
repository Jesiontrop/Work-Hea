package com.jesiontrop.workhea.repository;

import com.jesiontrop.workhea.model.Schedule;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ScheduleRepository extends PagingAndSortingRepository<Schedule, Long> {
}
