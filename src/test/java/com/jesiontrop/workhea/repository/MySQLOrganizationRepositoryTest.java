package com.jesiontrop.workhea.repository;

import com.jesiontrop.workhea.model.Organization;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;

class MySQLOrganizationRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private MySQLOrganizationRepository mySQLOrganizationRepository;

    @Test
    void findAll() {

    }

    @Test
    void findAllByNameOfOrganizationContains() {
    }

    @Test
    void testFindAllByNameOfOrganizationContains() {
    }
}