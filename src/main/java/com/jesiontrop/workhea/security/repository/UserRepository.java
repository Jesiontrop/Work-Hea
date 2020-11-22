package com.jesiontrop.workhea.security.repository;

import com.jesiontrop.workhea.security.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);
}
