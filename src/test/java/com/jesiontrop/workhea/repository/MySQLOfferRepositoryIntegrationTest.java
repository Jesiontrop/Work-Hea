package com.jesiontrop.workhea.repository;

import com.jesiontrop.workhea.model.Offer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class MySQLOfferRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private MySQLOfferRepository mySQLOfferRepository;

    @Test
    void findAll() {
        List<Offer> offers = new ArrayList<>();
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

        List<Offer> found = (List<Offer>) mySQLOfferRepository.findAll();
        assertEquals(5, found.size());

        assertThat(found).isEqualTo(offers);

    }

    @Test
    void findOfferByOrOrganizationIdEquals() {

    }

    @Test
    void testFindOfferByOrOrganizationIdEquals() {
    }

    @Test
    void findAllByVacancyTitleContains() {
    }

    @Test
    void testFindAllByVacancyTitleContains() {
    }
}