package com.jesiontrop.workhea.model;

import com.jesiontrop.workhea.security.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@RestResource(rel = "resumes", path = "resumes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Resume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    private String sex;
    private Date dateOfBirth;
    private String cityOfResidence;

    private String phoneNumber;
    private String workPhone;
    private String homePhone;
    private String email;
    private String anotherSite;

    private String careerObjective;

    @ManyToMany(targetEntity = Specialization.class)
    @RestResource(rel = "specializations", path = "specializations")
    private List<Specialization> specializations;
    private Integer salary;
    @ManyToMany(targetEntity = Employment.class)
    private List<Employment> employments;
    @ManyToMany(targetEntity = Schedule.class)
    private List<Schedule> schedules;

    @Lob
    @Column(length = 50000)
    private String aboutMe;

    private Date placedAt;

    @PrePersist
    void placedAt() {
        this.placedAt = new Date();
    }

    @ManyToOne(targetEntity = User.class)
    private User user;
}
