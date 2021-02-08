package com.jesiontrop.workhea.repository;

import com.jesiontrop.workhea.model.Offer;
import com.jesiontrop.workhea.model.Organization;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MySQLOfferRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private MySQLOfferRepository mySQLOfferRepository;

    @Test
    void findAll() {
        List<Offer> offers = new ArrayList<>();
        mySQLOfferRepository.findAll().forEach(offers::add);
        Offer offer;

        //#1
        offer = new Offer("Java Developer", 60000);
        offers.add(offer);
        testEntityManager.persist( offer);
        //#2
        offer = new Offer("JavaScript Developer", 60000);
        offers.add(offer);
        testEntityManager.persist( offer);
        //#3
        offer = new Offer("C# Developer", 60000);
        offers.add(offer);
        testEntityManager.persist( offer);
        //#4
        offer = new Offer("Python Developer", 60000);
        offers.add(offer);
        testEntityManager.persist( offer);
        //#5
        offer = new Offer("PHP Developer", 60000);
        offers.add(offer);
        testEntityManager.persist( offer);

        testEntityManager.flush();

        List<Offer> found = new ArrayList<>();
        mySQLOfferRepository.findAll().forEach(found::add);

        assertEquals(offers.size(), found.size());

        assertTrue(found.containsAll(offers));

    }

    @Test
    void findOfferByOrOrganizationIdEquals() {
        Organization organization = new Organization("Microsoft", "+71234567890");
        testEntityManager.persist(organization);
        testEntityManager.flush();

        List<Offer> offers = new ArrayList<>();
        Offer offer;

        //#1
        offer = new Offer("Java Developer", 60000, organization);
        offers.add(offer);
        testEntityManager.persist( offer);
        //#2
        offer = new Offer("JavaScript Developer", 60000, organization);
        offers.add(offer);
        testEntityManager.persist( offer);
        //#3
        offer = new Offer("C# Developer", 60000, organization);
        offers.add(offer);
        testEntityManager.persist( offer);
        //#4
        offer = new Offer("Python Developer", 60000, organization);
        offers.add(offer);
        testEntityManager.persist( offer);
        //#5
        offer = new Offer("PHP Developer", 60000, organization);
        offers.add(offer);
        testEntityManager.persist( offer);

        testEntityManager.flush();

        List<Offer> found = mySQLOfferRepository.findOfferByOrOrganizationIdEquals(organization.getId());
        
        assertEquals(offers.size(), found.size());

        assertTrue(found.containsAll(offers));
    }
    
    @Test
    void findAllByVacancyTitleContains() {
    }

}