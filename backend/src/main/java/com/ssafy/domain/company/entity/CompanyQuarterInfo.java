package com.ssafy.domain.company.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "quarter_info")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CompanyQuarterInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quarter_info_id")
    private Long id;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @NotNull
    @Column(name = "first_quarter_date")
    private String firstQuarterDate;

    @NotNull
    @Column(name = "second_quarter_date")
    private String secondQuarterDate;

    @NotNull
    @Column(name = "third_quarter_date")
    private String thirdQuarterDate;

}
