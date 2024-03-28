package com.ssafy.domain.company.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "youth_company")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CompanyYouth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "youth_company_id")
    private Long id;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @NotNull
    @Column(name = "flexible_work_arrangement")
    private boolean flexibleWorkArrangement;

    @NotNull
    @Column(name = "nine_to_six")
    private boolean nineToSix;

    @NotNull
    @Column(name = "refresh_holiday")
    private boolean refreshHoliday;

    @NotNull
    @Column(name = "support_child")
    private boolean supportChild;

    @NotNull
    @Column(name = "support_something_else")
    private boolean supportSomethingElse;

    @NotNull
    @Column(name = "cafeteria")
    private boolean cafeteria;

    @NotNull
    @Column(name = "dorm_commute_bus")
    private boolean dormCommuteBus;

    @NotNull
    @Column(name = "business_lounge")
    private boolean businessLounge;

    @NotNull
    @Column(name = "gym")
    private boolean gym;

    @NotNull
    @Column(name = "childcare_facility")
    private boolean childcareFacility;

    @NotNull
    @Column(name = "support_self_development")
    private boolean supportSelfDevelopment;

    @NotNull
    @Column(name = "support_culture_life")
    private boolean supportCultureLife;

    @NotNull
    @Column(name = "support_education")
    private boolean supportEducation;

    @NotNull
    @Column(name = "company_club")
    private boolean companyClub;

    @NotNull
    @Column(name = "go_to_school_later")
    private boolean goToSchoolLater;

    @NotNull
    @Column(name = "salary")
    private Long salary;

    @NotNull
    @Column(name = "employment_rate")
    private float employmentRate;

    @NotNull
    @Column(name = "youth_rate")
    private float youthRate;

    @NotNull
    @Column(name = "youth_hire_rate")
    private float youthHireRate;

    @NotNull
    @Column(name = "youth_hire_increasing_rate")
    private float youthHireIncreasingRate;

    @NotNull
    @Column(name = "youth_hire_increasing_cnt")
    private float youthHireIncreasingCnt;

}
