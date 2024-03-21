package com.ssafy.domain.patent.entity;

import com.ssafy.domain.company.entity.Company;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "patent")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Patent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patent_id")
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @NotNull
    @Column(name = "patent_name")
    private String patentName;

    @NotNull
    @Column(name = "patent_date")
    private String patentDate;

    @NotNull
    @Column(name = "patent_summary", columnDefinition = "TEXT")
    private String patentSummary;

}
