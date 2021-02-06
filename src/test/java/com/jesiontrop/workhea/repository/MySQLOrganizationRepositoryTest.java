package com.jesiontrop.workhea.repository;

import com.jesiontrop.workhea.model.Offer;
import com.jesiontrop.workhea.model.Organization;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@TestPropertySource(locations = "classpath:application.properties")
@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MySQLOrganizationRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private MySQLOrganizationRepository mySQLOrganizationRepository;

    @Test
    void findAll() {

        List<Organization> organizations = new ArrayList<>();

        mySQLOrganizationRepository.findAll().forEach(organizations::add);

        Organization organization;

        //#1
        organization = new Organization("Samsung", "+71234567890");
        organizations.add(organization);
        testEntityManager.persist( organization);

        //#2
        organization = new Organization("Microsoft", "+71234567890");
        organizations.add(organization);
        testEntityManager.persist( organization);

        //#3
        organization = new Organization("Exactpro", "+71234567890");
        organizations.add(organization);
        testEntityManager.persist( organization);

        //#4
        organization = new Organization("LG", "+71234567890");
        organizations.add(organization);
        testEntityManager.persist( organization);

        //#5
        organization = new Organization("Sony", "+71234567890");
        organizations.add(organization);
        testEntityManager.persist( organization);

        testEntityManager.flush();

        List<Organization> found = new ArrayList<>();
        mySQLOrganizationRepository.findAll().forEach(found::add);

        assertEquals(organizations.size(), found.size());

        assertTrue(found.containsAll(organizations));

        testEntityManager.clear();
    }

    @Test
    void findAllByNameOfOrganizationContains() {
    }

    @Test
    void testFindAllByNameOfOrganizationContains() {
    }
}