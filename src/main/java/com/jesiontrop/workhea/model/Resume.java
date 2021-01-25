package com.jesiontrop.workhea.model;

import com.jesiontrop.workhea.security.model.User;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
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
